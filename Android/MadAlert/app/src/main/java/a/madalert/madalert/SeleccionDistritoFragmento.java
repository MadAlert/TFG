package a.madalert.madalert;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.ArrayList;

import a.madalert.madalert.Adapter.GridViewAdapter;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.adapter.rxjava2.HttpException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SeleccionDistritoFragmento.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SeleccionDistritoFragmento extends Fragment {

    private TextView titulo;
    private Button buscar2;
    private CompositeDisposable mSubscriptions;
    private SharedPreferences mSharedPreferences;
    private GridView gridView;
    private View btnGo;
    private ArrayList<String> selectedStrings;
    private ArrayList<View> listaViews;
    private static final String[] categorias = new String[]{
            "Todas", "Desastres y accidentes", "Terrorismo", "Criminalidad",
            "Tráfico", "Eventos", "Transporte público", "Contaminación"};



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Spinner spnr;
    private String dist;

    public static final String TAG = SeleccionDistritoFragmento.class.getSimpleName();

    private OnFragmentInteractionListener mListener;

    public SeleccionDistritoFragmento() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seleccionar_distrito, container, false);
        mSubscriptions = new CompositeDisposable();
        initViews(view);
        initSharedPreferences();

        //return view;
        return view;
    }

    private void initViews(View v) {

        titulo = (TextView)v.findViewById(R.id.textView);
        buscar2 = v.findViewById(R.id.buscar);

        titulo.setText("Selecciona un distrito");
        buscar2.setOnClickListener(view -> alertasProcess(dist));

        gridView = (GridView) v.findViewById(R.id.grid);

        selectedStrings = new ArrayList<>();
        listaViews = new ArrayList<>();

        final GridViewAdapter adapter2 = new GridViewAdapter(categorias, getActivity());
        gridView.setAdapter(adapter2);
        gridView.setOnItemClickListener((parent, v1, position, id) -> {
            int selectedIndex = adapter2.selectedPositions.indexOf(position);
            if(position == 0 && id == 0 && !(selectedIndex > -1)){ //Se ha selecionado la opcion TODAS
                for(int i= listaViews.size()-1; i >= 0; i--){
                    ((GridItemView) listaViews.get(i)).display(false);
                    listaViews.remove(i);
                    selectedStrings.remove(i);
                    adapter2.selectedPositions.remove(i);
                }
            }
            if (selectedIndex > -1) {
                adapter2.selectedPositions.remove(selectedIndex);
                ((GridItemView) v1).display(false);
                selectedStrings.remove((String) parent.getItemAtPosition(position));
                listaViews.remove(v1);
            } else {
                if(adapter2.selectedPositions.contains(0) && id != 0) { //Esta todas
                    ((GridItemView) listaViews.get(0)).display(false);
                    selectedStrings.remove(0);
                    listaViews.remove(0);
                    adapter2.selectedPositions.remove(0);
                }
                adapter2.selectedPositions.add(position);
                ((GridItemView) v1).display(true);
                listaViews.add(v1);
                String x =  v1.toString();
                selectedStrings.add((String) parent.getItemAtPosition(position));
            }
        });

        spnr = (Spinner)v.findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.distritos_array , android.R.layout.simple_spinner_item);

        spnr.setAdapter(adapter);
        spnr.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {

                        dist = (String) spnr.getSelectedItem();
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }

                }
        );
    }


    private void initSharedPreferences() {

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    //private void getAlertasDistrito2(View v) {

        //setError();

        //String password = mEtPassword.getText().toString();

        //int err = 0;

        /*if (!validateEmail(email)) {

            err++;
            mTiEmail.setError("Email should be valid !");
        }

        if (!validateFields(password)) {

            err++;
            mTiPassword.setError("Password should not be empty !");
        }*/

        //if (err == 0) {

            //alertasProcess(dist);
            /*buscar2.setVisibility(View.GONE);
            titulo.setVisibility(View.GONE);
            spnr.setVisibility(View.GONE);*/

        /*}
           mProgressBar.setVisibility(View.VISIBLE);

        } else {

            showSnackBarMessage("Enter Valid Details !");
        }*/

    //}

    private void alertasProcess(String distrito) {

        Alertas alert = new Alertas();
        alert.setDistrito(distrito);
        handleResponse(distrito);
    }

    private void handleResponse(String alerta) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        //editor.putString(Constants.TOKEN,response.getToken())
        editor.putString("distrito", alerta);

        if(selectedStrings.size()==1 && selectedStrings.get(0)=="Todas"){
            editor.putString("hayCategorias", "0");
        }
        else{
            String categorias = new String();
            for(int i=0; i< selectedStrings.size();i++){
                categorias=categorias+selectedStrings.get(i);
                if(i < selectedStrings.size()-1){
                    categorias=categorias+",";
                }
            }
            editor.putString("hayCategorias", categorias); //Hay que pasar el array de string por aqui

        }
        editor.apply();

        getFragmentManager().beginTransaction()
                .replace(R.id.distritos_frame, new ListaAlertas())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }


    private void handleError(Throwable error) {

        // mProgressbar.setVisibility(View.GONE);
        boolean failed = false;

        if (error instanceof HttpException) {

            Gson gson = new GsonBuilder().create();

            try {

                String errorBody = ((HttpException) error).response().errorBody().string();
                Response response = gson.fromJson(errorBody, Response.class);
                showSnackBarMessage(response.getMessage());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalStateException | JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else {

            showSnackBarMessage("Network Error !");
        }
    }

    private void showSnackBarMessage(String message) {

        if (getView() != null) {

            Snackbar.make(getView(),message,Snackbar.LENGTH_SHORT).show();
        }
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
        try {
            mListener = (SeleccionDistritoFragmento.OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnFragmentInteractionListener") ;
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
