package a.madalert.madalert;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
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
    private boolean isCheckedSw;
    private String distrito; // ahora mismo no está siendo usado pero lo será en un futuro
    private int pos;


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
        Switch swUbi = (Switch) findViewById(R.id.switchUbi);
        swUbi.setChecked(isCheckedSw);

        if(swUbi != null)
            swUbi.setOnCheckedChangeListener(this);

        initSpinner();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        Toast.makeText(this, "La ubicación está " + (isChecked ? "activada" : "desactivada"),
                Toast.LENGTH_SHORT).show();

        isCheckedSw = isChecked;
        editor.putBoolean("isCheckedSw", isCheckedSw);
        Log.d("BOOL", String.valueOf(isChecked));
        editor.apply();

        initSpinner();
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

        selectedStrings = new ArrayList<>();

        final GridViewAdapter adapter2 = new GridViewAdapter(categorias, this);
        gridView.setAdapter(adapter2);
        gridView.setOnItemClickListener((parent, v1, position, id) -> {
            int selectedIndex = adapter2.selectedPositions.indexOf(position);
            if (selectedIndex > -1) {
                adapter2.selectedPositions.remove(selectedIndex);
                ((GridItemView) v1).display(false);
                selectedStrings.remove((String) parent.getItemAtPosition(position));
            } else {
                adapter2.selectedPositions.add(position);
                ((GridItemView) v1).display(true);
                selectedStrings.add((String) parent.getItemAtPosition(position));
            }
        });
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
}
