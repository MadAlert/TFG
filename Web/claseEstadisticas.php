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
   public function obtenerEstadisticas($distrito, $categorias) {
   		$coleccion = $this->conexion();
   		//echo "Hola";
    	//Query para ver alertas de distritos -> habria que añadir la condicion de fecha
    	$total = $coleccion->count(['distrito' => $distrito, 'categoria'=> $categorias]);
    	return $total;
    }

    /*public function obtenerEstSeguridad($distrito, $personas, $patrimonio, $armas, $ten_drogas, $con_drogas) {
      $coleccion = $this->conexionEstSeg();
      $total = $coleccion->['distrito' => $distrito, 'personas'=> $personas, 'patrimonio' => $patrimonio
                                   'armas'=> $armas, 'ten_drogas'=>$ten_drogas, 'con_drogas'=>$con_drogas];
      return $total;
    }*/

    public function obtenerEstSeguridadPersona($distrito, $personas) {
      $coleccion = $this->conexionEstSeg();
      $total = $coleccion->find(['distrito' => $distrito, 'personas' => $personas]);
      return $total;
    }

    //Funcion que inserta las alertas creadas por un usuario
    public function insertarEstadistica($categoria, $distrito, $mes){
    	$coleccion = $this->conexion();
    	$fecha = strftime("%m", time());
    	$documento = ['fecha'=>$fecha, 'distrito'=>$distrito, 'categoria'=>$categoria,'fuente'=>$nombre, 'veridico'=>false, 'url'=> NULL];
    	$coleccion->insertOne($documento);
    }

}
?>