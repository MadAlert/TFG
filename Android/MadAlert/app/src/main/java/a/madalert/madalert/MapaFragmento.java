package a.madalert.madalert;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.Fragment;

import io.reactivex.disposables.CompositeDisposable;


/**
 * A simple {@link Fragment} subclass.
 */
<<<<<<< HEAD
public class MapaFragmento extends Fragment{


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
=======
public class MapaFragmento extends Fragment implements
        GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback {

    private MapView mapView;
    private GoogleMap map;
    private SharedPreferences mSharedPreferences;
    private CompositeDisposable mSubscriptions;
    private SharedPreferences.Editor editor;
    private String latitud;
    private String longitud;
    private int km;
    private HashMap<String, Pair<Double, Double>> distCoord;

    public MapaFragmento() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("ronaldo", "abre pestaña maapa");
        View v = inflater.inflate(R.layout.fragment_mapa, container, false);
        mSubscriptions = new CompositeDisposable();
        initSharedPreferences();

        initCoord();
>>>>>>> origin/master

    private MapaFragmento.OnFragmentInteractionListener mListener;

    // TODO: Rename and change types and number of parameters
    public static MapaFragmento newInstance(String param1, String param2) {
        MapaFragmento fragment = new MapaFragmento();
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
        View v = inflater.inflate(R.layout.fragment_mapa, container, false);

        getFragmentManager().beginTransaction()
                .replace(R.id.mapa_frame, new MostrarMapa())
                //.addToBackStack(null)
                .commit();

        return v;
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
            mListener = (MapaFragmento.OnFragmentInteractionListener) context;
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
