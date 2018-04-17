package a.madalert.madalert;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import a.madalert.madalert.Adapter.DataAdapter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.adapter.rxjava2.HttpException;


/**
 * A simple {@link Fragment} subclass.
 */
public class distritos extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private TextView titulo;
    private Button buscar2;
    private CompositeDisposable mSubscriptions;
    private SharedPreferences mSharedPreferences;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Spinner spnr;
    private String dist;

    private distritos.OnFragmentInteractionListener mListener;

    public static final String TAG = distritos.class.getSimpleName();

    public distritos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragmento.
     */
    // TODO: Rename and change types and number of parameters
    public static distritos newInstance(String param1, String param2) {
        distritos fragment = new distritos();
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
        View view = inflater.inflate(R.layout.fragment_distritos, container, false);
        mSubscriptions = new CompositeDisposable();
        initViews(view);
        initSharedPreferences();

        return view;
    }

    private void initViews(View v) {

        titulo = (TextView)v.findViewById(R.id.textView);
        buscar2 = v.findViewById(R.id.buscar);

        titulo.setText("Selecciona un distrito");
        buscar2.setOnClickListener(view -> getAlertasDistrito2());

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

    private void getAlertasDistrito2() {

        //setError();

        //String password = mEtPassword.getText().toString();

        int err = 0;

        /*if (!validateEmail(email)) {

            err++;
            mTiEmail.setError("Email should be valid !");
        }

        if (!validateFields(password)) {

            err++;
            mTiPassword.setError("Password should not be empty !");
        }*/

        if (err == 0) {

            alertasProcess(dist);

        }
        /*    mProgressBar.setVisibility(View.VISIBLE);

        }*/ else {

            showSnackBarMessage("Enter Valid Details !");
        }

    }

    private void alertasProcess(String distrito) {

        /*mSubscriptions.add(NetworkUtil.getRetrofit().getAlertasDistrito(distrito)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));*/
        Alertas alert = new Alertas();
        alert.setDistrito(distrito);
        handleResponse(distrito);
    }

    private void handleResponse(String alerta) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        //editor.putString(Constants.TOKEN,response.getToken())
        editor.putString("distrito", alerta);
        editor.apply();

        //mEtEmail.setText(null);
        //distritoText.setText(null);

       getActivity().getFragmentManager().beginTransaction()
                .replace(R.id.content_frame,new ListaAlertas())
                .addToBackStack(null)
                .commit();

        /*Intent intent = new Intent(getActivity(), AlertasActivity.class);
        startActivity(intent);*/
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

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

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
