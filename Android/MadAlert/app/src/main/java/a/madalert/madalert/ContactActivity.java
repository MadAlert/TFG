package a.madalert.madalert;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity {

    private String[] TO = {"madalert17@gmail.com"};

    private EditText etName;
    private EditText etEmail;
    private EditText etSubject;
    private EditText etBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Button btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* obtenemos los datos para el env’o del correo */
                etName = (EditText) findViewById(R.id.etName);
                etEmail = (EditText) findViewById(R.id.etEmail);
                etSubject = (EditText) findViewById(R.id.etSubject);
                etBody = (EditText) findViewById(R.id.etBody);

                /* es necesario un intent que levante la actividad deseada */
                Intent itSend = new Intent(android.content.Intent.ACTION_SEND);
                itSend.setData(Uri.parse("mailto:"));

                /* vamos a enviar texto plano a menos que el checkbox esté marcado */
                itSend.setType("plain/text");

                /* colocamos los datos para el envío */
                itSend.putExtra(android.content.Intent.EXTRA_TEXT, new String[]{etName.getText().toString()});
                //itSend.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ etEmail.getText().toString()});
                itSend.putExtra(android.content.Intent.EXTRA_EMAIL, TO);
                itSend.putExtra(android.content.Intent.EXTRA_SUBJECT, etSubject.getText().toString());
                itSend.putExtra(android.content.Intent.EXTRA_TEXT, etBody.getText());

                /* iniciamos la actividad */

                if (validate()) {
                    startActivity(itSend);
                    //finish();
                    /*try {
                        startActivity(Intent.createChooser(itSend, "Enviar email..."));
                        finish();
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(ContactActivity.this,
                                "No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show();
                    }*/
                }
            }
        });
    }

    public boolean validate() {
        boolean valido = true;
        if(etName.getText().toString().isEmpty()) {
            valido = false;
            etName.setError("Introduce la nombre");
        }
        if(etEmail.getText().toString().isEmpty()) {
            valido=false;
            etEmail.setError("Introduce tu email");
        }
        if(etSubject.getText().toString().isEmpty()) {
            valido=false;
            etSubject.setError("Introduce tu asunto");
        }
        if(etBody.getText().toString().isEmpty()) {
            valido=false;
            etBody.setError("Introduce tu mensaje");
        }
        return valido;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            setResult(RESULT_OK);
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        return super.onKeyDown(keyCode, event);
    }

}
