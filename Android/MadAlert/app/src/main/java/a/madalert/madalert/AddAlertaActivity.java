package a.madalert.madalert;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import a.madalert.madalert.Clasificador.Post;
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
    private SharedPreferences mSharedPreferences;
    private Boolean mSnack;

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
            try {
                if(parseoCategoria(categoriaValida()).equals(categoriaP)){
                    mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                    mSnack = true;
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putBoolean("snack",mSnack);
                    editor.apply();

                    mSub.add(NetworkUtil.getRetrofit().postAlerta(alertaP, distritoP, fuenteP, categoriaP)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                            .subscribe(this::handleResponse, this::handleError));
                }
                else{
                    Snackbar.make(findViewById(R.id.activity_add_alerta),"La categoria indicada es incorrecta",Snackbar.LENGTH_LONG).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleError(Throwable throwable) {
    }

    private void handleResponse(Alertas alertas) {

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

    public String categoriaValida() throws IOException, SAXException, ParserConfigurationException {
        String api = "http://api.meaningcloud.com/class-1.1";
        String key = "05ed9a7c754aeee5d5f99470a756a5f8";
        String txt = alerta.getText().toString();
        String model = "news";
        Post post = new Post (api);
        post.addParameter("key", key);
        post.addParameter("txt", txt);
        post.addParameter("model", model);
        post.addParameter("of", "xml");
        String response = post.getResponse();

        // Prints the specific fields in the response (categories)
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(new ByteArrayInputStream(response.getBytes("UTF-8")));
        doc.getDocumentElement().normalize();
        Element response_node = doc.getDocumentElement();
        try {
            NodeList status_list = response_node.getElementsByTagName("status");
            Node status = status_list.item(0);
            NamedNodeMap attributes = status.getAttributes();
            Node code = attributes.item(0);
            if(!code.getTextContent().equals("0")) {
                System.out.println("Not found");
            } else {
                NodeList category_list = response_node.getElementsByTagName("category_list");
                if(category_list.getLength()>0){
                    Node categories = category_list.item(0);
                    NodeList category = categories.getChildNodes();
                    String output = "";
                    for(int i=0; i<category.getLength(); i++) {
                        Node info_category = category.item(i);
                        NodeList child_category = info_category.getChildNodes();
                        String label = "";
                        String code_cat = "";
                        String relevance = "";
                        for(int j=0; j<child_category.getLength(); j++){
                            Node n = child_category.item(j);
                            String name = n.getNodeName();
                            if(name.equals("code"))
                                code_cat = n.getTextContent();
                            else if(name.equals("label"))
                                label = n.getTextContent();
                            else if(name.equals("relevance"))
                                relevance = n.getTextContent();
                        }
                        output += code_cat;
                    }
                    if(output.isEmpty())
                        System.out.println("Not found");
                    else
                        return output;
                }
            }
        } catch (Exception e) {
            System.out.println("Not found");
        }
        return "Nada";
    }

    private String parseoCategoria(String codigo){
        String c = "";
        if (codigo.equals("1"))
            c = "Desastres y accidentes";
        if (codigo.equals("2"))
            c =  "Contaminación";
        if (codigo.equals("3"))
            c = "Eventos";
        if (codigo.equals("4"))
            c = "Criminalidad";
        if (codigo.equals("5"))
            c =  "Nada";
        if (codigo.equals("6"))
            c = "Tráfico";
        if (codigo.equals("7"))
            c = "Transporte público";
        if (codigo.equals("8"))
            c = "Terrorismo";
        return c;
    }
}
