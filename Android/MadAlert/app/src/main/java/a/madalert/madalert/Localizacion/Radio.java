package a.madalert.madalert.Localizacion;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Nerea on 20/05/2018.
 */

public final class Radio {
    private static HashMap<String, ArrayList<Pair<Double, Double>>> distCoord;

    public static HashMap<String, ArrayList<Pair<Double, Double>>> initCoord(){
        distCoord = new HashMap<String, ArrayList<Pair<Double, Double>>>();
        ArrayList<Pair<Double, Double>> coordenadas = new ArrayList<>();
        inicializarTodos(coordenadas);
        coordenadas = new ArrayList<>();
        inicializarArganzuela(coordenadas);
        coordenadas = new ArrayList<>();
        inicializarBarajas(coordenadas);
        coordenadas = new ArrayList<>();
        inicializarCarabanchel(coordenadas);
        coordenadas = new ArrayList<>();
        inicializarCentro(coordenadas);
        coordenadas = new ArrayList<>();
        inicializarChamartin(coordenadas);
        coordenadas = new ArrayList<>();
        inicializarChamberi(coordenadas);
        coordenadas = new ArrayList<>();
        inicializarCiudadLineal(coordenadas);
        coordenadas = new ArrayList<>();
        inicializarFuencarralPardo(coordenadas);
        coordenadas = new ArrayList<>();
        inicalizarGeneral(coordenadas);
        coordenadas = new ArrayList<>();
        inicializarHortaleza(coordenadas);
        coordenadas = new ArrayList<>();
        inicializarLatina(coordenadas);
        coordenadas = new ArrayList<>();
        incializarMoncloaAravaca(coordenadas);
        coordenadas = new ArrayList<>();
        inicializarMoratalaz(coordenadas);
        coordenadas = new ArrayList<>();
        incializarPuenteDeVallecas(coordenadas);
        coordenadas = new ArrayList<>();
        inicializarRetiro(coordenadas);
        coordenadas = new ArrayList<>();
        inicializarSalamanca(coordenadas);
        coordenadas = new ArrayList<>();
        inicializarSanBlasCanillejas(coordenadas);
        coordenadas = new ArrayList<>();
        inicializarTetuan(coordenadas);
        coordenadas = new ArrayList<>();
        inicializarUsera(coordenadas);
        coordenadas = new ArrayList<>();
        inicializarVicalvaro(coordenadas);
        coordenadas = new ArrayList<>();
        inicializarVillaDeVallecas(coordenadas);
        coordenadas = new ArrayList<>();
        inicializarVillaverde(coordenadas);
        return distCoord;
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

    public static ArrayList obtenerDistritosRadio(HashMap<String, ArrayList<Pair<Double, Double>>> coordenadas, int kms, Double parsLat, Double parsLong){
        coordenadas = initCoord();
        ArrayList distRadio = new ArrayList<>();
        boolean marcadorEncontrado;
        Iterator<Map.Entry<String, ArrayList<Pair<Double, Double>>>> iterator = coordenadas.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, ArrayList<Pair<Double, Double>>> it = iterator.next(); // iterador del HashMap
            if(!it.getKey().equals("Todos") && !it.getKey().equals("General")) {
                ArrayList<Pair<Double, Double>> listAux = it.getValue(); // obtengo el arrayList del distrito que estoy iterando
                Iterator<Pair<Double, Double>> itArrayList = listAux.iterator(); // iterador del arrayList
                marcadorEncontrado = false;
                while (itArrayList.hasNext() && !marcadorEncontrado) {
                    Pair<Double, Double> parAux = itArrayList.next();
                    Double lat = parAux.first;
                    Double longi = parAux.second;
                    Double var = distanciaCoord(parsLat, parsLong, lat, longi);
                    if (var <= kms) {
                        distRadio.add(it.getKey());
                        marcadorEncontrado = true; // en el momento en el que encuentre una coordenada dentro, ya no es necesario buscar mas
                    }
                }
            }
        }
        return distRadio;
    }

