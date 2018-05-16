package a.madalert.madalert;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Button btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* obtenemos los datos para el env’o del correo */
                EditText etName = (EditText) findViewById(R.id.etName);
                EditText etEmail = (EditText) findViewById(R.id.etEmail);
                EditText etSubject = (EditText) findViewById(R.id.etSubject);
                EditText etBody = (EditText) findViewById(R.id.etBody);

                /* es necesario un intent que levante la actividad deseada */
                Intent itSend = new Intent(android.content.Intent.ACTION_SEND);

                /* vamos a enviar texto plano a menos que el checkbox estŽ marcado */
                itSend.setType("plain/text");

                /* colocamos los datos para el env’o */
                itSend.putExtra(android.content.Intent.EXTRA_TEXT, new String[]{ etName.getText().toString()});
                itSend.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ etEmail.getText().toString()});
                itSend.putExtra(android.content.Intent.EXTRA_SUBJECT, etSubject.getText().toString());
                itSend.putExtra(android.content.Intent.EXTRA_TEXT, etBody.getText());

                /* iniciamos la actividad */
                startActivity(itSend);
            }
        });
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
