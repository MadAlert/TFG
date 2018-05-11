<?php
require 'vendor/autoload.php';
require_once("src/dao/DAOalertas.php");
class claseAlertas {

	public $dbhost ="localHost";
	public $daoAler;

    function __construct() {
		  $this->daoAler = new DAOAlertas();
    }

    public function obtenerAlertas($distrito, $categorias) {
    	//Query para ver alertas de distritos -> habria que añadir la condicion de fecha
    	$documento = $this->daoAler->obtenerAlertas($distrito, $categorias);
    	$total = $this->daoAler->totalObtenerAlertas($distrito, $categorias);
      $i =0;
    	if($total > 0){
	    	foreach ($documento as $doc) {
          $fecha =  $doc->fecha;
          $fechaDate=$fecha->toDateTime();
          $fechaString = $fechaDate->format('H:i:s d-m-Y');
	    		$distrito = $doc->distrito;
	    		$texto = $doc->alerta;
	    		$categoria = $doc->categoria;
	    		$autor = $doc->fuente;
	    		$url = $doc->url;
	    		if(isset($doc->veridico)){
	    			$verificado = $doc->veridico;
	    		}
	    		else{
	    			$verificado = true;
	    		}
	    		$this->mostrarAlertas($distrito, $fechaString, $texto, $categoria, $autor, $url, $verificado);
	    		
	    	}
	    	return true;
	    }
	    else{
	    	return false;
	    }

    	
    }


    // Muestra todas las alertas de un distrito (sin tener en cuenta categorías)
    public function obtenerAlertasDistrito($distrito) {
   		$documento = $this->daoAler->obtenerAlertasDistrito($distrito);
    	$total = $this->daoAler->totalAlertasDistrito($distrito);
    	if($total > 0){
	    	foreach ($documento as $doc) {
	    		# code...
	    		$fecha =  $doc->fecha;
	    		$fechaDate=$fecha->toDateTime();
	    		$fechaString = $fechaDate->format('H:i:s d-m-Y');
	    		$distrito = $doc->distrito;
	    		$texto = $doc->alerta;
	    		$categoria = $doc->categoria;
	    		$autor = $doc->fuente;
	    		if(isset($doc->url)){
	    			$url = $doc->url;
	    		}
	    		else{
	    			$url = null;
	    		}
	    		if(isset($doc->veridico)){
	    			$verificado = $doc->veridico;
	    		}
	    		else{
	    			$verificado = true;
	    		}
	    		$this->mostrarAlertas($distrito, $fechaString, $texto, $categoria, $autor, $url, $verificado);
	    		
	    	}
	    	return true;
	    }
	    else{
	    	return false;
	    }

    	
    }

    //Obtiene el numero de alertas de todos los distritos
    public function obtenerNumeroAlertas($distrito) {
   	  $total = $this->daoAler->obtenerNumeroAlertas($distrito);

      return $total;	
    }

    public function crearCamposOcultosNumAlertas($lista){
      echo ' <input type="hidden" name="arganzuela" value='.$lista[0].' id="arganzuela"/>';
      echo ' <input type="hidden" name="barajas" value='.$lista[1].' id="barajas"/>';
      echo ' <input type="hidden" name="carabanchel" value='.$lista[2].' id="carabanchel"/>';
      echo ' <input type="hidden" name="centro" value='.$lista[3].' id="centro"/>';
      echo ' <input type="hidden" name="chamartin" value='.$lista[4].' id="chamartin"/>';
      echo ' <input type="hidden" name="chamberi" value='.$lista[5].' id="chamberi"/>';
      echo ' <input type="hidden" name="ciudadlineal" value='.$lista[6].' id="ciudadlineal"/>';
      echo ' <input type="hidden" name="fuencarral" value='.$lista[7].' id="fuencarral"/>';
      echo ' <input type="hidden" name="hortaleza" value='.$lista[8].' id="hortaleza"/>';
      echo ' <input type="hidden" name="latina" value='.$lista[9].' id="latina"/>';
      echo ' <input type="hidden" name="moncloa" value='.$lista[10].' id="moncloa"/>';
      echo ' <input type="hidden" name="moratalaz" value='.$lista[11].' id="moratalaz"/>';
      echo ' <input type="hidden" name="puentevallecas" value='.$lista[12].' id="puentevallecas"/>';
      echo ' <input type="hidden" name="retiro" value='.$lista[13].' id="retiro"/>';
      echo ' <input type="hidden" name="salamanca" value='.$lista[14].' id="salamanca"/>';
      echo ' <input type="hidden" name="sanblas" value='.$lista[15].' id="sanblas"/>';
      echo ' <input type="hidden" name="tetuan" value='.$lista[16].' id="tetuan"/>';
      echo ' <input type="hidden" name="usera" value='.$lista[17].' id="usera"/>';
      echo ' <input type="hidden" name="vicalvaro" value='.$lista[18].' id="vicalvaro"/>';
      echo ' <input type="hidden" name="villavallecas" value='.$lista[19].' id="villavallecas"/>';
      echo ' <input type="hidden" name="villaverde" value='.$lista[20].' id="villaverde"/>';
    }

