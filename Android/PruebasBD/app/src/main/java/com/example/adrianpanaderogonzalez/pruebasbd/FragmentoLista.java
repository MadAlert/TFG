package com.example.adrianpanaderogonzalez.pruebasbd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.SyncStateContract;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adrianpanaderogonzalez.pruebasbd.dummy.DummyContent;
import com.example.adrianpanaderogonzalez.pruebasbd.dummy.DummyContent.DummyItem;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class FragmentoLista extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    public static final String TAG = FragmentoLista.class.getSimpleName();

    private CompositeDisposable mSub;
    private SharedPreferences mShared;

    private TextView vContent;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FragmentoLista newInstance(int columnCount) {
        FragmentoLista fragment = new FragmentoLista();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
       /* mSub = new CompositeDisposable();
        initViews(view);
*/
        // Set the adapter ESTO ES LO QUE HACE MOSTRAR LA LISTA DE ITEMS
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
        return view;
    }


    private void initViews(View view){

        vContent = (TextView) view.findViewById(R.id.content);

        vContent.setOnClickListener(view1 -> muestraAlerta());

    }

    private void muestraAlerta(){

        String alerta = vContent.getText().toString();

        muestraProcess(alerta);

    }

    private void muestraProcess(String distrito){
        mSub.add(NetworkUtil.getRetrofit().getAlertasDistrito(distrito)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));
    }

    private void handleResponse(Alertas alertas) {
        SharedPreferences.Editor editor = mShared.edit();
        editor.putString("noticia", alertas.getAlertas());
        editor.apply();

        Intent intent = new Intent(getActivity(), AlertasActivity.class);
        startActivity(intent);
    }

    private void handleError(Throwable throwable) {
    }

    private void handleResponse(Response response){


    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else throw new RuntimeException(context.toString()
                + " must implement OnListFragmentInteractionListener");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
