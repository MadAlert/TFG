package a.madalert.madalert;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.disposables.CompositeDisposable;

public class ConfigActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private ArrayList<String> categorias;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;

    private TextView muestrKm;
    private Spinner spinner;
    private int km;
    private Switch swUbi;
    private boolean isCheckedSw;
    private String distritoConf;
    private int pos; // posicion del distrito
    private AlertDialog alert;

    private LocationManager locationManager;
    private LocationListener locationListener;

    private CheckBox todas;
    private CheckBox dya;
    private CheckBox terrorismo;
    private CheckBox crimi;
    private CheckBox traf;
    private CheckBox event;
    private CheckBox transp;
    private CheckBox cont;

    private boolean todasBool;
    private boolean dyaBool;
    private boolean terrBool;
    private boolean crimiBool;
    private boolean trafBool;
    private boolean eventosBool;
    private boolean transpBool;
    private boolean contBool;

    private String latitud;
    private String longitud;

    private static final String LOGTAG = "android-localizacion";

    private static final int PETICION_PERMISO_LOCALIZACION = 101;
    private static final int PETICION_CONFIG_UBICACION = 201;

    private GoogleApiClient apiClient;
    private LocationRequest locRequest;


    private String listaCategoria;

    public ConfigActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        CompositeDisposable mSubscriptions = new CompositeDisposable();
        initSharedPreferences();
        editor = mSharedPreferences.edit(); // para guardar las configuraciones

        // Para quitar el mensaje de 'bienvenida'
        editor.putBoolean("primeraVez", false);
        editor.apply();

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
        listaCategoria = mSharedPreferences.getString("listaCat", "");

        // necesario para el algoritmo del radio
        latitud = mSharedPreferences.getString("latitud", "");
        longitud = mSharedPreferences.getString("longitud", "");

        Log.d("coordenada_lat", String.valueOf(latitud));
        Log.d("coordenada_long", String.valueOf(longitud));

    }

    private void initSwitch() {
        swUbi = (Switch) findViewById(R.id.switchUbi);
        swUbi.setChecked(isCheckedSw);

        if (swUbi != null)
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
        if (isCheckedSw) {
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            // Verificamos si el GPS esta encendido o no:
            assert locationManager != null;
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
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

                    int MY_PERMISSIONS_REQUEST_LOCATION = 1;
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_LOCATION);

                }
            }
        }
        initSpinner();
    }

    private void initSeekBar() {
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

    private void initListCheckBox() {
        LinearLayout listCheckBox = (LinearLayout) findViewById(R.id.categorias);
        todas = (CheckBox) findViewById(R.id.todas);
        dya = (CheckBox) findViewById(R.id.dya);
        terrorismo = (CheckBox) findViewById(R.id.terrorismo);
        crimi = (CheckBox) findViewById(R.id.criminalidad);
        traf = (CheckBox) findViewById(R.id.trafico);
        event = (CheckBox) findViewById(R.id.eventos);
        transp = (CheckBox) findViewById(R.id.transp);
        cont = (CheckBox) findViewById(R.id.contaminacion);

        todas.setChecked(todasBool);
        dya.setChecked(dyaBool);
        terrorismo.setChecked(terrBool);
        crimi.setChecked(crimiBool);
        traf.setChecked(trafBool);
        event.setChecked(eventosBool);
        transp.setChecked(transpBool);
        cont.setChecked(contBool);

        anyChecked();
        //categorias.add(listaCategoria);
        categorias = new ArrayList<String>(Arrays.asList(listaCategoria.split(",")));


        todas.setOnCheckedChangeListener((compoundButton, b) -> {
            todasBool = b;
            if (todasBool) {
                categorias.clear();
                todasCheck();
            }

            editor.putString("listaCat", mytoString(categorias, ","));
            listaCategoria = mytoString(categorias, ",");
            editor.putBoolean("todas", b);
            editor.apply();
            anyChecked();
        });

        dya.setOnCheckedChangeListener((compoundButton, b) -> {
            dyaBool = b;
            if (dyaBool)
                categorias.add("Desastres y accidentes");
            else
                categorias.remove("Desastres y accidentes");

            editor.putString("listaCat", mytoString(categorias, ","));
            listaCategoria = mytoString(categorias, ",");
            editor.putBoolean("dya", b);
            editor.apply();
            anyChecked();
        });

        terrorismo.setOnCheckedChangeListener((compoundButton, b) -> {
            terrBool = b;
            if (terrBool)
                categorias.add("Terrorismo");
            else
                categorias.remove("Terrorismo");
            //editor.putString("listaCat", categorias.toString());
            editor.putString("listaCat", mytoString(categorias, ","));
            listaCategoria = mytoString(categorias, ",");
            editor.putBoolean("terrorismo", b);
            editor.apply();
            anyChecked();
        });

        crimi.setOnCheckedChangeListener((compoundButton, b) -> {
            crimiBool = b;
            if (crimiBool)
                categorias.add("Criminalidad");
            else
                categorias.remove("Criminalidad");
            editor.putString("listaCat", mytoString(categorias, ","));
            listaCategoria = mytoString(categorias, ",");
            editor.putBoolean("crimi", b);
            editor.apply();
            anyChecked();
        });

        traf.setOnCheckedChangeListener((compoundButton, b) -> {
            trafBool = b;
            if (trafBool)
                categorias.add("Tráfico");
            else
                categorias.remove("Tráfico");
            editor.putString("listaCat", mytoString(categorias, ","));
            listaCategoria = mytoString(categorias, ",");
            editor.putBoolean("trafico", b);
            editor.apply();
            anyChecked();
        });

        event.setOnCheckedChangeListener((compoundButton, b) -> {
            eventosBool = b;
            if (eventosBool)
                categorias.add("Eventos");
            else
                categorias.remove("Eventos");
            editor.putString("listaCat", mytoString(categorias, ","));
            listaCategoria = mytoString(categorias, ",");
            editor.putBoolean("eventos", b);
            editor.apply();
            anyChecked();
        });

        transp.setOnCheckedChangeListener((compoundButton, b) -> {
            transpBool = b;
            if (transpBool)
                categorias.add("Transporte público");
            else
                categorias.remove("Transporte público");
            editor.putString("listaCat", mytoString(categorias, ","));
            listaCategoria = mytoString(categorias, ",");
            editor.putBoolean("transporte", b);
            editor.apply();
            anyChecked();
        });

        cont.setOnCheckedChangeListener((compoundButton, b) -> {
            contBool = b;
            if (contBool)
                categorias.add("Contaminación");
            else
                categorias.remove("Contaminación");
            editor.putString("listaCat", mytoString(categorias, ","));
            listaCategoria = mytoString(categorias, ",");
            editor.putBoolean("contaminacion", b);
            editor.apply();
            anyChecked();
        });

    }

    private void initSpinner() {
        spinner = (Spinner) findViewById(R.id.spinnerDistritos);
        spinner.setSelection(pos);

        if (isCheckedSw) {
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
                editor.putString("distritoConf", distritoConf);
                editor.putInt("posicion", pos);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

    private void localizacion() {

        Location location;
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

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

    }

    private static String mytoString(ArrayList<String> theAray, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < theAray.size(); i++) {
            if (i > 0) {
                sb.append(delimiter);
            }
            String item = theAray.get(i);
            sb.append(item);
        }
        return sb.toString();
    }

    private void anyChecked() {
        if (!dyaBool && !crimiBool && !transpBool && !trafBool && !contBool && !eventosBool && !terrBool) {
            todasBool = true;
            todas.setChecked(todasBool);
        }
        if (dyaBool || crimiBool || transpBool || trafBool || contBool || eventosBool || terrBool) {
            todasBool = false;
            todas.setChecked(todasBool);
        }
        if (dyaBool && crimiBool && transpBool && trafBool && contBool && eventosBool && terrBool) {
            todasBool = true;
            todas.setChecked(todasBool);
        }

    }

    private void todasCheck() {
        dyaBool = crimiBool = transpBool = trafBool = contBool = eventosBool = terrBool = false;
        todas.setChecked(todasBool);
        dya.setChecked(dyaBool);
        crimi.setChecked(crimiBool);
        transp.setChecked(transpBool);
        traf.setChecked(trafBool);
        cont.setChecked(contBool);
        event.setChecked(eventosBool);
        terrorismo.setChecked(terrBool);
    }

}
