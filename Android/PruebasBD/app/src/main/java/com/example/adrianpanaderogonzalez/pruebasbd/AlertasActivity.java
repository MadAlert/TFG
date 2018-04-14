package com.example.adrianpanaderogonzalez.pruebasbd;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by gmolindi on 10/04/2018.
 */

public class AlertasActivity extends AppCompatActivity {
    private TextView mTv1;
    private TextView mTv2;

    private CompositeDisposable mSub;
    private SharedPreferences mSharedPreferences;

    private String mDistrito;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_item);
        mSub = new CompositeDisposable();
        initViews();
        initSharedPreferences();
        loadAlerta();


    }

    private void initViews() {

        mTv1 = (TextView) findViewById(R.id.tv1);
        mTv2 = (TextView) findViewById(R.id.tv2);

    }

    private void initSharedPreferences() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mDistrito = mSharedPreferences.getString("distrito", "");
    }

    private void loadAlerta() {
        mSub.add(NetworkUtil.getRetrofit().getAlertasDistrito(mDistrito)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));
    }

    private void handleResponse(Alertas alertas) {
        mTv1.setText(alertas.getAlertas());
        mTv2.setText(alertas.getDistrito());

    }

    private void handleError(Throwable error) {
        showSnackBarMessage("ERRRRRRRR Error !");
    }

    private void showSnackBarMessage(String message) {
        Snackbar.make(findViewById(R.id.tv1),message,Snackbar.LENGTH_SHORT).show();
    }


}
