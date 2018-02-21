<?php
require 'vendor/autoload.php';
class claseAlertas {

	public $dbhost ="localHost";

	public function conexion(){
		$client = new MongoDB\Client;
		//DB
		$db = $client->noticias;
		$coleccion = $db->alertas;

		return $coleccion;
		
	}

    public function obtenerAlertas($distrito, $categorias) {
   		$coleccion = $this->conexion();
   		//echo "Hola";
    	//Query para ver alertas de distritos -> habria que añadir la condicion de fecha
    	$documento = $coleccion->find(['distrito' => $distrito, 'categoria'=>array('$in' => $categorias)]);
    	$total = $coleccion->count(['distrito' => $distrito, 'categoria'=>array('$in' => $categorias)]);
    	if($total > 0){
	    	foreach ($documento as $doc) {
	    		# code...
	    		$distrito = $doc->distrito;
	    		$texto = $doc->alerta;
	    		$fecha = $doc->fecha;
	    		$categoria = $doc->categoria;
	    		$autor = $doc->fuente;
	    		$url = $doc->url;
	    		if(isset($doc->veridico)){
	    			$verificado = $doc->veridico;
	    		}
	    		else{
	    			$verificado = true;
	    		}
	    		$this->mostrarAlertas($distrito, $fecha, $texto, $categoria, $autor, $url, $verificado);
	    		
	    	}
	    	return true;
	    }
	    else{
	    	return false;
	    }

    	
    }


    // Muestra todas las alertas de un distrito (sin tener en cuenta categorías)
    public function obtenerAlertasDistrito($distrito) {
   		$coleccion = $this->conexion();
   		
    	$documento = $coleccion->find(['distrito' => $distrito]);
    	$total = $coleccion->count(['distrito' => $distrito]);
    	if($total > 0){
	    	foreach ($documento as $doc) {
	    		# code...
	    		$distrito = $doc->distrito;
	    		$texto = $doc->alerta;
	    		$fecha = $doc->fecha;
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
	    		$this->mostrarAlertas($distrito, $fecha, $texto, $categoria, $autor, $url, $verificado);
	    		
	    	}
	    	return true;
	    }
	    else{
	    	return false;
	    }

    	
    }

    //Obtiene el numero de alertas de todos los distritos
    public function obtenerNumeroAlertas($distrito) {
   	  $coleccion = $this->conexion();

      $total = $coleccion->count(['distrito' => $distrito]);
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
      $totalArganzuela= $this->obtenerNumeroAlertas("Arganzuela");
      $totalBarajas = $this->obtenerNumeroAlertas("Barajas");
      $totalCarabanchel = $this->obtenerNumeroAlertas("Carabanchel");
      $totalCentro = $this->obtenerNumeroAlertas("Centro");
      $totalChamartin = $this->obtenerNumeroAlertas("Chamartín");
      $totalChamberi = $this->obtenerNumeroAlertas("Chamberí");
      $totalCiudadLineal = $this->obtenerNumeroAlertas("Ciudad Lineal");
      $totalFuencarral = $this->obtenerNumeroAlertas("Fuencarral-ElPardo");
      $totalHortaleza = $this->obtenerNumeroAlertas("Hortaleza");
      $totalLatina = $this->obtenerNumeroAlertas("Latina");
      $totalMoncloa = $this->obtenerNumeroAlertas("Moncloa-Aravaca");
      $totalMoratalaz = $this->obtenerNumeroAlertas("Moratalaz");
      $totalPuenteVallecas = $this->obtenerNumeroAlertas("Puente de Vallecas");
      $totalRetiro = $this->obtenerNumeroAlertas("Retiro");
      $totalSalamanca = $this->obtenerNumeroAlertas("Salamanca");
      $totalSanBlas = $this->obtenerNumeroAlertas("San Blas-Canillejas");
      $totalTetuan = $this->obtenerNumeroAlertas("Tetuán");
      $totalUsera = $this->obtenerNumeroAlertas("Usera");
      $totalVicalvaro = $this->obtenerNumeroAlertas("Vicálvaro");
      $totalVillaVallecas = $this->obtenerNumeroAlertas("Villa de Vallecas");
      $totalVillaverde = $this->obtenerNumeroAlertas("Villaverde");
      $lista = [$totalArganzuela, $totalBarajas, $totalCarabanchel, $totalCentro, $totalChamartin, $totalChamberi, $totalCiudadLineal, $totalFuencarral, $totalHortaleza, $totalLatina, $totalMoncloa, $totalMoratalaz, $totalPuenteVallecas, $totalRetiro, $totalSalamanca, $totalSanBlas, $totalTetuan, $totalUsera, $totalVicalvaro, $totalVillaVallecas, $totalVillaverde];
      return $lista;
    }

    public function crearCamposOcultosMarcadores($distrito){
      	echo '<input type="hidden" name="distrito" value='.$distrito.' id="distrito"/>';
    }

    public function obtenerDatosMarcadores($distrito){
    	return $distritoMapa = $this->obtenerAlertasDistrito($distrito);
    }


    //Funcion que inserta las alertas creadas por un usuario
    public function insertarAlerta($nombre, $categoria, $distrito, $alerta){
    	$coleccion = $this->conexion();
    	$fecha = strftime("%d-%m-%Y %H:%M:%S", time());
    	$documento = ['alerta'=> $alerta, 'fecha'=>$fecha, 'distrito'=>$distrito, 'categoria'=>$categoria,'fuente'=>$nombre, 'veridico'=>false, 'url'=> NULL];
    	$coleccion->insertOne($documento);
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
                        echo '<span class="sl-date"> '.$fecha.' </span>';
                        	echo "<p></p>";
                        	echo '<div class="m-t-20 row">
                        			<div class="col-md-1">';
                        	if($verificado != false){
                        		echo '<i class="mdi mdi-verified"></i></div>';
                        	}
                        	else{
                        		echo '</div>';
                        	}
                        	if ($url != Null){
                            	echo "<a href=".$url.">".$texto."</a>";
                            }else{
                            	echo "$texto";
                            }
                            echo '</div>';
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
    		
                <div class="form-group">
                    <label class="col-sm-12">Selecciona un distrito</label>
                    <div class="col-sm-12">      
						<select class="form-control form-control-line" name="distritos" id="distritos">
							<option>Arganzuela</option>
							<option>Barajas</option>
							<option>Carabanchel</option>
							<option>Centro</option>
							<option>Chamartín</option>
							<option>Chamberí</option>
							<option>Ciudad Lineal</option>
							<option>Fuencarral-El Pardo</option>
							<option>General</option>
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
					</div>                                      
                </div>
                                    
			 </html>';
    }

   public function mostrarCategorias(){
    	echo '<html>
    		
                <div class="form-group">
                    <label class="col-sm-12">Selecciona un categoria</label>
                    <div class="col-sm-12">      
						<select class="form-control form-control-line" name="categorias" id="categorias">
                            <option>Contaminación</option>
                            <option>Criminalidad</option>
                            <option>Desastres y accidentes</option>
                            <option>Eventos</option>
                            <option>Terrorismo</option>
                            <option>Tráfico</option>
                            <option>Transporte público</option>
						</select>
					</div>                                      
                </div>
                                    
			 </html>';
    }

}
?>