package com.example.adrianpanaderogonzalez.pruebasbd;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by gmolindi on 10/04/2018.
 */

public class AlertasActivity extends AppCompatActivity {
    private CompositeDisposable mSub;
    private SharedPreferences mSharedPreferences;

    private String mDistrito;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_item);
        mSub = new CompositeDisposable();
        initSharedPreferences();
        loadAlerta();

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

    }

    private void handleError(Throwable error) {
    }


}
