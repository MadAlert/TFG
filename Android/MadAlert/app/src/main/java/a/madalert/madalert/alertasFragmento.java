package a.madalert.madalert;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import a.madalert.madalert.Adapter.DataAdapter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlertasFragmento extends Fragment {

    private TextView textView;
    private TextView firstTime;

    private CompositeDisposable mSub;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;

    private RecyclerView mRecyclerView;

    private DataAdapter mAdapter;

    private ArrayList<Alertas> mAndroidArrayList;

    private String mDistrito;

    private String mHayCategorias;

    private boolean mTodas;

    private boolean mFirstTime;

    private boolean mCheckedSw;
    private String latitud, longitud;

    private ListaAlertas.OnFragmentInteractionListener mListener;

    public static final String TAG = AlertasFragmento.class.getSimpleName();

    private VolleyS volley;

    protected RequestQueue fRequestQueue;
    private String distritoObtenido;

    public AlertasFragmento() {

    }

    private void initRecyclerView(View v) {
        textView = (TextView) v.findViewById(R.id.textView);
        firstTime = (TextView) v.findViewById(R.id.firstTime);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void initSharedPreferences() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mDistrito = mSharedPreferences.getString("distritoConf", "");
        mHayCategorias = mSharedPreferences.getString("listaCat","");
        mTodas = mSharedPreferences.getBoolean("todasBool", false);
        mFirstTime = mSharedPreferences.getBoolean("primeraVez", true);
        mCheckedSw = mSharedPreferences.getBoolean("isCheckedSw", false);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        volley = VolleyS.getInstance(getActivity().getApplicationContext());
        fRequestQueue = volley.getRequestQueue();
        View v = inflater.inflate(R.layout.fragment_alertas, container, false);

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.anadirAlerta);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent AddAlerta = new Intent(getContext(), AddAlertaActivity.class);
                startActivity(AddAlerta);
            }
        });

        mSub = new CompositeDisposable();
        initRecyclerView(v);
        initSharedPreferences();
        editor = mSharedPreferences.edit();
        if (mFirstTime) {
            // first time task
            // record the fact that the app has been started at least once
            firstTime.setText(R.string.firstTime);
        }

        //if(!mCheckedSw)
            loadAlerta();
        //else {
          //  ejecutar();
        //}

        return v;
    }

    private void loadAlerta() {
        if(mCheckedSw) {
            latitud = mSharedPreferences.getString("latitud", "");
            longitud = mSharedPreferences.getString("longitud", "");
            obtenerDistrito(latitud, longitud);
        }
        else{
            mDistrito = mSharedPreferences.getString("distritoConf", "");
        }
        if(mTodas) {
            mSub.add(NetworkUtil.getRetrofit().getAlertasDistrito(mDistrito)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        }
        else{
            String[] categoriasP;
            categoriasP = mHayCategorias.split(",");
            mSub.add(NetworkUtil.getRetrofit().getAlertasDistritoCategoria(mDistrito, mHayCategorias)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        }

    }

    private void handleResponse(List<Alertas> alertas) {
        textView.setText("Distrito " + mDistrito);
        mAndroidArrayList = new ArrayList<>(alertas);
        mAdapter = new DataAdapter(mAndroidArrayList);
        mRecyclerView.setAdapter(mAdapter);

        if(mAndroidArrayList.isEmpty())
            firstTime.setText("¡No hay alertas para esa configuración!");

        /*mTv1.setText(alertas.getAlertas());
        mTv2.setText(alertas.getDistrito());*/

    }

    private void handleError(Throwable error) {
        //showSnackBarMessage("ERRRRRRRR Error !");
    }

    public static void ejecutar(){
        time time = new time();
        time.execute();
    }

    public static class time extends AsyncTask<Void, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(Void... voids) {
            for(int i = 1; i < 20; i++) { // 20 segundos
                //Llamar a la funcion que comprueba la distancia
                //si la distacia es >=100 -> actualizamos y llamamos a cargar alerta
                //Hay que llamar a la funcion del radio y obtener los distritos
                Log.d("comprobar", "Cada 2 segundos");
            }

            return true;
        }

        protected void onPostExecuted(Boolean aBoolean){
            ejecutar();
        }
    }


    private void obtenerDistrito(String latitud, String longitud) {
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
                    60000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
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


}
