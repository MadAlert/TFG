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
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.util.ArrayList;

import a.madalert.madalert.Adapter.GridViewAdapter;
import io.reactivex.disposables.CompositeDisposable;

public class ConfigActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    private ArrayList<String> selectedStrings;
    private static final String[] categorias = new String[]{
            "Todas", "Desastres y accidentes", "Terrorismo", "Criminalidad",
            "Tráfico", "Eventos", "Transporte público", "Contaminación"};

    private GridView gridView;
    private SharedPreferences mSharedPreferences;
    private CompositeDisposable mSubscriptions;

    private SeekBar seekBar;
    private TextView muestrKm;
    private Switch swUbi;
    private Spinner spinner;
    private int km;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        mSubscriptions = new CompositeDisposable();
        initSharedPreferences();
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        seekBar = (SeekBar) findViewById(R.id.seekBarRadio);
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

        gridView = (GridView) findViewById(R.id.grid);

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

        swUbi = (Switch) findViewById(R.id.switchUbi);

        if(swUbi != null)
            swUbi.setOnCheckedChangeListener(this);

        // Guarda los cambios realizados

        editor.putString("swUbi", swUbi.toString());
        editor.putString("distrito",  "lo que queda");
        editor.apply();

    }

    private void initSharedPreferences() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        km = mSharedPreferences.getInt("km", 0);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        Toast.makeText(this, "La ubicación está " + (isChecked ? "activada" : "desactivada"),
                Toast.LENGTH_SHORT).show();
        spinner = (Spinner) findViewById(R.id.spinnerDistritos);
        if(isChecked) {
            spinner.setVisibility(View.INVISIBLE);
        } else {
            spinner.setVisibility(View.VISIBLE);
        }
    }
}