    private static void inicializarVillaverde(ArrayList<Pair<Double, Double>> coordenadas) {
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

    private static void inicializarVillaDeVallecas(ArrayList<Pair<Double, Double>> coordenadas) {
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
    }

    private static void inicializarUsera(ArrayList<Pair<Double, Double>> coordenadas) {
        coordenadas.add(new Pair<>(40.377026, -3.701982));
        coordenadas.add(new Pair<>(40.3929487,-3.705566));
        coordenadas.add(new Pair<>(40.3829065,-3.7170111));
        coordenadas.add(new Pair<>(40.3666549,-3.7191074));
        coordenadas.add(new Pair<>(40.3652534,-3.7029396));
        coordenadas.add(new Pair<>(40.3644353,-3.6900679));
        coordenadas.add(new Pair<>(40.3794643,-3.6924083));
        coordenadas.add(new Pair<>(40.3936308,-3.7045326));
        distCoord.put("Usera", coordenadas);
    }

    private static void inicializarVicalvaro(ArrayList<Pair<Double, Double>> coordenadas) {
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
    }

    private static void inicializarTetuan(ArrayList<Pair<Double, Double>> coordenadas) {
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
    }

    private static void inicializarSanBlasCanillejas(ArrayList<Pair<Double, Double>> coordenadas) {
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
    }

    private static void inicializarTodos(ArrayList<Pair<Double, Double>> coordenadas) {
        coordenadas.add(new Pair<>(40.4420755, -3.7458086));
        distCoord.put("Todos", coordenadas);
    }

    private static void inicializarArganzuela( ArrayList<Pair<Double, Double>> coordenadas){
        coordenadas.add(new Pair<>(40.400861, -3.699350));
        coordenadas.add(new Pair<>(40.412284, -3.721580));
        coordenadas.add(new Pair<>(40.4042021, -3.7181038));
        coordenadas.add(new Pair<>(40.3911617, -3.7025685));
        coordenadas.add(new Pair<>(40.3853107, -3.6929554));
        coordenadas.add(new Pair<>(40.3951166, -3.7017531));
        coordenadas.add(new Pair<>(40.4035485, -3.6980838));
        coordenadas.add(new Pair<>(40.4044309, -3.7117953));
        distCoord.put("Arganzuela", coordenadas);
    }
    private static void inicializarSalamanca(ArrayList<Pair<Double, Double>> coordenadas) {
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
    }

    private static void inicializarRetiro(ArrayList<Pair<Double, Double>> coordenadas) {
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
    }

    private static void incializarPuenteDeVallecas(ArrayList<Pair<Double, Double>> coordenadas) {
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
    }

    private static void inicializarMoratalaz(ArrayList<Pair<Double, Double>> coordenadas) {
        coordenadas.add(new Pair<>(40.407016, -3.644330));
        coordenadas.add(new Pair<>(40.4066104,-3.6658144));
        coordenadas.add(new Pair<>(40.4107957,-3.6627519));
        coordenadas.add(new Pair<>(40.4136321,-3.6447327));
        coordenadas.add(new Pair<>(40.4121581,-3.6262237));
        coordenadas.add(new Pair<>(40.4022133,-3.6287256));
        coordenadas.add(new Pair<>(40.394843,-3.633604));
        coordenadas.add(new Pair<>(40.4021715,-3.6509371));
        distCoord.put("Moratalaz", coordenadas);
    }

    private static void incializarMoncloaAravaca(ArrayList<Pair<Double, Double>> coordenadas) {
        coordenadas.add(new Pair<>(40.443568,-3.742829));
        coordenadas.add(new Pair<>(40.423416,-3.7122597));
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
        coordenadas.add(new Pair<>(40.4371129,-3.7216419));
        distCoord.put("Moncloa-Aravaca", coordenadas);
    }

    private static void inicializarLatina(ArrayList<Pair<Double, Double>> coordenadas) {
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
    }

    private static void inicalizarGeneral(ArrayList<Pair<Double, Double>> coordenadas) {
        coordenadas.add(new Pair<>(40.416595,-3.7059791));
        distCoord.put("General", coordenadas);
    }

    private static void inicializarHortaleza(ArrayList<Pair<Double, Double>> coordenadas) {
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
    }

    private static void inicializarFuencarralPardo(ArrayList<Pair<Double, Double>> coordenadas) {
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
    }

    private static void inicializarCiudadLineal(ArrayList<Pair<Double, Double>> coordenadas) {
        coordenadas.add(new Pair<>(40.455531, -3.656119));
        coordenadas.add(new Pair<>(40.4816562,-3.6702447));
        coordenadas.add(new Pair<>(40.4689737,-3.6705823));
        coordenadas.add(new Pair<>(40.437791,-3.6611025));
        coordenadas.add(new Pair<>(40.4199269,-3.657214));
        coordenadas.add(new Pair<>(40.4148774,-3.6260929));
        coordenadas.add(new Pair<>(40.4378572,-3.6376626));
        coordenadas.add(new Pair<>(40.4600702,-3.6599855));
        distCoord.put("Ciudad Lineal", coordenadas);
    }

    private static void inicializarChamberi(ArrayList<Pair<Double, Double>> coordenadas) {
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
    }

    private static void inicializarChamartin(ArrayList<Pair<Double, Double>> coordenadas) {
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
    }

    private static void inicializarCentro(ArrayList<Pair<Double, Double>> coordenadas) {
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
    }

    private static void inicializarCarabanchel(ArrayList<Pair<Double, Double>> coordenadas) {
        coordenadas.add(new Pair<>(40.381607, -3.735203));
        coordenadas.add(new Pair<>(40.3629794, -3.7677715));
        coordenadas.add(new Pair<>(40.3708105, -3.7617848));
        coordenadas.add(new Pair<>(40.3800955,-3.7421296));
        coordenadas.add(new Pair<>(40.3898531,-3.7211869));
        coordenadas.add(new Pair<>(40.3959653,-3.7242554));
        coordenadas.add(new Pair<>(40.3823838,-3.755884));
        coordenadas.add(new Pair<>(40.3729929,-3.7599287));
        distCoord.put("Carabanchel", coordenadas);
    }

    private static void inicializarBarajas(ArrayList<Pair<Double, Double>> coordenadas) {
        coordenadas.add(new Pair<>(40.4839402, -3.5701402));
        coordenadas.add(new Pair<>(404669605., -3.6318945));
        coordenadas.add(new Pair<>(40.4545846, -3.6084184));
        coordenadas.add(new Pair<>(40.454716, -3.565998));
        coordenadas.add(new Pair<>(40.4581447, -3.5399055));
        coordenadas.add(new Pair<>(40.4894846, -3.5513853));
        coordenadas.add(new Pair<>(40.510900, -3.568144));
        coordenadas.add(new Pair<>(40.4931808, -3.5981631));
        distCoord.put("Barajas", coordenadas);
    }

}