    public function obtenerDatos (){
      $totalArganzuela = $this->daoAler->obtenerNumeroAlertas("Arganzuela");
      $totalBarajas = $this->daoAler->obtenerNumeroAlertas("Barajas");
      $totalCarabanchel = $this->daoAler->obtenerNumeroAlertas("Carabanchel");
      $totalCentro = $this->daoAler->obtenerNumeroAlertas("Centro");
      $totalChamartin = $this->daoAler->obtenerNumeroAlertas("Chamartín");
      $totalChamberi = $this->daoAler->obtenerNumeroAlertas("Chamberí");
      $totalCiudadLineal = $this->daoAler->obtenerNumeroAlertas("Ciudad Lineal");
      $totalFuencarral = $this->daoAler->obtenerNumeroAlertas("Fuencarral-El Pardo");
      $totalHortaleza = $this->daoAler->obtenerNumeroAlertas("Hortaleza");
      $totalLatina = $this->daoAler->obtenerNumeroAlertas("Latina");
      $totalMoncloa = $this->daoAler->obtenerNumeroAlertas("Moncloa-Aravaca");
      $totalMoratalaz = $this->daoAler->obtenerNumeroAlertas("Moratalaz");
      $totalPuenteVallecas = $this->daoAler->obtenerNumeroAlertas("Puente de Vallecas");
      $totalRetiro = $this->daoAler->obtenerNumeroAlertas("Retiro");
      $totalSalamanca = $this->daoAler->obtenerNumeroAlertas("Salamanca");
      $totalSanBlas = $this->daoAler->obtenerNumeroAlertas("San Blas-Canillejas");
      $totalTetuan = $this->daoAler->obtenerNumeroAlertas("Tetuán");
      $totalUsera = $this->daoAler->obtenerNumeroAlertas("Usera");
      $totalVicalvaro = $this->daoAler->obtenerNumeroAlertas("Vicálvaro");
      $totalVillaVallecas = $this->daoAler->obtenerNumeroAlertas("Villa de Vallecas");
      $totalVillaverde = $this->daoAler->obtenerNumeroAlertas("Villaverde");
      $lista = [$totalArganzuela, $totalBarajas, $totalCarabanchel, $totalCentro, $totalChamartin, $totalChamberi, $totalCiudadLineal, $totalFuencarral, $totalHortaleza, $totalLatina, $totalMoncloa, $totalMoratalaz, $totalPuenteVallecas, $totalRetiro, $totalSalamanca, $totalSanBlas, $totalTetuan, $totalUsera, $totalVicalvaro, $totalVillaVallecas, $totalVillaverde];
      return $lista;
    }

    public function crearCamposOcultosMarcadores($distrito){
      	echo '<input type="hidden" name="distrito" value='.$distrito.' id="distrito"/>';
    }

    public function obtenerDatosMarcadores($distrito){
    	return $distritoMapa = $this->daoAler->obtenerAlertasDistrito($distrito);
    }


