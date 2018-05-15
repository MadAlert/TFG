package a.madalert.madalert;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
 * Activities that contain this fragment must implement the
 * {@link ListaAlertas.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListaAlertas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaAlertas extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView textView;
    private TextView firstTime;

    private CompositeDisposable mSub;
    private SharedPreferences mSharedPreferences;

    private RecyclerView mRecyclerView;

    private DataAdapter mAdapter;

    private ArrayList<Alertas> mAndroidArrayList;

    private String mDistrito;

    private String mHayCategorias;

    private String mListaCat;

    private boolean mMapa;

    private String mDistritoMapa;

    private OnFragmentInteractionListener mListener;

    public ListaAlertas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaAlertas.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaAlertas newInstance(String param1, String param2) {
        ListaAlertas fragment = new ListaAlertas();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        mSub = new CompositeDisposable();
        //initViews();
        initRecyclerView(view);
        initSharedPreferences();
        loadAlerta();

        return view;
    }

    private void initRecyclerView(View v) {
        textView = (TextView) v.findViewById(R.id.textView);
        firstTime = (TextView) v.findViewById(R.id.firstTime);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
    }


    private void initSharedPreferences() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        //mDistrito = mSharedPreferences.getString("posicion", "");
        mDistrito = mSharedPreferences.getString("distrito", "");
        mHayCategorias = mSharedPreferences.getString("hayCategorias","");
        mListaCat = mSharedPreferences.getString("listaCat", "");
        mMapa = mSharedPreferences.getBoolean("vieneMapa" , false);
        mDistritoMapa = mSharedPreferences.getString("distritoMapa", "");
    }

    private void loadAlerta() {
        if (!mMapa) { //NO viene del mapa
            if (mHayCategorias.equals("0")) {
                mSub.add(NetworkUtil.getRetrofit().getAlertasDistrito(mDistrito)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                        .subscribe(this::handleResponse, this::handleError));
            } else {
                mSub.add(NetworkUtil.getRetrofit().getAlertasDistritoCategoria(mDistrito, mHayCategorias)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                        .subscribe(this::handleResponse, this::handleError));
            }
        } else { //SI viene del mapa
            mSub.add(NetworkUtil.getRetrofit().getAlertasDistritoCategoria(mDistritoMapa, mListaCat)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError));


        }
    }

    private void handleResponse(List<Alertas> alertas) {
        if (!mMapa) {
            textView.setText("Distrito " + mDistrito);
        } else {
            textView.setText("Distrito " + mDistritoMapa);
        }
        mAndroidArrayList = new ArrayList<>(alertas);
        mAdapter = new DataAdapter(mAndroidArrayList, false);
        mRecyclerView.setAdapter(mAdapter);
        /*mTv1.setText(alertas.getAlertas());
        mTv2.setText(alertas.getDistrito());*/
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean("vieneMapa", false);
        editor.apply();

        if(mAndroidArrayList.isEmpty())
            firstTime.setText("¡No hay nada que mostrar para esa combinación!");

    }

    private void handleError(Throwable error) {
        //showSnackBarMessage("ERRRRRRRR Error !");
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean("vieneMapa", false);
        editor.apply();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}