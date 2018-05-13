package a.madalert.madalert;


import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapaFragmento extends Fragment implements
        GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap map;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;
    private String latitud;
    private String longitud;
    private int km;
    private HashMap<String, Pair<Double, Double>> distCoord;

    public MapaFragmento() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mapa, container, false);

        initSharedPreferences();
        initCoord();

        return v;
    }

    private void initSharedPreferences() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        latitud = mSharedPreferences.getString("latitud", "");
        longitud = mSharedPreferences.getString("longitud", "");
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
        map.setMyLocationEnabled(true);

        double latitud2 = Double.parseDouble(latitud);
        double longitud2 = Double.parseDouble(longitud);



        int kilometros = km * 1000;

        Log.d("kilometros", String.valueOf(kilometros));

        Circle circle = map.addCircle(new CircleOptions()
                .center(new LatLng(latitud2, longitud2))
                .radius(kilometros)
                .strokeColor(Color.BLUE)
                .fillColor(0x220000FF)
                .strokeWidth(5));


        Iterator<Map.Entry<String, Pair<Double, Double>>> iterator = distCoord.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Pair<Double, Double>> it = iterator.next();
            Double lat = it.getValue().first;
            Double longi = it.getValue().second;
            googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, longi)).title(it.getKey()).snippet("Se han encontrado 0 alertas")).setTag(0);
        }

        // Set a listener for marker click.
        map.setOnInfoWindowClickListener(this);

        CameraPosition camera = CameraPosition.builder().target(circle.getCenter()/*new LatLng(latitud2, longitud2)*/).zoom(12).bearing(0).build();

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(camera));
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


    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this.getActivity(), "Info window clicked",
                Toast.LENGTH_LONG).show();
    }
}
