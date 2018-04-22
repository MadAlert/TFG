package a.madalert.madalert;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import a.madalert.madalert.Adapter.DataAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by gmolindi on 10/04/2018.
 */

public class AlertasActivity extends AppCompatActivity {
    private TextView textView;

    private CompositeDisposable mSub;
    private SharedPreferences mSharedPreferences;

    private RecyclerView mRecyclerView;

    private DataAdapter mAdapter;

    private ArrayList<Alertas> mAndroidArrayList;

    private String mDistrito;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_item);
        mSub = new CompositeDisposable();
        //initViews();
        initRecyclerView();
        initSharedPreferences();
        loadAlerta();
    }

    private void initRecyclerView() {

        textView = (TextView) findViewById(R.id.textView);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
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

    private void handleResponse(List<Alertas> alertas) {
        textView.setText("Distrito " + mDistrito);
        mAndroidArrayList = new ArrayList<>(alertas);
        mAdapter = new DataAdapter(mAndroidArrayList);
        mRecyclerView.setAdapter(mAdapter);
        /*mTv1.setText(alertas.getAlertas());
        mTv2.setText(alertas.getDistrito());*/

    }

    private void handleError(Throwable error) {
        showSnackBarMessage("ERRRRRRRR Error !");
    }

    private void showSnackBarMessage(String message) {
        //Snackbar.make(findViewById(R.id.tv1),message,Snackbar.LENGTH_SHORT).show();
    }


}
