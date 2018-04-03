package com.example.adrianpanaderogonzalez.pruebasbd;

import com.example.adrianpanaderogonzalez.pruebasbd.fragmento;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.UnknownHostException;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private TextView mResult;
    private Button but;
    private Spinner spinner;
    private CompositeDisposable mSubscriptions;
    private fragmento f;
    private Class fragmentClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (f == null) {

            f = new fragmento();
        }
        getFragmentManager().beginTransaction().replace(R.id.ventanaFragmento,f,fragmento.TAG).commit();





       /* mSubscriptions = new CompositeDisposable();
        mResult = (TextView) findViewById(R.id.tv_result);
        but = (Button) findViewById(R.id.btn);
        spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.puta, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view,
            int pos, long id) {
                // An item was selected. You can retrieve the selected item using
                parent.getItemAtPosition(pos);

            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });*/

        //Hacer petición GET
        //new GetDataTask().execute("http://192.168.1.207:1000/api/alertas");

        //Hacer petición POST
        //new PostDataTask().execute("http://192.168.1.53:1000/api/alertas");

        //Hacer petición PUT
        //new PutDataTask().execute("http://192.168.1.53:1000/api/alertas/5ab18c7da104795fe0c508da");

        //Hacer petición Delete
        //new DeleteDataTask().execute("http://192.168.1.53:1000/api/alertas/5ab18c7da104795fe0c508da");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onButtonClick(View v) throws UnknownHostException {

        Alertas alertas= new Alertas();

        String selec = spinner.getSelectedItem().toString();

        //GetAlertasAsyncTask tsk= new GetAlertasAsyncTask();
        //tsk.execute(alertas);


        //Intent i = new Intent(this, VerAlertasActivity.class);
        //startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
