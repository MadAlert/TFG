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

    /*public function obtenerEstSeguridad($distrito, $personas, $patrimonio, $armas, $ten_drogas, $con_drogas) {
      $coleccion = $this->conexionEstSeg();
      $total = $coleccion->['distrito' => $distrito, 'personas'=> $personas, 'patrimonio' => $patrimonio
                                   'armas'=> $armas, 'ten_drogas'=>$ten_drogas, 'con_drogas'=>$con_drogas];
      return $total;
    }*/

    public function obtenerEstSeguridadPersona($distrito) {
      $coleccion = $this->conexionEstSeg();
      $total = $coleccion->findOne(['distrito' => $distrito]).personas;
      return int($total);
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
    
    public function crearCamposOcultos($distrito, $totalTerrorismo, $totalEventos, $totalDesastres, $totalCriminalidad, $totalTransporte, $totalContaminacion, $totalTrafico, $i){
      echo ' <input type="hidden" name="distrito" value='.$distrito.' id="distrito"/>';
      echo ' <input type="hidden" name="terrorismo" value='.$totalTerrorismo.' id="terrorismo"/>';
      echo ' <input type="hidden" name="desastres" value='.$totalDesastres.' id="desastres"/>';
      echo ' <input type="hidden" name="eventos" value='.$totalEventos.' id="eventos"/>';
      echo ' <input type="hidden" name="transporte" value='.$totalTransporte.' id="transporte"/>';
      echo ' <input type="hidden" name="terrorismo" value='.$totalTerrorismo.' id="terrorismo"/>';
      echo ' <input type="hidden" name="criminalidad" value='.$totalCriminalidad.' id="criminalidad"/>';
      echo ' <input type="hidden" name="contaminacion" value='.$totalContaminacion.' id="contaminacion"/>';
      echo ' <input type="hidden" name="trafico" value='.$totalTrafico.' id="trafico"/>';
      if($i==1){
            echo ' <input type="hidden" name="nombre" value="piechart1" id="nombre"/>';
      }
      else{
            echo ' <input type="hidden" name="nombre" value="piechart2" id="nombre"/>';
      }
    }

    public function noHayEstadisticas($totalTerrorismo, $totalEventos, $totalDesastres, $totalCriminalidad, $totalTransporte, $totalContaminacion, $totalTrafico){
      if($totalTrafico == 0 && $totalTerrorismo==0 && $totalDesastres==0 && $totalContaminacion==0 && $totalEventos==0 && $totalTransporte==0 && $totalCriminalidad==0){
                return true;                  
      }
      else{
                return false;
      }

    }

}
?>