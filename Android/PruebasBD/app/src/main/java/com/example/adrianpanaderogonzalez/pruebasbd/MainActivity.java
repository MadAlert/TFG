package com.example.adrianpanaderogonzalez.pruebasbd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.adrianpanaderogonzalez.pruebasbd.dummy.DummyContent;

import java.net.UnknownHostException;


import io.reactivex.disposables.CompositeDisposable;


public class MainActivity extends AppCompatActivity implements FragmentoLista.OnListFragmentInteractionListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    private TextView mResult;
    private Button but;
    private Spinner spinner;
    private CompositeDisposable mSubscriptions;
    private FragmentoInicio f;
    private Class fragmentClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (f == null) {

            f = new FragmentoInicio();
        }
        getFragmentManager().beginTransaction().replace(R.id.ventanaFragmento,f, FragmentoInicio.TAG).commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // no se usa ya
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

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
