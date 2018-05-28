package a.madalert.madalert;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import a.madalert.madalert.Adapter.DataAdapter;
import a.madalert.madalert.Localizacion.Radio;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MostrarMapa.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MostrarMapa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MostrarMapa extends Fragment implements
        GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MapView mapView;
    private GoogleMap map;
    private CompositeDisposable mSub;
    private SharedPreferences mSharedPreferences;
    private String latitud;
    private String longitud;
    private boolean isCheckedSw;
    private String distritoConf;
    private ArrayList<String> distRadio;
    private String mListaCat;
    private int km;
    private HashMap<String, ArrayList<Pair<Double,Double>>> distCoord;
    private ArrayList<Pair<String, Integer>> markerDistrito;
    private ImageButton button;

    private OnFragmentInteractionListener mListener;

    private int contador;

    public MostrarMapa() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MostrarMapa.
     */
    // TODO: Rename and change types and number of parameters
    public static MostrarMapa newInstance(String param1, String param2) {
        MostrarMapa fragment = new MostrarMapa();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mostrar_mapa, container, false);

        mSub = new CompositeDisposable();
        initSharedPreferences();

        distCoord = Radio.initCoord(); //Inicializo las coordenadas

        button = (ImageButton) view.findViewById(R.id.buttonMaps);
        button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 // Esto se ejecuta cada vez que se realiza el gesto
                 mapView = view.findViewById(R.id.mapG);

                 if (mapView != null) {
                     mapView.onCreate(null);
                     mapView.onResume();
                     mapView.getMapAsync(MostrarMapa.this);
                 }
             }
        });


        return view;
    }

    private void initSharedPreferences() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        latitud = mSharedPreferences.getString("latitud", "");
        longitud = mSharedPreferences.getString("longitud", "");
        isCheckedSw = mSharedPreferences.getBoolean("isCheckedSw", false);
        distritoConf = mSharedPreferences.getString("distritoConf", "");
        mListaCat = mSharedPreferences.getString("listaCat", "");
        km = mSharedPreferences.getInt("km", 0);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) view.findViewById(R.id.mapG);

        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        Circle circle;

        double parsLat = 0;
        double parsLong = 0;
        distRadio = new ArrayList<>();
        markerDistrito = new ArrayList<>();

        contador = 0;

        int kms = km;
        String cat = mListaCat;
        if (mListaCat.equals("")) {
            cat = "Todas";
        }

        if(isCheckedSw) {
            map.setMyLocationEnabled(true);

            parsLat = Double.parseDouble(latitud);
            parsLong = Double.parseDouble(longitud);
        }
        else {
            Iterator<Map.Entry<String, ArrayList<Pair<Double,Double>>>> iterator = distCoord.entrySet().iterator();
            boolean encontrado = false;
            while (iterator.hasNext() || !encontrado) {
                Map.Entry<String, ArrayList<Pair<Double,Double>>> it = iterator.next();
                if (distritoConf.equals(it.getKey()) && !encontrado) {
                    encontrado = true;
                    parsLat = it.getValue().get(0).first;
                    parsLong = it.getValue().get(0).second;
                }
            }
        }
        if(kms==0 && isCheckedSw){
            String distritoUbi = mSharedPreferences.getString("distritoUbicacion","");
            distRadio.add(distritoUbi);
            mSub.add(NetworkUtil.getRetrofit().getCountAlertasDistrito(distritoUbi, true, cat)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        }
        else {

            Iterator<Map.Entry<String, ArrayList<Pair<Double, Double>>>> iterator = distCoord.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, ArrayList<Pair<Double, Double>>> it = iterator.next();
                Double lat = it.getValue().get(0).first;
                Double longi = it.getValue().get(0).second;
                //Double var = Math.sqrt( (Math.pow(parsLat-lat,2) + (Math.pow(parsLong-longi, 2))));
                Double var = Radio.distanciaCoord(parsLat, parsLong, lat, longi);
                if ((var <= kms) || distritoConf.equals("Todos")) {
                    if (!it.getKey().equals("Todos")) {
                        distRadio.add(it.getKey());
                        mSub.add(NetworkUtil.getRetrofit().getCountAlertasDistrito(it.getKey(), true, cat)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                                .subscribe(this::handleResponse, this::handleError));
                    }
                }
            }
        }

        circle = map.addCircle(new CircleOptions()
                .center(new LatLng(parsLat, parsLong))
                .radius(kms*1000)
                .strokeColor(Color.BLUE)
                .fillColor(0x220000FF)
                .strokeWidth(5));

        // Set a listener for marker click.
        map.setOnInfoWindowClickListener(this);
        CameraPosition camera = CameraPosition.builder().target(circle.getCenter()).zoom(12).bearing(0).build();
        map.moveCamera(CameraUpdateFactory.newCameraPosition(camera));
    }

    private void handleResponse(JsonArray pair) {
        JsonObject objeto;
        String distrito="";
        int total=0;
        for(JsonElement obj: pair){
            objeto  = obj.getAsJsonObject();
            distrito = objeto.get("_id").getAsString();
            total = objeto.get("total").getAsInt();
        }
        if(!distrito.equals("")) {
            if (contador < distRadio.size()) {
                Pair<String, Integer> p = new Pair<>(distrito, total);
                markerDistrito.add(p);
                contador++;
            }
        }else{
                contador++;
        }
        if (contador == distRadio.size()) {
            for (int i = 0; i < markerDistrito.size(); i++) {
                //Añadir marcador al mapa
                Pair<String, Integer> disCount = markerDistrito.get(i);
                String dis = disCount.first;
                ArrayList<Pair<Double, Double>> arrayPar = distCoord.get(dis);
                Pair<Double, Double> par = arrayPar.get(0);
                map.addMarker(new MarkerOptions().position(new LatLng(par.first, par.second)).title(dis)
                        .snippet("Se han encontrado " + disCount.second + " alertas"));
            }

        }
    }

    private void handleError(Throwable error) {
        Log.d("Marcador","ERRRRRRRR Error !");
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        //editor.putString(Constants.TOKEN,response.getToken())
        editor.putString("distritoMapa", marker.getTitle());
        editor.putBoolean("vieneMapa", true);
        editor.apply();

        getFragmentManager().beginTransaction()
                .replace(R.id.mapa_frame, new ListaAlertas())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
