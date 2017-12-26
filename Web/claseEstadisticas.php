<?php
require 'vendor/autoload.php';
class claseEstadisticas {

	public $dbhost ="localHost";


	public function conexion(){
		$client = new MongoDB\Client;
		//DB
		$db = $client->noticias;
		$coleccion = $db->estadisticas;

		return $coleccion;
		
	}

    public function conexionEstSeg(){
    $client = new MongoDB\Client;
    //DB
    $db = $client->noticias;
    $coleccion = $db->estSeguridad;

    return $coleccion;
  }

	//Devuelve el numero total de alertas en un distrito con una categoria dada 
   public function obtenerEstadisticas($distrito, $categorias, $mes) {
   		$coleccion = $this->conexion();
   		//echo "Hola";
    	//Query para ver alertas de distritos -> habria que añadir la condicion de fecha
    	$total = $coleccion->count(['distrito' => $distrito, 'categoria'=> $categorias , 'mes' => $mes]);
    	return $total;
    }

    //Devuelve el numero total de alertas en un distrito con una categoria dada 
   public function obtenerEstadisticasSinMes($distrito, $categorias) {
      $coleccion = $this->conexion();
      //echo "Hola";
      //Query para ver alertas de distritos -> habria que añadir la condicion de fecha
      $total = $coleccion->count(['distrito' => $distrito, 'categoria'=> $categorias ]);
      return $total;
    }

    /*public function obtenerEstSeguridad($distrito, $personas, $patrimonio, $armas, $ten_drogas, $con_drogas) {
      $coleccion = $this->conexionEstSeg();
      $total = $coleccion->['distrito' => $distrito, 'personas'=> $personas, 'patrimonio' => $patrimonio
                                   'armas'=> $armas, 'ten_drogas'=>$ten_drogas, 'con_drogas'=>$con_drogas];
      return $total;
    }*/

    public function obtenerEstSeguridad($distrito, $tipo) {
      $coleccion = $this->conexionEstSeg();
      $total = $coleccion->findOne(array('distrito' => $distrito), array($tipo));
      return $total[$tipo];
    }

    //Funcion que inserta las alertas creadas por un usuario
    public function insertarEstadistica($categoria, $distrito, $mes){
    	$coleccion = $this->conexion();
    	$fecha = strftime("%m", time());
    	$documento = ['fecha'=>$fecha, 'distrito'=>$distrito, 'categoria'=>$categoria,'fuente'=>$nombre, 'veridico'=>false, 'url'=> NULL];
    	$coleccion->insertOne($documento);
    }

    public function obtenerMes($i){
      $mesAnterior1 = date('m', strtotime('-1 month'));
      $mesAnterior2 = date('m', strtotime('-2 month'));
      if($i==1){
          $mes=$mesAnterior1;
      }
      else{
        $mes = $mesAnterior2;
      }
      return $mes;
    }
    
    public function crearCamposOcultosPrimerMes($distrito, $lista, $mes){
      echo ' <input type="hidden" name="distrito" value='.$distrito.' id="distrito"/>';
      echo ' <input type="hidden" name="terrorismo" value='.$lista[0].' id="terrorismo"/>';
      echo ' <input type="hidden" name="desastres" value='.$lista[1].' id="desastres"/>';
      echo ' <input type="hidden" name="eventos" value='.$lista[2].' id="eventos"/>';
      echo ' <input type="hidden" name="transporte" value='.$lista[3].' id="transporte"/>';
      echo ' <input type="hidden" name="criminalidad" value='.$lista[4].' id="criminalidad"/>';
      echo ' <input type="hidden" name="contaminacion" value='.$lista[5].' id="contaminacion"/>';
      echo ' <input type="hidden" name="trafico" value='.$lista[6].' id="trafico"/>';
      echo ' <input type="hidden" name="mes1" value='.$mes.' id="mes1"/>';

    }

    public function crearCamposOcultosSegundoMes($distrito, $lista , $mes){
      echo ' <input type="hidden" name="distrito" value='.$distrito.' id="distrito"/>';
      echo ' <input type="hidden" name="terrorismo2" value='.$lista[0].' id="terrorismo2"/>';
      echo ' <input type="hidden" name="desastres2" value='.$lista[1].' id="desastres2"/>';
      echo ' <input type="hidden" name="eventos2" value='.$lista[2].' id="eventos2"/>';
      echo ' <input type="hidden" name="transporte2" value='.$lista[3].' id="transporte2"/>';
      echo ' <input type="hidden" name="criminalidad2" value='.$lista[4].' id="criminalidad2"/>';
      echo ' <input type="hidden" name="contaminacion2" value='.$lista[5].' id="contaminacion2"/>';
      echo ' <input type="hidden" name="trafico2" value='.$lista[6].' id="trafico2"/>';
      echo ' <input type="hidden" name="mes2" value='.$mes.' id="mes2"/>';

    }

    public function noHayEstadisticas($lista){
      if($lista[0] == 0 && $lista[1]==0 && $lista[2]==0 && $lista[3]==0 && $lista[4]==0 && $lista[5]==0 && $lista[6]==0){
                return true;                  
      }
      else{
                return false;
      }
    }

    public function obtenerDatos ($distrito, $mes){
      $totalTerrorismo= $this->obtenerEstadisticas($distrito, "Terrorismo",$mes);
      $totalDesastres = $this->obtenerEstadisticas($distrito, "Desastres y accidentes",$mes);
      $totalEventos= $this->obtenerEstadisticas($distrito, "Eventos",$mes);
      $totalTransporte= $this->obtenerEstadisticas($distrito, "Transporte público",$mes);
      $totalCriminalidad= $this->obtenerEstadisticas($distrito, "Criminalidad",$mes);
      $totalContaminacion= $this->obtenerEstadisticas($distrito, "Contaminación",$mes);
      $totalTrafico= $this->obtenerEstadisticas($distrito, "Tráfico",$mes);
      $lista = [$totalTerrorismo,$totalDesastres,$totalEventos,$totalTransporte,$totalCriminalidad,$totalContaminacion,$totalTrafico];
      return $lista;
    }
     
    public function mesEnLetras($mes){
      $meses  = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
        return $meses[$mes-1];
    }                   

}
?>