    //Funcion que inserta las alertas creadas por un usuario
    public function insertarAlerta($nombre, $categoria, $distrito, $alerta){
      $fechaH = new DateTime(null, new DateTimeZone('Europe/Madrid'));

      
      date_add($fechaH, date_interval_create_from_date_string('2 hours'));
      $fecha = new MongoDB\BSON\UTCDateTime($fechaH);
    	$documento = ['alerta'=> $alerta, 'fecha'=>$fecha, 'distrito'=>$distrito, 'categoria'=>$categoria,'fuente'=>$nombre, 'veridico'=>false, 'url'=> NULL];
    	$this->daoAler->insertarAlerta($documento);    	
    }

    //Funcion que muestra una alerta con el formato correspondiente
    public function mostrarAlertas($distrito, $fecha, $texto, $categoria, $autor, $url, $verificado){
    	echo '
    	
            <div class="profiletimeline">
                <div class="sl-item">
                    <div class="sl-left"></div>
                    <div class="sl-right">
                      <div>';
                    	echo '<a class="link"><u>'.$categoria.'</u></a>';
                    	//strftime ( "%d-%m-%Y", $fecha);
                        echo '<span class="sl-date"> '.$fecha.' </span>';
                        	echo "<p></p>";
                        	echo '<div class="m-t-20 row">
                            <div class="col-md-1 col-xs-12">';
                              if($categoria == "Desastres y accidentes") {
                                  echo '<img src= "assets/images/accident.png"/>';
                              } else if($categoria == "Terrorismo") {
                                  echo '<img src= "assets/images/terrorism.png"/>';
                              } else if($categoria == "Eventos") {
                                  echo '<img src= "assets/images/events.png"/>';
                              } else if($categoria == "Contaminación") {
                                  echo '<img src= "assets/images/contamination.png"/>';
                              } else if($categoria == "Criminalidad") {
                                  echo '<img src= "assets/images/criminal.png"/>';
                              } else if($categoria == "Tráfico") {
                                  echo '<img src= "assets/images/traffic.png"/>';
                              } else if($categoria == "Transporte público") {
                                  echo '<img src= "assets/images/bus.png"/>';
                              }
                          echo '</div>';
                        			
                              //AÑADIR LOS ICONOS AQUIIIIIIIIIIIIIIIII
                        	if($verificado != false){
                            echo '<div class="col-md-1 col-xs-12">';
                        		echo '<i class="mdi mdi-verified"></i></div>';
                        	}
                          echo '<div class="col-md-10 col-xs-12">';
                                                                
                        	if ($url != Null){
                            	echo "<a href=".$url.">".$texto."</a>";
                            }else{
                            	echo "$texto";
                            }
                            echo '</div></div>';
                            echo '<p></p>';
                            echo '<div class="like-comm"> <a href="javascript:void(0)" class="link m-r-10">Fuente:</a><class="link m-r-10">' .$autor.'</div>';
                    echo '</div>';
                    echo '</div>';
                echo '</div>';
           echo ' </div>';
       echo '<hr>';
    }

    public function mostrarDistritos(){
    	echo '<html>
    		      
			<select class="form-control form-control-line" name="distritos" id="distritos">
				<option>Arganzuela</option>
				<option>Barajas</option>
				<option>Carabanchel</option>
				<option>Centro</option>
				<option>Chamartín</option>
				<option>Chamberí</option>
				<option>Ciudad Lineal</option>
				<option>Fuencarral-El Pardo</option>
				<option>Hortaleza</option>
				<option>Latina</option>
				<option>Moncloa-Aravaca</option>
				<option>Moratalaz</option>
				<option>Puente de Vallecas</option>
				<option>Retiro</option>
				<option>Salamanca</option>
				<option>San Blas-Canillejas</option>
				<option>Tetuán</option>
				<option>Usera</option>
				<option>Vicálvaro</option>
				<option>Villa de Vallecas</option>
				<option>Villaverde</option>
			</select>
                                    
			 </html>';
    }

   public function mostrarCategorias(){
    	echo '<html>
    		          
			<select class="form-control form-control-line" name="categorias" id="categorias">
        <option>Contaminación</option>
        <option>Criminalidad</option>
        <option>Desastres y accidentes</option>
        <option>Eventos</option>
        <option>Terrorismo</option>
        <option>Tráfico</option>
        <option>Transporte público</option>
			</select>
                                    
			 </html>';
    }

}
?>