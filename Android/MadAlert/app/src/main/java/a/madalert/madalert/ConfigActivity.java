package a.madalert.madalert;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import a.madalert.madalert.Adapter.GridViewAdapter;
import io.reactivex.disposables.CompositeDisposable;

public class ConfigActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private ArrayList<String> selectedStrings;
    private static final String[] categorias = new String[]{
            "Todas", "Desastres y accidentes", "Terrorismo", "Criminalidad",
            "Tráfico", "Eventos", "Transporte público", "Contaminación"};

    private SharedPreferences mSharedPreferences;

    private TextView muestrKm;
    private Spinner spinner;
    private int km;
    private SharedPreferences.Editor editor;
    private Switch swUbi;
    private boolean isCheckedSw;
    private ArrayList<View> listaViews;
    private String distrito; // ahora mismo no está siendo usado pero lo será en un futuro
    private int pos;
    private int MY_PERMISSIONS_REQUEST_LOCATION =1;
    private AlertDialog alert;

    private Location location;
    private LocationManager locationManager;
    private LocationListener locationListener;

    private String categoriasArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        CompositeDisposable mSubscriptions = new CompositeDisposable();
        initSharedPreferences();
        editor = mSharedPreferences.edit(); // para guardar las configuraciones

        initSeekBar();
        initGridView();
        initSwitch();

    }

    private void initSharedPreferences() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        km = mSharedPreferences.getInt("km", -1);
        isCheckedSw = mSharedPreferences.getBoolean("isCheckedSw", true);
       // distrito = mSharedPreferences.getString("distrito", "");
        pos = mSharedPreferences.getInt("posicion", -1);


        // falta guardar las categorias, recuerda lo pasos: put, get y set. QUE NO SE TE OLVIDE EL SET GONZA COÑO
    }

    private void initSwitch(){
        swUbi = (Switch) findViewById(R.id.switchUbi);
        swUbi.setChecked(isCheckedSw);

        if(swUbi != null)
            swUbi.setOnCheckedChangeListener(this);

        initSpinner();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        //Toast.makeText(this, "La ubicación está " + (isChecked ? "activada" : "desactivada"),
               // Toast.LENGTH_SHORT).show();

        isCheckedSw = isChecked;
        editor.putBoolean("isCheckedSw", isCheckedSw);
        Log.d("BOOL", String.valueOf(isChecked));
        editor.apply();

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        // Here, thisActivity is the current activity
        if(isCheckedSw) {
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
           // Verificamos si el GPS esta encendido o no:
            assert locationManager != null;
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            {
                localizacion();
                AlertNoGps();
            }


            // Para permitir que la app acceda a la ubicacion del dispositivo
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {

                } else {

                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_LOCATION);

                }
            }
        }
        initSpinner();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {

                }
                return;
            }
        }
    }

    private void initSeekBar(){
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBarRadio);
        seekBar.setProgress(km);

        // obtiene el valor de los km
        muestrKm = findViewById(R.id.tvKm);

        muestrKm.setText(km + " km");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                muestrKm.setText(progress + " km");
                km = progress;
                editor.putInt("km", km);
                editor.apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initGridView(){
        GridView gridView = (GridView) findViewById(R.id.grid);

        final GridViewAdapter adapter2 = new GridViewAdapter(categorias, this);
        gridView.setAdapter(adapter2);

       /* for (int i = categoriasArray.size() - 1; i >= 0; i--) {
            adapter2.selectedPositions.add(i);
            ((GridItemView) listaViews.get(categoriasArray.get(i))).display(true);
            categoriasArray.add(categoriasArray.get(i));
            Log.d("TAGG", listaViews.get(i).toString());
        }*/

        selectedStrings = new ArrayList<>();
        listaViews = new ArrayList<>();

        gridView.setOnItemClickListener((parent, v1, position, id) -> {
            int selectedIndex = adapter2.selectedPositions.indexOf(position);
            if(position == 0 && id == 0 && !(selectedIndex > -1)){ //Se ha selecionado la opcion TODAS
                for(int i= listaViews.size()-1; i >= 0; i--){
                    ((GridItemView) listaViews.get(i)).display(false);
                    listaViews.remove(i);
                    selectedStrings.remove(i);
                    adapter2.selectedPositions.remove(i);
                }
            }
            if (selectedIndex > -1) {
                adapter2.selectedPositions.remove(selectedIndex);
                ((GridItemView) v1).display(false);
                selectedStrings.remove((String) parent.getItemAtPosition(position));
                listaViews.remove(v1);
            } else {
                if(adapter2.selectedPositions.contains(0) && id != 0) { //Esta todas
                    ((GridItemView) listaViews.get(0)).display(false);
                    selectedStrings.remove(0);
                    listaViews.remove(0);
                    adapter2.selectedPositions.remove(0);
                }
                adapter2.selectedPositions.add(position);
                ((GridItemView) v1).display(true);
                listaViews.add(v1);
                selectedStrings.add((String) parent.getItemAtPosition(position));
            }
        });

        if(selectedStrings.size()==1 && selectedStrings.get(0)=="Todas"){
            editor.putString("hayCategorias", "0");
        }
        else{
            categoriasArray = new String();
            for(int i=0; i< selectedStrings.size();i++){
                categoriasArray=categoriasArray+selectedStrings.get(i);
                if(i < selectedStrings.size()-1){
                    categoriasArray=categoriasArray+",";
                }
            }
            editor.putString("hayCategorias", categoriasArray); //Hay que pasar el array de string por aqui
        }
       /* else{
            categoriasArray = new ArrayList<>();
            for(int i=0; i< selectedStrings.size();i++){
                categoriasArray.set(i, Integer.valueOf(categoriasArray + selectedStrings.get(i)));
                if(i < selectedStrings.size()-1){
                    categoriasArray.set(i, Integer.valueOf(categoriasArray.get(i) + ","));
                }
            }
            editor.putString("hayCategorias", categoriasArray.toString()); //Hay que pasar el array de string por aqui
        }*/

        editor.apply();

    }

    private void initSpinner(){
        spinner = (Spinner) findViewById(R.id.spinnerDistritos);
        spinner.setSelection(pos);

        if(isCheckedSw) {
            spinner.setVisibility(View.INVISIBLE);
        } else {
            spinner.setVisibility(View.VISIBLE);
        }

        // Esto sirve para que se quede guardado el distrito seleccionado
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                //distrito = spinner.toString(); ESTO HABRÁ QUE GUARDARLO COMO STRING TB CUANDO HAGA LA CONSULTA..
                pos = spinner.getSelectedItemPosition();
                Log.d("TAG POS", String.valueOf(pos));
                //editor.putString("distrito",  distrito);
                editor.putInt("posicion", pos);
                editor.apply();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });



    }

    private void AlertNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("El sistema GPS esta desactivado, ¿Desea activarlo?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                        isCheckedSw = false;
                        editor.putBoolean("isCheckedSw", isCheckedSw);
                        editor.apply();
                        swUbi.setChecked(isCheckedSw);
                    }
                });
        alert = builder.create();
        alert.show();
    }

    private void localizacion(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            } else {
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        } else {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }


        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                isCheckedSw = false;
                swUbi.setChecked(isCheckedSw);
                editor.putBoolean("isCheckedSw", isCheckedSw);
                editor.apply();
            }
        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);

    }
}
