package a.madalert.madalert;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;


public class SoporteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soporte);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.contenedor_soporte, new ActivitySoporte());
        ft.commit();
    }


    public static class ActivitySoporte extends PreferenceFragment {

        public ActivitySoporte() {
            // Constructor Por Defecto
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }
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
