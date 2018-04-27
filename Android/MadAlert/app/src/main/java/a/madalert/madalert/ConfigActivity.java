package a.madalert.madalert;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import io.reactivex.disposables.CompositeDisposable;

public class ConfigActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
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
    private String distritoConf; // ahora mismo no está siendo usado pero lo será en un futuro
    private int pos; // posicion del distrito
    private int MY_PERMISSIONS_REQUEST_LOCATION =1;
    private AlertDialog alert;

    private Location location;
    private LocationManager locationManager;
    private LocationListener locationListener;

    private LinearLayout listCheckBox;

    private boolean todasBool;
    private boolean dyaBool;
    private boolean terrBool;
    private boolean crimiBool;
    private boolean trafBool;
    private boolean eventosBool;
    private boolean transpBool;
    private boolean contBool;

    private List<Boolean> catBool;
    private String catList;

    public ConfigActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        CompositeDisposable mSubscriptions = new CompositeDisposable();
        initSharedPreferences();
        editor = mSharedPreferences.edit(); // para guardar las configuraciones

        initSeekBar();
        initListCheckBox();
        initSwitch();

    }

    private void initSharedPreferences() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        km = mSharedPreferences.getInt("km", -1);
        isCheckedSw = mSharedPreferences.getBoolean("isCheckedSw", false);
        pos = mSharedPreferences.getInt("posicion", -1);
        todasBool = mSharedPreferences.getBoolean("todas", false);
        dyaBool = mSharedPreferences.getBoolean("dya", false);
        terrBool = mSharedPreferences.getBoolean("terrorismo", false);
        crimiBool = mSharedPreferences.getBoolean("crimi", false);
        trafBool = mSharedPreferences.getBoolean("trafico", false);
        eventosBool = mSharedPreferences.getBoolean("eventos", false);
        transpBool = mSharedPreferences.getBoolean("transporte", false);
        contBool = mSharedPreferences.getBoolean("contaminacion", false);
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

    private void initListCheckBox(){
        listCheckBox = (LinearLayout) findViewById(R.id.categorias);
        CheckBox todas = (CheckBox) findViewById(R.id.todas);
        CheckBox dya = (CheckBox) findViewById(R.id.dya);
        CheckBox terrorismo = (CheckBox) findViewById(R.id.terrorismo);
        CheckBox crimi = (CheckBox) findViewById(R.id.criminalidad);
        CheckBox traf = (CheckBox) findViewById(R.id.trafico);
        CheckBox event = (CheckBox) findViewById(R.id.eventos);
        CheckBox transp = (CheckBox) findViewById(R.id.transp);
        CheckBox cont = (CheckBox) findViewById(R.id.contaminacion);

        todas.setChecked(todasBool);
        dya.setChecked(dyaBool);
        terrorismo.setChecked(terrBool);
        crimi.setChecked(crimiBool);
        traf.setChecked(trafBool);
        event.setChecked(eventosBool);
        transp.setChecked(transpBool);
        cont.setChecked(contBool);

        // Inicializa
        catBool = new List<Boolean>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<Boolean> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] ts) {
                return null;
            }

            @Override
            public boolean add(Boolean aBoolean) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends Boolean> collection) {
                return false;
            }

            @Override
            public boolean addAll(int i, @NonNull Collection<? extends Boolean> collection) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Boolean get(int i) {
                return null;
            }

            @Override
            public Boolean set(int i, Boolean aBoolean) {
                return null;
            }

            @Override
            public void add(int i, Boolean aBoolean) {

            }

            @Override
            public Boolean remove(int i) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<Boolean> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<Boolean> listIterator(int i) {
                return null;
            }

            @NonNull
            @Override
            public List<Boolean> subList(int i, int i1) {
                return null;
            }
        };
        for(int i = 0; i < 8; i++)
            catBool.set(i, false);

        todas.setOnCheckedChangeListener((compoundButton, b) -> {
            todasBool = b;
            catBool.set(0, todasBool);
            editor.putBoolean("todas", b);
            editor.apply();
        });

        dya.setOnCheckedChangeListener((compoundButton, b) -> {
            dyaBool = b;
            catBool.set(1, dyaBool);
            editor.putBoolean("dya", b);
            editor.apply();
        });

        terrorismo.setOnCheckedChangeListener((compoundButton, b) -> {
            terrBool = b;
            catBool.set(2, terrBool);
            editor.putBoolean("terrorismo", b);
            editor.apply();
        });

        crimi.setOnCheckedChangeListener((compoundButton, b) -> {
            crimiBool = b;
            catBool.set(3, crimiBool);
            editor.putBoolean("crimi", b);
            editor.apply();
        });

        traf.setOnCheckedChangeListener((compoundButton, b) -> {
            trafBool = b;
            catBool.set(4, trafBool);
            editor.putBoolean("trafico", b);
            editor.apply();
        });

        event.setOnCheckedChangeListener((compoundButton, b) -> {
            eventosBool = b;
            catBool.set(5, eventosBool);
            editor.putBoolean("eventos", b);
            editor.apply();
        });

        transp.setOnCheckedChangeListener((compoundButton, b) -> {
            transpBool = b;
            catBool.set(6, transpBool);
            editor.putBoolean("transporte", b);
            editor.apply();
        });

        cont.setOnCheckedChangeListener((compoundButton, b) -> {
            contBool = b;
            catBool.set(7, contBool);
            editor.putBoolean("contaminacion", b);
            editor.apply();
        });

        catList = new String();
        for(int i=0; i < categorias.length;i++){
            catList=catList+categorias[i];
            if(i < categorias.length-1){
                catList=catList+",";
            }
        }

        editor.putString("catLista", "");
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
                distritoConf = spinner.getSelectedItem().toString(); //ESTO HABRÁ QUE GUARDARLO COMO STRING TB CUANDO HAGA LA CONSULTA..
                pos = spinner.getSelectedItemPosition();
                Log.d("TAG POS", String.valueOf(pos));
                editor.putString("distritoConf",  distritoConf);
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
