package a.madalert.madalert;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
<<<<<<< HEAD
=======
import android.net.Uri;
import android.os.AsyncTask;
>>>>>>> a657a95d5d9115870becceef56aa686d4fbad478
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import a.madalert.madalert.Adapter.DataAdapter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlertasFragmento extends Fragment {

    private TextView textView;
    private TextView firstTime;

    //private HashMap<String, Pair<Double, Double>> distCoord;
    private HashMap<String, ArrayList<Pair<Double, Double>>> distCoord;
    private ArrayList<String> distRadio;
    private int kms;

<<<<<<< HEAD
=======
    private AlertasFragmento.OnFragmentInteractionListener mListener;

>>>>>>> a657a95d5d9115870becceef56aa686d4fbad478
    private CompositeDisposable mSub;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;

    private RecyclerView mRecyclerView;

    private DataAdapter mAdapter;

    private ArrayList<Alertas> mAndroidArrayList;

    private String mDistrito;

    private String auxDistrito;

    private String mHayCategorias;

    private boolean mTodas;

    private boolean mFirstTime;

    private boolean mCheckedSw;
    private String latitud, longitud;

    public static final String TAG = AlertasFragmento.class.getSimpleName();

    private VolleyS volley;

    protected RequestQueue fRequestQueue;
    private String distritoObtenido;
    private SwipeRefreshLayout swipeRefreshLayout;

    public AlertasFragmento() {

    }

    private void initRecyclerView(View v) {
        textView = (TextView) v.findViewById(R.id.textView);
        firstTime = (TextView) v.findViewById(R.id.firstTime);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Esto se ejecuta cada vez que se realiza el gesto
                try {
                    loadAlerta();
                    swipeRefreshLayout.setRefreshing(false);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initSharedPreferences() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mDistrito = mSharedPreferences.getString("distritoConf", "");
        mHayCategorias = mSharedPreferences.getString("listaCat","");
        mTodas = mSharedPreferences.getBoolean("todas", false);
        mFirstTime = mSharedPreferences.getBoolean("primeraVez", true);
        mCheckedSw = mSharedPreferences.getBoolean("isCheckedSw", false);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        volley = VolleyS.getInstance(getActivity().getApplicationContext());
        fRequestQueue = volley.getRequestQueue();

        View v = inflater.inflate(R.layout.fragment_alertas, container, false);
        mSub = new CompositeDisposable();
        initSharedPreferences();
        editor = mSharedPreferences.edit();

        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.anadirAlerta);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent AddAlerta = new Intent(getContext(), AddAlertaActivity.class);
                startActivity(AddAlerta);
            }
        });

        initRecyclerView(v);
        if (mFirstTime) {
            // first time task
            firstTime.setText(R.string.firstTime);
        }
        else {
            try {
                loadAlerta();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return v;
    }

    public void initCoord(){
        distCoord = new HashMap<String, ArrayList<Pair<Double, Double>>>();
        ArrayList<Pair<Double, Double>> coordenadas = new ArrayList<>();

        coordenadas.add(new Pair<>(40.4420755, -3.7458086));
        distCoord.put("Todos", coordenadas);
        coordenadas = new ArrayList<>();

        coordenadas.add(new Pair<>(40.400861, -3.699350));
        coordenadas.add(new Pair<>(40.412284, -3.721580));
        coordenadas.add(new Pair<>(40.4042021, -3.7181038));
        coordenadas.add(new Pair<>(40.3911617, -3.7025685));
        coordenadas.add(new Pair<>(40.3853107, -3.6929554));
        coordenadas.add(new Pair<>(40.3951166, -3.7017531));
        coordenadas.add(new Pair<>(40.4035485, -3.6980838));
        coordenadas.add(new Pair<>(40.4044309, -3.7117953));
        distCoord.put("Arganzuela", coordenadas);
        coordenadas = new ArrayList<>();

        coordenadas.add(new Pair<>(40.4839402, -3.5701402));
        coordenadas.add(new Pair<>(404669605., -3.6318945));
        coordenadas.add(new Pair<>(40.4545846, -3.6084184));
        coordenadas.add(new Pair<>(40.454716, -3.565998));
        coordenadas.add(new Pair<>(40.4581447, -3.5399055));
        coordenadas.add(new Pair<>(40.4894846, -3.5513853));
        coordenadas.add(new Pair<>(40.510900, -3.568144));
        coordenadas.add(new Pair<>(40.4931808, -3.5981631));
        distCoord.put("Barajas", coordenadas);
        coordenadas = new ArrayList<>();

        coordenadas.add(new Pair<>(40.381607, -3.735203));
        coordenadas.add(new Pair<>(40.3629794, -3.7677715));
        coordenadas.add(new Pair<>(40.3708105, -3.7617848));
        coordenadas.add(new Pair<>(40.3800955,-3.7421296));
        coordenadas.add(new Pair<>(40.3898531,-3.7211869));
        coordenadas.add(new Pair<>(40.3959653,-3.7242554));
        coordenadas.add(new Pair<>(40.3823838,-3.755884));
        coordenadas.add(new Pair<>(40.3729929,-3.7599287));
        distCoord.put("Carabanchel", coordenadas);
        coordenadas = new ArrayList<>();

        coordenadas.add(new Pair<>(40.4169416, -3.7083759));
        coordenadas.add(new Pair<>(40.4176312, -3.7134502));
        coordenadas.add(new Pair<>(40.4262074, -3.7082359));
        coordenadas.add(new Pair<>(40.4263031, -3.7177963));
        coordenadas.add(new Pair<>(40.4209311, -3.7134716));
        coordenadas.add(new Pair<>(40.4178273, -3.7240932));
        coordenadas.add(new Pair<>(40.4106633, -3.7164006));
        coordenadas.add(new Pair<>(40.4072322, -3.7063799));
        coordenadas.add(new Pair<>(40.4133509, -3.7108323));
        distCoord.put("Centro", coordenadas);
        coordenadas = new ArrayList<>();

        coordenadas.add(new Pair<>(40.3729929,-3.7599287));
        coordenadas.add(new Pair<>(40.460367, -3.676567));
        coordenadas.add(new Pair<>(40.4449207,-3.6906002));
        coordenadas.add(new Pair<>(40.4462761,-3.6669539));
        coordenadas.add(new Pair<>(40.4577385,-3.6706446));
        coordenadas.add(new Pair<>(40.4704885,-3.6766742));
        coordenadas.add(new Pair<>(40.4823385,-3.6759714));
        coordenadas.add(new Pair<>(40.480739,-3.685048));
        coordenadas.add(new Pair<>(40.4591262,-3.6913298));
        coordenadas.add(new Pair<>(40.4646117,-3.6920164));
        distCoord.put("Chamartín", coordenadas);
        coordenadas = new ArrayList<>();

        coordenadas.add(new Pair<>(40.438656, -3.704180));
        coordenadas.add(new Pair<>(40.4276636,-3.6950067));
        coordenadas.add(new Pair<>(40.4365733,-3.6984078));
        coordenadas.add(new Pair<>(40.4449674,-3.6929951));
        coordenadas.add(new Pair<>(40.4447633,-3.7062506));
        coordenadas.add(new Pair<>(40.4461166,-3.7178618));
        coordenadas.add(new Pair<>(40.4385658,-3.7173978));
        coordenadas.add(new Pair<>(40.4327352,-3.7180415));
        coordenadas.add(new Pair<>(40.4313306,-3.7057678));
        distCoord.put("Chamberí", coordenadas);
        coordenadas = new ArrayList<>();

        coordenadas.add(new Pair<>(40.455531, -3.656119));
        coordenadas.add(new Pair<>(40.4816562,-3.6702447));
        coordenadas.add(new Pair<>(40.4689737,-3.6705823));
        coordenadas.add(new Pair<>(40.437791,-3.6611025));
        coordenadas.add(new Pair<>(40.4199269,-3.657214));
        coordenadas.add(new Pair<>(40.4148774,-3.6260929));
        coordenadas.add(new Pair<>(40.4378572,-3.6376626));
        coordenadas.add(new Pair<>(40.4600702,-3.6599855));
        distCoord.put("Ciudad Lineal", coordenadas);
        coordenadas = new ArrayList<>();

        coordenadas.add(new Pair<>(40.5353893,-3.7946006));
        coordenadas.add(new Pair<>(40.4761481,-3.7727917));
        coordenadas.add(new Pair<>(40.4937001,-3.8326717));
        coordenadas.add(new Pair<>(40.5609201,-3.8702837));
        coordenadas.add(new Pair<>(40.6072401,-3.8101157));
        coordenadas.add(new Pair<>(40.5683781,-3.7182427));
        coordenadas.add(new Pair<>(40.6126271,-3.6784847));
        coordenadas.add(new Pair<>(40.6384511,-3.6497997));
        coordenadas.add(new Pair<>(40.5818829,-3.6350135));
        distCoord.put("Fuencarral-El Pardo", coordenadas);
        coordenadas = new ArrayList<>();

        coordenadas.add(new Pair<>(40.4420755, -3.7458085));
        distCoord.put("General", coordenadas);
        coordenadas = new ArrayList<>();

        coordenadas.add(new Pair<>(40.485152, -3.634796));
        coordenadas.add(new Pair<>(40.453222,-3.6118037));
        coordenadas.add(new Pair<>(40.4748449,-3.6090464));
        coordenadas.add(new Pair<>(40.500768,-3.6058173));
        coordenadas.add(new Pair<>(40.4855435,-3.6730761));
        coordenadas.add(new Pair<>(40.5041784,-3.630032));
        coordenadas.add(new Pair<>(40.5124907,-3.6549122));
        coordenadas.add(new Pair<>(40.4854782,-3.6685271));
        coordenadas.add(new Pair<>(40.4654995,-3.6600727));
        coordenadas.add(new Pair<>(40.4516708,-3.647616));
        distCoord.put("Hortaleza", coordenadas);
        coordenadas = new ArrayList<>();

        coordenadas.add(new Pair<>(40.387812, -3.773530));
        coordenadas.add(new Pair<>(40.396179,-3.8338897));
        coordenadas.add(new Pair<>(40.3689646,-3.8096));
        coordenadas.add(new Pair<>(40.3652534,-3.7882067));
        coordenadas.add(new Pair<>(40.3831045,-3.7740446));
        coordenadas.add(new Pair<>(40.4147817,-3.7288869));
        coordenadas.add(new Pair<>(40.4078135,-3.7462462));
        coordenadas.add(new Pair<>(40.3970943,-3.7782074));
        coordenadas.add(new Pair<>(40.391718,-3.8012747));
        coordenadas.add(new Pair<>(40.4002481,-3.7695814));
        distCoord.put("Latina", coordenadas);
        coordenadas = new ArrayList<>();

        coordenadas.add(new Pair<>(40.443568,-3.742829));
        coordenadas.add(new Pair<>(40.4289998,-3.736993));
        coordenadas.add(new Pair<>(40.4444825,-3.7499534));
        coordenadas.add(new Pair<>(40.4585248,-3.7295686));
        coordenadas.add(new Pair<>(40.4709156,-3.7375079));
        coordenadas.add(new Pair<>(40.4718624,-3.7700592));
        coordenadas.add(new Pair<>(40.468667,-3.8006364));
        coordenadas.add(new Pair<>(40.4723031,-3.8335096));
        coordenadas.add(new Pair<>(40.4549982,-3.8060867));
        coordenadas.add(new Pair<>(40.4442212,-3.7891673));
        coordenadas.add(new Pair<>(40.4332791,-3.7734495));
        coordenadas.add(new Pair<>(40.4154087,-3.7625276));
        coordenadas.add(new Pair<>(40.400214,-3.7709817));
        distCoord.put("Moncloa-Aravaca", coordenadas);
        coordenadas = new ArrayList<>();

        coordenadas.add(new Pair<>(40.407016, -3.644330));
        coordenadas.add(new Pair<>(40.4066104,-3.6658144));
        coordenadas.add(new Pair<>(40.4107957,-3.6627519));
        coordenadas.add(new Pair<>(40.4136321,-3.6447327));
        coordenadas.add(new Pair<>(40.4121581,-3.6262237));
        coordenadas.add(new Pair<>(40.4022133,-3.6287256));
        coordenadas.add(new Pair<>(40.394843,-3.633604));
        coordenadas.add(new Pair<>(40.4021715,-3.6509371));
        distCoord.put("Moratalaz", coordenadas);
        coordenadas = new ArrayList<>();

        coordenadas.add(new Pair<>(40.386887, -3.658476));
        coordenadas.add(new Pair<>(40.3812974,-3.6587336));
        coordenadas.add(new Pair<>(40.3842722,-3.6444428));
        coordenadas.add(new Pair<>(40.3935717,-3.6440136));
        coordenadas.add(new Pair<>(40.405529,-3.6654983));
        coordenadas.add(new Pair<>(40.3929098,-3.6737754));
        coordenadas.add(new Pair<>(40.3820983,-3.6860492));
        coordenadas.add(new Pair<>(40.3642307,-3.6808028));
        coordenadas.add(new Pair<>(40.364631,-3.6672472));
        distCoord.put("Puente de Vallecas", coordenadas);
        coordenadas = new ArrayList<>();

        coordenadas.add(new Pair<>(40.4101076, -3.6736514));
        coordenadas.add(new Pair<>(40.3970847,-3.6754431));
        coordenadas.add(new Pair<>(40.3842722,-3.6444428));
        coordenadas.add(new Pair<>(40.4066275,-3.6697676));
        coordenadas.add(new Pair<>(40.418309,-3.6620747));
        coordenadas.add(new Pair<>(40.4202203,-3.6763873));
        coordenadas.add(new Pair<>(40.417386,-3.6917832));
        coordenadas.add(new Pair<>(40.4092825,-3.6895838));
        coordenadas.add(new Pair<>(40.403525,-3.6845975));
        distCoord.put("Retiro", coordenadas);
        coordenadas = new ArrayList<>();

        coordenadas.add(new Pair<>(40.429807, -3.673778));
        coordenadas.add(new Pair<>(40.4233873,-3.6946562));
        coordenadas.add(new Pair<>(40.422689,-3.6756179));
        coordenadas.add(new Pair<>(40.4213617,-3.6637679));
        coordenadas.add(new Pair<>(40.4315381,-3.6698297));
        coordenadas.add(new Pair<>(40.4427989,-3.6631081));
        coordenadas.add(new Pair<>(40.4373933,-3.6757949));
        coordenadas.add(new Pair<>(40.43733,-3.6894876));
        coordenadas.add(new Pair<>(40.4306316,-3.6884549));
        distCoord.put("Salamanca", coordenadas);
        coordenadas = new ArrayList<>();

        coordenadas.add(new Pair<>(40.436229, -3.599431));
        coordenadas.add(new Pair<>(40.4458837,-3.5365669));
        coordenadas.add(new Pair<>(40.4472227,-3.5736779));
        coordenadas.add(new Pair<>(40.4478432,-3.6087397));
        coordenadas.add(new Pair<>(40.4467492,-3.6426643));
        coordenadas.add(new Pair<>(40.430679,-3.6297683));
        coordenadas.add(new Pair<>(40.421548,-3.6211423));
        coordenadas.add(new Pair<>(40.413249,-3.5939983));
        coordenadas.add(new Pair<>(40.4291692,-3.6227479));
        distCoord.put("San Blas-Canillejas", coordenadas);
        coordenadas = new ArrayList<>();

        coordenadas.add(new Pair<>(40.460158, -3.698835));
        coordenadas.add(new Pair<>(40.4502804,-3.6997143));
        coordenadas.add(new Pair<>(40.4593912,-3.6977831));
        coordenadas.add(new Pair<>(40.4729693,-3.6903588));
        coordenadas.add(new Pair<>(40.4720348,-3.7031797));
        coordenadas.add(new Pair<>(40.4620523,-3.7125675));
        coordenadas.add(new Pair<>(40.4571707,-3.71128));
        coordenadas.add(new Pair<>(40.4511948,-3.7103788));
        coordenadas.add(new Pair<>(40.4697087,-3.6963025));
        distCoord.put("Tetuán", coordenadas);
        coordenadas = new ArrayList<>();

        coordenadas.add(new Pair<>(40.377026, -3.701982));
        coordenadas.add(new Pair<>(40.3929487,-3.705566));
        coordenadas.add(new Pair<>(40.3829065,-3.7170111));
        coordenadas.add(new Pair<>(40.3666549,-3.7191074));
        coordenadas.add(new Pair<>(40.3652534,-3.7029396));
        coordenadas.add(new Pair<>(40.3644353,-3.6900679));
        coordenadas.add(new Pair<>(40.3794643,-3.6924083));
        coordenadas.add(new Pair<>(40.3936308,-3.7045326));
        distCoord.put("Usera", coordenadas);
        coordenadas = new ArrayList<>();

        coordenadas.add(new Pair<>(40.393974, -3.581134));
        coordenadas.add(new Pair<>(40.4149611,-3.6160247));
        coordenadas.add(new Pair<>(40.3924543,-3.6233781));
        coordenadas.add(new Pair<>(40.3766379,-3.6004356));
        coordenadas.add(new Pair<>(40.3574618,-3.5565886));
        coordenadas.add(new Pair<>(40.3952834,-3.5312878));
        coordenadas.add(new Pair<>(40.416848, -3.528069));
        coordenadas.add(new Pair<>(40.4110252,-3.5545241));
        coordenadas.add(new Pair<>(40.425154, -3.577095));
        distCoord.put("Vicálvaro", coordenadas);
        coordenadas = new ArrayList<>();

        coordenadas.add(new Pair<>(40.355089, -3.621192));
        coordenadas.add(new Pair<>(40.3142841,-3.5844007));
        coordenadas.add(new Pair<>(40.3235731,-3.6291037));
        coordenadas.add(new Pair<>(40.3305081,-3.6589827));
        coordenadas.add(new Pair<>(40.3591611,-3.6789337));
        coordenadas.add(new Pair<>(40.3815168,-3.6238959));
        coordenadas.add(new Pair<>(40.3703551,-3.6094362));
        coordenadas.add(new Pair<>(40.3703551,-3.6094362));
        coordenadas.add(new Pair<>(40.3613981,-3.5755987));
        distCoord.put("Villa de Vallecas", coordenadas);
        coordenadas = new ArrayList<>();

        coordenadas.add(new Pair<>(40.345987, -3.693332));
        coordenadas.add(new Pair<>(40.3634511,-3.7206217));
        coordenadas.add(new Pair<>(40.3600102,-3.6901649));
        coordenadas.add(new Pair<>(40.3459968,-3.6789094));
        coordenadas.add(new Pair<>(40.3283665,-3.6915137));
        coordenadas.add(new Pair<>(40.3272701,-3.7080657));
        coordenadas.add(new Pair<>(40.3328448,-3.7223507));
        coordenadas.add(new Pair<>(40.3560919,-3.7226726));
        distCoord.put("Villaverde", coordenadas);


    }

    public void recorrerRadio(Boolean ubicacionActivada){
        initCoord();
        distRadio = new ArrayList<>();
        boolean marcadorEncontrado;
        //Iterator<Map.Entry<String, Pair<Double, Double>>> iterator = distCoord.entrySet().iterator();
        Iterator<Map.Entry<String, ArrayList<Pair<Double, Double>>>> iterator = distCoord.entrySet().iterator();
        Double parsLat=0.0, parsLong=0.0;
        if(ubicacionActivada) {
            latitud = mSharedPreferences.getString("latitud", "");
            longitud = mSharedPreferences.getString("longitud", "");
            parsLat = Double.parseDouble(latitud);
            parsLong = Double.parseDouble(longitud);
        }
        else{
            auxDistrito=mDistrito;
            if(auxDistrito != null) { // Como la ubi esta desactivada solo se quiere el marcador de centro del distrito, el get(0)
                Pair<Double, Double> latLong = distCoord.get(auxDistrito).get(0);
                parsLat = latLong.first;
                parsLong = latLong.second;
            }
        }
        kms = mSharedPreferences.getInt("km", 0);

        while (iterator.hasNext()) {
            Map.Entry<String, ArrayList<Pair<Double, Double>>> it = iterator.next(); // iterador del HashMap
            if(!it.getKey().equals("Todos") && !it.getKey().equals("General")) {
                ArrayList<Pair<Double, Double>> listAux = it.getValue(); // obtengo el arrayList del distrito que estoy iterando
                Iterator<Pair<Double, Double>> itArrayList = listAux.iterator(); // iterador del arrayList
                marcadorEncontrado = false;
                while (itArrayList.hasNext() && !marcadorEncontrado) {
                    //Double lat = it.getValue().get().first;
                    //Double longi = it.getValue().get().second;
                    Pair<Double, Double> parAux = itArrayList.next();
                    Double lat = parAux.first;
                    Double longi = parAux.second;
                    //Double var = Math.sqrt( (Math.pow(parsLat-lat,2) + (Math.pow(parsLong-longi, 2))));
                    Double var = distanciaCoord(parsLat, parsLong, lat, longi);
                    if (var <= kms) {
                        distRadio.add(it.getKey());
                        marcadorEncontrado = true; // en el momento en el que encuentre una coordenada dentro, ya no es necesario buscar mas
                    }
                }
            }
        }
    }

    public static double distanciaCoord(double lat1, double lng1, double lat2, double lng2) {
        //double radioTierra = 3958.75;//en millas
        double radioTierra = 6371;//en kilómetros
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        double distancia = radioTierra * va2;

        return distancia;
    }

    private void loadAlerta() throws IOException, JSONException {
        auxDistrito="";
        distRadio = new ArrayList<>();
        if(mCheckedSw) {
            // esto tiene que estar aqui, no mover
            latitud = mSharedPreferences.getString("latitud", "");
            longitud = mSharedPreferences.getString("longitud", "");
            obtenerDistrito(latitud, longitud);

            for(int j = 0; j < distRadio.size(); j++) {
                //auxDistrito += "," + distRadio.get(j);
                mDistrito += "," + distRadio.get(j);
            }
        }
        else{
            mDistrito = mSharedPreferences.getString("distritoConf", "");
            auxDistrito = mDistrito;
            if(!mDistrito.equals("Todos") || kms > 0) { //Cuando hay radio y distrito!=Todos
                recorrerRadio(mCheckedSw);
                for(int j = 0; j < distRadio.size(); j++) {
                    //auxDistrito += "," + distRadio.get(j);
                    auxDistrito += "," + distRadio.get(j);
                }
            }

            if(mTodas) {
                mSub.add(NetworkUtil.getRetrofit().getAlertasDistrito(auxDistrito)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                        .subscribe(this::handleResponse, this::handleError));
            }
            else{
                String[] categoriasP;
                //obtenerDistrito(latitud, longitud);
                categoriasP = mHayCategorias.split(",");
                mSub.add(NetworkUtil.getRetrofit().getAlertasDistritoCategoria(auxDistrito, mHayCategorias)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                        .subscribe(this::handleResponse, this::handleError));
            }
        }
    }

    private void handleResponse(List<Alertas> alertas) {
        textView.setText("Distrito " + mDistrito);
        mAndroidArrayList = new ArrayList<>(alertas);
        mAdapter = new DataAdapter(mAndroidArrayList, true, mDistrito); //Si tiene varios distritos muestra el distrito en cada RecyclerView
        mRecyclerView.setAdapter(mAdapter);

        if(mAndroidArrayList.isEmpty())
            firstTime.setText("¡No hay alertas para esa configuración!");

        /*mTv1.setText(alertas.getAlertas());
        mTv2.setText(alertas.getDistrito());*/

    }

    private void handleError(Throwable error) {
        //showSnackBarMessage("ERRRRRRRR Error !");
    }

    private void obtenerDistrito(String latitud, String longitud) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng="+latitud+","+longitud+"&result_type=sublocality&key=AIzaSyDOveaxbksFJQgJfQXoWvvw9vOntdr8r3o";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url, jsonObject -> {
            distritoObtenido = jsonObject.toString();

            if(distritoObtenido!=null){
                JsonParser parser = new JsonParser();
                // Obtain Array
                JsonObject gsonObj = parser.parse(distritoObtenido).getAsJsonObject();
                JsonArray demarcation = gsonObj.get("results").getAsJsonArray();
                List listDemarcation = new ArrayList();
                for (JsonElement demarc : demarcation) {
                    JsonObject gsonP = demarc.getAsJsonObject();
                    // Primitives elements of object
                    JsonArray address = gsonP.get("address_components").getAsJsonArray();
                    int i=0;
                    for(JsonElement add: address){
                        if(i==0){
                            JsonObject gsonAdd = add.getAsJsonObject();
                            mDistrito = gsonAdd.get("long_name").getAsString();
                            auxDistrito = mDistrito;
                            recorrerRadio(mCheckedSw);
                            for(int j = 0; j < distRadio.size(); j++) {
                                //auxDistrito += "," + distRadio.get(j);
                                auxDistrito += "," + distRadio.get(j);
                            }
                            if(mTodas) {
                                mSub.add(NetworkUtil.getRetrofit().getAlertasDistrito(auxDistrito)
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                                        .subscribe(this::handleResponse, this::handleError));
                            }
                            else {
                                mSub.add(NetworkUtil.getRetrofit().getAlertasDistritoCategoria(auxDistrito, mHayCategorias)
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                                        .subscribe(this::handleResponse, this::handleError));
                            }
                        }
                        i++;
                    }
                }
            }else{
                Toast.makeText(getActivity(), "Erroooooor", Toast.LENGTH_LONG).show();
            }
            onConnectionFinished();
        }, volleyError -> onConnectionFailed(volleyError.toString()));
        addToQueue(request);
    }

    public void addToQueue(Request request) {
        if (request != null) {
            request.setTag(this);
            if (fRequestQueue == null)
                fRequestQueue = volley.getRequestQueue();
            request.setRetryPolicy(new DefaultRetryPolicy(
                    10000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
            ));
            onPreStartConnection();
            fRequestQueue.add(request);
        }
    }

    public void onPreStartConnection() {
        getActivity().setProgressBarIndeterminateVisibility(true);
    }

    public void onConnectionFinished() {
        getActivity().setProgressBarIndeterminateVisibility(false);
    }

    public void onConnectionFailed(String error) {
        getActivity().setProgressBarIndeterminateVisibility(false);
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (AlertasFragmento.OnFragmentInteractionListener) context;
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
