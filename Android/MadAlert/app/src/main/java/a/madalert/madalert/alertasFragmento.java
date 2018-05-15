package a.madalert.madalert;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import a.madalert.madalert.Adapter.DataAdapter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlertasFragmento extends Fragment {

    private TextView textView;
    private TextView firstTime;

    private HashMap<String, Pair<Double, Double>> distCoord;
    private ArrayList<String> distRadio;
    private int kms;


    private CompositeDisposable mSub;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;

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

    private ListaAlertas.OnFragmentInteractionListener mListener;

    public static final String TAG = AlertasFragmento.class.getSimpleName();

    private VolleyS volley;

    protected RequestQueue fRequestQueue;
    private String distritoObtenido;

    public AlertasFragmento() {

    }

    public void initCoord(){
        distCoord = new HashMap<String, Pair<Double, Double>>();
        distCoord.put("Arganzuela", new Pair<>(40.400861, -3.699350));
        distCoord.put("Barajas", new Pair<>(40.4839402, -3.5701402));
        distCoord.put("Carabanchel", new Pair<>(40.381607, -3.735203));
        distCoord.put("Centro", new Pair<>(40.4169416, -3.7083759));
        distCoord.put("Chamartín", new Pair<>(40.460367, -3.676567));
        distCoord.put("Chamberí", new Pair<>(40.438656, -3.704180));
        distCoord.put("Ciudad Lineal", new Pair<>(40.455531, -3.656119));
        distCoord.put("Fuencarral-El Pardo", new Pair<>(40.494289, -3.693477));
        distCoord.put("General", new Pair<>(40.4420755, -3.7458085));
        distCoord.put("Hortaleza", new Pair<>(40.485152, -3.634796));
        distCoord.put("Latina", new Pair<>(40.387812, -3.773530));
        distCoord.put("Moncloa-Aravaca", new Pair<>(40.443568,  -3.742829));
        distCoord.put("Moratalaz", new Pair<>(40.407016, -3.644330));
        distCoord.put("Puente de Vallecas", new Pair<>(40.386887, -3.658476));
        distCoord.put("Retiro", new Pair<>(40.4101076, -3.6736514));
        distCoord.put("Salamanca", new Pair<>(40.429807, -3.673778));
        distCoord.put("San Blas-Canillejas", new Pair<>(40.436229, -3.599431));
        distCoord.put("Tetuán", new Pair<>(40.460158, -3.698835));
        distCoord.put("Usera", new Pair<>(40.377026, -3.701982));
        distCoord.put("Vicálvaro", new Pair<>(40.393974, -3.581134));
        distCoord.put("Villa de Vallecas", new Pair<>(40.355089, -3.621192));
        distCoord.put("Villaverde", new Pair<>(40.345987, -3.693332));
    }

    public void recorrerRadio(Boolean ubicacionActivada){
        initCoord();
        distRadio = new ArrayList<>();
        Iterator<Map.Entry<String, Pair<Double, Double>>> iterator = distCoord.entrySet().iterator();
        Double parsLat, parsLong;
        if(ubicacionActivada) {
            latitud = mSharedPreferences.getString("latitud", "");
            longitud = mSharedPreferences.getString("longitud", "");
            parsLat = Double.parseDouble(latitud);
            parsLong = Double.parseDouble(longitud);
        }
        else{
            auxDistrito=mDistrito;
            Pair<Double,Double> latLong = distCoord.get(auxDistrito);
            parsLat = latLong.first;
            parsLong = latLong.second;
        }
        kms = mSharedPreferences.getInt("km", 0);

        while (iterator.hasNext()){
            Map.Entry<String, Pair<Double, Double>> it = iterator.next();
            Double lat = it.getValue().first;
            Double longi = it.getValue().second;
            //Double var = Math.sqrt( (Math.pow(parsLat-lat,2) + (Math.pow(parsLong-longi, 2))));
            Double var = distanciaCoord(parsLat, parsLong, lat, longi);
            if(var <= kms){
                distRadio.add(it.getKey());
            }
        }
        Log.d("nerea", distRadio.toString());
    }

    public static double distanciaCoord(double lat1, double lng1, double lat2, double lng2) {
        //double radioTierra = 3958.75;//en millas
        double radioTierra = 6371;//en kilómetros
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        double distancia = radioTierra * va2;

        return distancia;
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
        else {
            try {
                loadAlerta();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //else {
            ejecutar();
            //}
        }

        return v;
    }

    private void loadAlerta() throws IOException, JSONException {
        auxDistrito="";
        if(mCheckedSw) {
           // auxDistrito="";
            // esto tiene que estar aqui, no mover
            latitud = mSharedPreferences.getString("latitud", "");
            longitud = mSharedPreferences.getString("longitud", "");
            //obtDistrito(latitud, longitud);
            obtenerDistrito(latitud, longitud);

          /*  for(int j = 0; j < distRadio.size(); j++) {
                //auxDistrito += "," + distRadio.get(j);
                mDistrito += "," + distRadio.get(j);
            }*/
        }
        else{
            mDistrito = mSharedPreferences.getString("distritoConf", "");
            recorrerRadio(mCheckedSw);
            auxDistrito = mDistrito;
            for(int j = 0; j < distRadio.size(); j++) {
                //auxDistrito += "," + distRadio.get(j);
                auxDistrito += "," + distRadio.get(j);
            }
            if(mTodas) {
                mSub.add(NetworkUtil.getRetrofit().getAlertasDistrito(auxDistrito)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                        .subscribe(this::handleResponse, this::handleError));
            }
            else{
                String[] categoriasP;
                //obtenerDistrito(latitud, longitud);
                categoriasP = mHayCategorias.split(",");
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
                            //auxDistrito = gsonAdd.get("long_name").getAsString();
                            //mDistrito = gsonAdd.get("long_name").getAsString() + auxDistrito ;
                            mDistrito = gsonAdd.get("long_name").getAsString();
                            auxDistrito = mDistrito;
                            recorrerRadio(mCheckedSw);
                            for(int j = 0; j < distRadio.size(); j++) {
                                //auxDistrito += "," + distRadio.get(j);
                                auxDistrito += "," + distRadio.get(j);
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

}
