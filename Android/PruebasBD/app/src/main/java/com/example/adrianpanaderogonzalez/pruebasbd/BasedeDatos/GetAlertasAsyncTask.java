package com.example.adrianpanaderogonzalez.pruebasbd.BasedeDatos;

import com.example.adrianpanaderogonzalez.pruebasbd.Alertas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.os.AsyncTask;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;


/**
 * Created by adrianpanaderogonzalez on 22/3/18.
 */

public class GetAlertasAsyncTask extends AsyncTask<Alertas, Void, ArrayList<Alertas>> {
    static BasicDBObject user = null;
    static String OriginalObject = "";
    static String server_output = null;
    static String temp_output = null;

    @Override
    protected ArrayList<Alertas> doInBackground(Alertas... arg0) {

        ArrayList<Alertas> alertas = new ArrayList<Alertas>();
        try
        {

            QueryBuilder qb = new QueryBuilder();
            URL url = new URL(qb.buildAlertasGetURL());
            HttpURLConnection conn = (HttpURLConnection) url
                    .openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            while ((temp_output = br.readLine()) != null) {
                server_output = temp_output;
            }

            // create a basic db list
            String mongoarray = "{ artificial_basicdb_list: " + server_output + "}";
            Object o = com.mongodb.util.JSON.parse(mongoarray);


            DBObject dbObj = (DBObject) o;
            BasicDBList contacts = (BasicDBList) dbObj.get("artificial_basicdb_list");

            for (Object obj : contacts) {
                DBObject userObj = (DBObject) obj;

                Alertas temp = new Alertas();
                temp.setAlertas(userObj.get("alerta").toString());
                temp.setFecha(userObj.get("fecha").toString());
                temp.setCategoria(userObj.get("categoria").toString());
                temp.setDistrito(userObj.get("distrito").toString());
                temp.setFuente(userObj.get("fuente").toString());
                temp.setUrl(userObj.get("url").toString());
                if(userObj.get("veridico") != null ){
                    temp.setVeridico(userObj.get("veridico").toString());
                }
                alertas.add(temp);

            }

        }catch (Exception e) {
            e.getMessage();
        }

        return alertas;
    }
}
