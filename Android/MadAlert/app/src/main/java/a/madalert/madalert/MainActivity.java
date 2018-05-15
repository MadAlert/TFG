package a.madalert.madalert;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements DistritosFragmento.OnFragmentInteractionListener,
        SeleccionDistritoFragmento.OnFragmentInteractionListener,
        MapaFragmento.OnFragmentInteractionListener,
        MostrarMapa.OnFragmentInteractionListener,
        ListaAlertas.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;
    private String latitud;
    private String longitud;
    private boolean primeraVez;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout =(TabLayout)findViewById(R.id.tabs);
        ViewPager pager = (ViewPager)findViewById(R.id.viewpager);

        TabpagerAdapter tabpagerAdapter = new TabpagerAdapter(getSupportFragmentManager());
        pager.setAdapter(tabpagerAdapter);
        tabLayout.setupWithViewPager(pager);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        primeraVez =true;
        // Para la ubicacion
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        }
        else{
            locationStart();
        }
    }

    private void locationStart(){
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setMainActivity(this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(!gpsEnabled){
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }

        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        if(requestCode == 1000){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                locationStart();
                return;
            }
        }
    }

    public void setLocation(Location loc){
        if(loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0){
            try{
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
                if(!list.isEmpty()){
                    Address DirCalle = list.get(0);
                    Log.d("calle", DirCalle.getAddressLine(0));
                    latitud = String.valueOf(DirCalle.getLatitude());
                    longitud = String.valueOf(DirCalle.getLongitude());
                    mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    editor = mSharedPreferences.edit(); // para guardar las configuraciones
                    editor.putString("latitud", latitud);
                    editor.putString("longitud", longitud);
                    editor.apply();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

       int id = item.getItemId();

       switch (id) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //Fragment fragment = null;
        //Class fragmentClass = null;

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_configuracion) {
            startActivity(new Intent(getApplicationContext(), ConfigActivity.class));
        } else if (id == R.id.nav_contacto) {
            startActivity(new Intent(getApplicationContext(), ContactActivity.class));
        } else if (id == R.id.nav_soporte) {
            startActivity(new Intent(getApplicationContext(), SoporteActivity.class));
            //fragmentClass = SoporteFragmento.class;
        } else if (id == R.id.nav_aboutus) {
            startActivity(new Intent(getApplicationContext(), AboutUsActivity.class));
        } else if (id == R.id.nav_faq) {
            //fragmentClass = FaqFragmento.class;
        }
       /* try {
           // fragment = (Fragment) fragmentClass.newInstance();
        }
        catch (Exception e){
            e.printStackTrace();
        }*/


        //setTitle(item.getTitle());
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    private double distanciaCoordenadas(double latitudNueva, double longitudNueva){
        double distancia = 0;
        double radioTierra = 6371;//en kilómetros
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String strLatitudAntigua = mSharedPreferences.getString("latitud","");
        String strLongitudAntigua = mSharedPreferences.getString("longitud","");

        if(strLatitudAntigua!="" && strLongitudAntigua!=""){
            double latitudAntigua = Double.parseDouble(strLatitudAntigua);
            double longitudAntigua = Double.parseDouble(strLongitudAntigua);
            double dLat = Math.toRadians(latitudAntigua - latitudNueva);
            double dLng = Math.toRadians(longitudAntigua - longitudNueva);
            double sindLat = Math.sin(dLat / 2);
            double sindLng = Math.sin(dLng / 2);
            double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                    * Math.cos(Math.toRadians(latitudAntigua)) * Math.cos(Math.toRadians(latitudNueva));
            double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
            distancia = radioTierra * va2;
        }
        return distancia;
    }


    // Aqui empieza la clase Localizacion
    public class Localizacion implements LocationListener {
        MainActivity mainActivity;

        public MainActivity getMainActivity(){
            return mainActivity;
        }

        public void setMainActivity(MainActivity mainActivity){
            this.mainActivity = mainActivity;
        }

        @Override
        public void onLocationChanged(Location location) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas debido a la deteccion de un cambio de ubicacion
            latitud = String.valueOf(location.getLatitude());
            longitud = String.valueOf(location.getLongitude());

            if(distanciaCoordenadas(Double.parseDouble(latitud),Double.parseDouble(longitud)) >= 50  || primeraVez) {
                if(primeraVez){
                    primeraVez = false;
                }
                mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                editor = mSharedPreferences.edit(); // para guardar las configuraciones
                editor.putString("latitud", latitud);
                editor.putString("longitud", longitud);
                editor.apply();
            }
            else{
                Log.d("l", "hollaaaaaa");
            }
           // Log.d("coordenada_lat", String.valueOf(lat));
            //Log.d("coordenada_long", String.valueOf(longi));

            this.mainActivity.setLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status){
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }

    }
}




