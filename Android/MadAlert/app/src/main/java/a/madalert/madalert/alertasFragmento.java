package a.madalert.madalert;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import a.madalert.madalert.Adapter.DataAdapter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlertasFragmento extends Fragment {

    private TextView textView;

    private CompositeDisposable mSub;
    private SharedPreferences mSharedPreferences;

    private RecyclerView mRecyclerView;

    private DataAdapter mAdapter;

    private ArrayList<Alertas> mAndroidArrayList;

    private String mDistrito;

    private String mHayCategorias;

    private boolean mTodas;

    private ListaAlertas.OnFragmentInteractionListener mListener;

    public static final String TAG = AlertasFragmento.class.getSimpleName();

    public AlertasFragmento() {

    }

    private void initRecyclerView(View v) {
        textView = (TextView) v.findViewById(R.id.textView);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void initSharedPreferences() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mDistrito = mSharedPreferences.getString("distritoConf", "");
        mHayCategorias = mSharedPreferences.getString("listaCat","");
        mTodas = mSharedPreferences.getBoolean("todasBool", false);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_alertas, container, false);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.anadirAlerta);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent AddAlerta = new Intent(getContext(), AddAlertaActivity.class);
                startActivity(AddAlerta);
            }
        });

        mSub = new CompositeDisposable();
        initRecyclerView(v);
        initSharedPreferences();

        loadAlerta();

        return v;
    }

    private void loadAlerta() {
        Log.d("ELLA", mHayCategorias);
        if(mTodas) {
            mSub.add(NetworkUtil.getRetrofit().getAlertasDistrito(mDistrito)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        }
        else{
            String[] categoriasP;
            categoriasP = mHayCategorias.split(",");
            mSub.add(NetworkUtil.getRetrofit().getAlertasDistritoCategoria(mDistrito, mHayCategorias)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));
        }
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
        //showSnackBarMessage("ERRRRRRRR Error !");
    }

}
