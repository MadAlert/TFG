package a.madalert.madalert;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class AddAlertaActivity extends AppCompatActivity {

    private CompositeDisposable mSub;
    private EditText nombre;
    private EditText email;
    private Spinner categoria;
    private Spinner distrito;
    private EditText alerta;
    private Button boton;

    private String dist;
    private String cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alerta);
        mSub = new CompositeDisposable();

        nombre = findViewById(R.id.editText);
        email = findViewById(R.id.editText2);
        alerta = findViewById(R.id.editText3);
        boton = findViewById(R.id.button);

        categoria = findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.categorias_array , android.R.layout.simple_spinner_item);

        categoria.setAdapter(adapter);
        categoria.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {

                        cat = (String) categoria.getSelectedItem();
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }

                }
        );

        distrito = findViewById(R.id.spinner2);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(
                this, R.array.distritos_array , android.R.layout.simple_spinner_item);

        distrito.setAdapter(adapter2);
        distrito.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {

                        dist = (String) distrito.getSelectedItem();
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }

                }
        );

        boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                postAlerta();
            }
        });

    }

    private void postAlerta() {
        String alertaP = alerta.getText().toString();
        String distritoP = distrito.getSelectedItem().toString();
        String fuenteP = nombre.getText().toString();
        String categoriaP = categoria.getSelectedItem().toString();

        if(validate()) {

            mSub.add(NetworkUtil.getRetrofit().postAlerta(alertaP, distritoP, fuenteP, categoriaP)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        }
    }

    private void handleError(Throwable throwable) {
    }

    private void handleResponse(Alertas alertas) {
        Snackbar.make(findViewById(R.id.activity_add_alerta),"Se ha añadido correctamente",Snackbar.LENGTH_LONG).show();
        //Toast.makeText(this, "Se añadio la alertita", Toast.LENGTH_LONG).show();

        /*FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.alertas_fragmento, new AlertasFragmento());
        ft.commit();*/
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public boolean validate() {
        boolean valido = true;
        if(alerta.getText().toString().isEmpty()) {
            valido = false;
            alerta.setError("Introduce la alerta");
        }
        if(nombre.getText().toString().isEmpty()) {
            valido=false;
            nombre.setError("Introduce tu nombre");
        }
        if(email.getText().toString().isEmpty()) {
            valido=false;
            email.setError("Introduce tu email");
        }
        return valido;
    }
}
