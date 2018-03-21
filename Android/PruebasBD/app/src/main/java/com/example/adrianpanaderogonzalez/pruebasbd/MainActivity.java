package com.example.adrianpanaderogonzalez.pruebasbd;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mResult = (TextView) findViewById(R.id.tv_result);

        //Hacer petición GET
        new GetDataTask().execute("http://192.168.1.53:1000/api/estadisticas");

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

    class GetDataTask extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Cargando datos...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            StringBuilder result = new StringBuilder();

            //Inicializa y configura la peticion, de conexion al servidor
            try {
                URL url = new URL(params[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000 /*miliseg*/);
                urlConnection.setConnectTimeout(10000 /*miliseg*/);
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-Type", "application/json"); //añadir cabecera
                urlConnection.connect();


                //Leer los datos devueltos por el servidor
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line).append("\n");
                }

            } catch (IOException ex) {
                return "Error Conexion!";
            }

            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            //añade los datos devueltos a textView
            mResult.setText(result);

            //Cancela el progreso del dialogo
            if(progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    }

    class PostDataTask extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Insertando datos...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                return postData(params[0]);
            } catch (IOException ex) {
                return "Error conexion!";
            } catch (JSONException ex) {
                return "Data Invalid !";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            mResult.setText(result);

            if(progressDialog != null) {
                progressDialog.dismiss();
            }
        }

        private String postData(String urlPath) throws IOException, JSONException {

            StringBuilder result = new StringBuilder();
            BufferedWriter bufferedWriter = null;
            BufferedReader bufferedReader = null;

            try {
                //Crea datos y los manda al servidor
                JSONObject dataToSend = new JSONObject();
                dataToSend.put("alertas", "Requisadas dos pistolas de fogueo y munición");
                dataToSend.put("fecha", "2018-02-21T18:20:00Z");
                dataToSend.put("url", "https://www.madridiario.es/453543/pistolas-fogueo-municion-navajas-arganzuela");
                dataToSend.put("distrito", "Arganzuela");
                dataToSend.put("categoria", "Contaminación");
                dataToSend.put("fuente", "madridDiario");

                //Inicializar y configurar la petición, despues se conecta al servidor
                URL url = new URL(urlPath);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000 /*miliseg*/);
                urlConnection.setConnectTimeout(10000 /*miliseg*/);
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true); //activada salida
                urlConnection.setRequestProperty("Content-Type", "application/json"); //añadir cabecera
                urlConnection.connect();

                //Escribe datos en el servidor
                OutputStream outputStream = urlConnection.getOutputStream();
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                bufferedWriter.write(dataToSend.toString());
                bufferedWriter.flush();

                //Lee datos del servidor
                InputStream inputStream = urlConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line).append("\n");
                }
            } finally {
                if(bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            }

            return result.toString();
        }
    }

    class PutDataTask extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Actualizando datos...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                return putData(params[0]);
            } catch (IOException ex) {
                return "Error conexión";
            } catch (JSONException ex) {
                return "Datos invalidos";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            mResult.setText(result);

            if(progressDialog != null) {
                progressDialog.dismiss();
            }
        }

        private String putData(String urlPath) throws IOException, JSONException {

            BufferedWriter bufferedWriter = null;
            String result = null;

            try {
                //Crear datos a actualizar
                JSONObject dataToSend = new JSONObject();
                dataToSend.put("alertas", "Requisadas dos pistolas de fogueo y munición");
                dataToSend.put("fecha", "2018-02-21T18:20:00Z");
                dataToSend.put("url", "https://www.madridiario.es/453543/pistolas-fogueo-municion-navajas-arganzuela");
                dataToSend.put("distrito", "Arganzuela");
                dataToSend.put("categoria", "Criminalidad");
                dataToSend.put("fuente", "madridDiario");

                //Inicializar y configurar la petición, despues se conecta al servidor
                URL url = new URL(urlPath);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000 /*miliseg*/);
                urlConnection.setConnectTimeout(10000 /*miliseg*/);
                urlConnection.setRequestMethod("PUT");
                urlConnection.setDoOutput(true); //activada salida
                urlConnection.setRequestProperty("Content-Type", "application/json"); //añadir cabecera
                urlConnection.connect();

                //Escribe datos en el servidor
                OutputStream outputStream = urlConnection.getOutputStream();
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                bufferedWriter.write(dataToSend.toString());
                bufferedWriter.flush();

                //Comprueba si la actualizacion ha funcionado o no
                if (urlConnection.getResponseCode() == 200) {
                    return "Actualización completada !";
                } else {
                    return "Actualización fallida";
                }
            } finally {
                if(bufferedWriter != null) {
                    bufferedWriter.close();
                }
            }

        }
    }

    class DeleteDataTask extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Borrando datos...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                return deleteData(params[0]);
            } catch (IOException ex) {
                return "Error de conexión";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            mResult.setText(result);

            if(progressDialog != null) {
                progressDialog.dismiss();
            }
        }

        private String deleteData(String urlPath) throws  IOException {

            String result = null;

            //Inicializar y configurar la petición, despues se conecta al servidor
            URL url = new URL(urlPath);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /*miliseg*/);
            urlConnection.setConnectTimeout(10000 /*miliseg*/);
            urlConnection.setRequestMethod("DELETE");
            urlConnection.setDoOutput(true); //activada salida
            urlConnection.setRequestProperty("Content-Type", "application/json"); //añadir cabecera
            urlConnection.connect();

            if(urlConnection.getResponseCode() == 204) {
                result = "Borrado correctamente !";
            } else {
                result = "Borrado fallido";
            }

            return result;
        }
    }
}
