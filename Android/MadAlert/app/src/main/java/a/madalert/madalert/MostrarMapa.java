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
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private SharedPreferences.Editor editor;
    private String latitud;
    private String longitud;
    private boolean isCheckedSw;
    private String distritoConf;
    private ArrayList<String> distRadio;
    private String mListaCat;
    private Integer count;
    private int km;
    private HashMap<String, Pair<Double, Double>> distCoord;
    private HashMap<String, Pair<Double, Double>> distritosValidos;
    private List<Integer> alertas;

    private OnFragmentInteractionListener mListener;

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
        initCoord();

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
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
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

        int kms = km;

        Iterator<Map.Entry<String, Pair<Double, Double>>> iterator = distCoord.entrySet().iterator();

        if(isCheckedSw) {
            map.setMyLocationEnabled(true);

            parsLat = Double.parseDouble(latitud);
            parsLong = Double.parseDouble(longitud);

            distritosValidos = new HashMap<String, Pair<Double, Double>>();
            alertas = new ArrayList<>();
            int i = 0;
            while (iterator.hasNext()){
                Map.Entry<String, Pair<Double, Double>> it = iterator.next();
                Double lat = it.getValue().first;
                Double longi = it.getValue().second;
                //Double var = Math.sqrt( (Math.pow(parsLat-lat,2) + (Math.pow(parsLong-longi, 2))));
                Double var = distanciaCoord(parsLat, parsLong, lat, longi);
                if(var <= kms){
                    distRadio.add(it.getKey());
                    String cat = mListaCat;
                    if(mListaCat==""){
                        cat = "Todas";
                    }
                   mSub.add(NetworkUtil.getRetrofit().getCountAlertasDistrito(it.getKey(),true,cat)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                            .subscribe(this::handleResponse, this::handleError));
                    distritosValidos.put(it.getKey(),new Pair(lat,longi)); //nuevo de nerea pero no vale pa na
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, longi)).title(it.getKey()).snippet("Se han encontrado " + count + " alertas"));

                }
            }
            Iterator<Map.Entry<String, Pair<Double, Double>>> iterator2 = distritosValidos.entrySet().iterator();
        }
        else {
            boolean encontrado = false;

            while (iterator.hasNext()) {
                Map.Entry<String, Pair<Double, Double>> it = iterator.next();
                if (distritoConf.equals(it.getKey()) && !encontrado) {
                    encontrado = true;
                    parsLat = it.getValue().first;
                    parsLong = it.getValue().second;
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(parsLat, parsLong)).title(it.getKey()).snippet("Se han encontrado " + count + " alertas"));
                }
                Double lat = it.getValue().first;
                Double longi = it.getValue().second;
                //Double var = Math.sqrt( (Math.pow(parsLat-lat,2) + (Math.pow(parsLong-longi, 2))));
                Double var = distanciaCoord(parsLat, parsLong, lat, longi);
                if(var <= kms && !distritoConf.equals(it.getKey())){
                    distRadio.add(it.getKey());
                    String cat = mListaCat;
                    if(mListaCat==""){
                        cat = "Todas";
                    }
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, longi)).title(it.getKey()).snippet("Se han encontrado " + count + " alertas"));
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

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(camera));
    }

    private void handleResponse(Integer integer) {
        Log.d("nerePUTAAMA", integer.toString());
        count = integer;
        alertas.add(integer); //Nuevo pero no vale pana
    }


    private void handleError(Throwable error) {
        Log.d("adritonto","ERRRRRRRR Error !");
    }

    public void initCoord() {
        distCoord = new HashMap<String, Pair<Double, Double>>();
        distCoord.put("Arganzuela", new Pair<>(40.400861, -3.699350));
        distCoord.put("Barajas", new Pair<>(40.4839402, -3.5701402));
        distCoord.put("Carabanchel", new Pair<>(40.381607, -3.735203));
        distCoord.put("Centro", new Pair<>(40.4169416, -3.7083759));
        distCoord.put("Chamartín", new Pair<>(40.460367, -3.676567));
        distCoord.put("Chamberí", new Pair<>(40.438656, -3.704180));
        distCoord.put("Ciudad Lineal", new Pair<>(40.455531, -3.656119));
        distCoord.put("Fuencarral-El Pardo", new Pair<>(40.494289, -3.693477));
        distCoord.put("Hortaleza", new Pair<>(40.485152, -3.634796));
        distCoord.put("Latina", new Pair<>(40.387812, -3.773530));
        distCoord.put("Moncloa-Aravaca", new Pair<>(40.443568, -3.742829));
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
