package a.madalert.madalert;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import a.madalert.madalert.Adapter.DataAdapter;
import a.madalert.madalert.Localizacion.Radio;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class AlertasFragmento extends Fragment {

    private TextView textView;
    private TextView firstTime;

    private HashMap<String, ArrayList<Pair<Double, Double>>> distCoord;
    private ArrayList<String> distRadio;
    private int kms;

    private AlertasFragmento.OnFragmentInteractionListener mListener;

    private CompositeDisposable mSub;
    private SharedPreferences mSharedPreferences;

    private RecyclerView mRecyclerView;

    private DataAdapter mAdapter;

    private ArrayList<Alertas> mAndroidArrayList;

    private String mDistrito;

    private String auxDistrito;

    private String mHayCategorias;

    private boolean mTodas;

    private boolean mFirstTime;

    private boolean mCheckedSw;
    private String latitud, longitud;

    public static final String TAG = AlertasFragmento.class.getSimpleName();

    private VolleyS volley;

    protected RequestQueue fRequestQueue;
    private String distritoObtenido;
    private SwipeRefreshLayout swipeRefreshLayout;

    public AlertasFragmento() {
        // Inflate the layout for this fragment
    }

    private void initRecyclerView(View v) {
        textView = (TextView) v.findViewById(R.id.textView);
        firstTime = (TextView) v.findViewById(R.id.firstTime);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Esto se ejecuta cada vez que se realiza el gesto
                try {
                    loadAlerta();
                    swipeRefreshLayout.setRefreshing(false);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initSharedPreferences() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mDistrito = mSharedPreferences.getString("distritoConf", "");
        mHayCategorias = mSharedPreferences.getString("listaCat","");
        mTodas = mSharedPreferences.getBoolean("todas", false);
        mFirstTime = mSharedPreferences.getBoolean("primeraVez", true);
        mCheckedSw = mSharedPreferences.getBoolean("isCheckedSw", false);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        volley = VolleyS.getInstance(getActivity().getApplicationContext());
        fRequestQueue = volley.getRequestQueue();

        int count = getFragmentManager().getBackStackEntryCount();
        //si no queda ningún fragment sale de este activity
        if (count == 1) {
            getFragmentManager().popBackStack();
        }

        View v = inflater.inflate(R.layout.fragment_alertas, container, false);
        mSub = new CompositeDisposable();
        initSharedPreferences();
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.anadirAlerta);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent AddAlerta = new Intent(getContext(), AddAlertaActivity.class);
                startActivity(AddAlerta);
            }
        });

        initRecyclerView(v);
        if (mFirstTime) {
            // first time task
            firstTime.setText(R.string.firstTime);
        }
        else {
            try {
                loadAlerta();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return v;
    }

    public void recorrerRadio(Boolean ubicacionActivada){
        distCoord = Radio.initCoord();
        distRadio = new ArrayList<>();
        Double parsLat=0.0, parsLong=0.0;
        if(ubicacionActivada) {
            latitud = mSharedPreferences.getString("latitud", "");
            longitud = mSharedPreferences.getString("longitud", "");
            parsLat = Double.parseDouble(latitud);
            parsLong = Double.parseDouble(longitud);
        }
        else{
            auxDistrito=mDistrito;
            if(auxDistrito != null) { // Como la ubi esta desactivada solo se quiere el marcador de centro del distrito, el get(0)
                Pair<Double, Double> latLong = distCoord.get(auxDistrito).get(0);
                parsLat = latLong.first;
                parsLong = latLong.second;
            }
        }
        kms = mSharedPreferences.getInt("km", 0);
        distRadio = Radio.obtenerDistritosRadio(distCoord,kms, parsLat, parsLong);
    }

    private void loadAlerta() throws IOException, JSONException {
        auxDistrito="";
        distRadio = new ArrayList<>();
        if(mCheckedSw) {
            // esto tiene que estar aqui, no mover
            latitud = mSharedPreferences.getString("latitud", "");
            longitud = mSharedPreferences.getString("longitud", "");
            obtenerDistrito(latitud, longitud);

            for(int j = 0; j < distRadio.size(); j++) {
                mDistrito += "," + distRadio.get(j);
            }
        }
        else{
            mDistrito = mSharedPreferences.getString("distritoConf", "");
            auxDistrito = mDistrito;
            if(!mDistrito.equals("Todos") || kms > 0) { //Cuando hay radio y distrito!=Todos
                recorrerRadio(mCheckedSw);
                for(int j = 0; j < distRadio.size(); j++) {
                    auxDistrito += "," + distRadio.get(j);
                }
            }

            if(mTodas) {
                mSub.add(NetworkUtil.getRetrofit().getAlertasDistrito(auxDistrito)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                        .subscribe(this::handleResponse, this::handleError));
            }
            else{
                mSub.add(NetworkUtil.getRetrofit().getAlertasDistritoCategoria(auxDistrito, mHayCategorias)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                        .subscribe(this::handleResponse, this::handleError));
            }
        }
    }

    private void handleResponse(List<Alertas> alertas) {
        textView.setText("Distrito " + mDistrito);
        mAndroidArrayList = new ArrayList<>(alertas);
        mAdapter = new DataAdapter(mAndroidArrayList, true, mDistrito); //Si tiene varios distritos muestra el distrito en cada RecyclerView
        mRecyclerView.setAdapter(mAdapter);

        if(mAndroidArrayList.isEmpty())
            firstTime.setText("¡No hay alertas para esa configuración!");
    }

    private void handleError(Throwable error) {

    }

    public void obtenerDistrito(String latitud, String longitud) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng="+latitud+","+longitud+"&result_type=sublocality&key=AIzaSyDOveaxbksFJQgJfQXoWvvw9vOntdr8r3o";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url, jsonObject -> {
            distritoObtenido = jsonObject.toString();

            if(distritoObtenido!=null){
                JsonParser parser = new JsonParser();
                // Obtain Array
                JsonObject gsonObj = parser.parse(distritoObtenido).getAsJsonObject();
                JsonArray demarcation = gsonObj.get("results").getAsJsonArray();
                List listDemarcation = new ArrayList();
                for (JsonElement demarc : demarcation) {
                    JsonObject gsonP = demarc.getAsJsonObject();
                    // Primitives elements of object
                    JsonArray address = gsonP.get("address_components").getAsJsonArray();
                    int i=0;
                    for(JsonElement add: address){
                        if(i==0){
                            JsonObject gsonAdd = add.getAsJsonObject();
                            mDistrito = gsonAdd.get("long_name").getAsString();
                            auxDistrito = mDistrito;
                            recorrerRadio(mCheckedSw);
                            for(int j = 0; j < distRadio.size(); j++) {
                                auxDistrito += "," + distRadio.get(j);
                            }
                            if(kms==0 && mCheckedSw){
                                SharedPreferences.Editor editor = mSharedPreferences.edit();
                                editor.putString("distritoUbicacion",mDistrito);
                                editor.commit();
                            }
                            if(mTodas) {
                                mSub.add(NetworkUtil.getRetrofit().getAlertasDistrito(auxDistrito)
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                                        .subscribe(this::handleResponse, this::handleError));
                            }
                            else {
                                mSub.add(NetworkUtil.getRetrofit().getAlertasDistritoCategoria(auxDistrito, mHayCategorias)
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                                        .subscribe(this::handleResponse, this::handleError));
                            }
                        }
                        i++;
                    }
                }
            }else{
                Toast.makeText(getActivity(), "Erroooooor", Toast.LENGTH_LONG).show();
            }
            onConnectionFinished();
        }, volleyError -> onConnectionFailed(volleyError.toString()));
        addToQueue(request);
    }

    public void addToQueue(Request request) {
        if (request != null) {
            request.setTag(this);
            if (fRequestQueue == null)
                fRequestQueue = volley.getRequestQueue();
            request.setRetryPolicy(new DefaultRetryPolicy(
                    10000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            ));
            onPreStartConnection();
            fRequestQueue.add(request);
        }
    }

    public void onPreStartConnection() {
        getActivity().setProgressBarIndeterminateVisibility(true);
    }

    public void onConnectionFinished() {
        getActivity().setProgressBarIndeterminateVisibility(false);
    }

    public void onConnectionFailed(String error) {
        getActivity().setProgressBarIndeterminateVisibility(false);
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (AlertasFragmento.OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnFragmentInteractionListener") ;
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
