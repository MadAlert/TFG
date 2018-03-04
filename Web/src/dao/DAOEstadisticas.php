<?php
require 'vendor/autoload.php';
require_once("Conexion.php");


class DAOEstadisticas {
	public $dbhost = "localhost";
	private $conEst, $conEstDet, $conEstAcc, $conEstSeg;

	function __construct() {
		$conexion = new Conexion();
		$this->conEst = $conexion->conexionEstadisticas();
		$this->conEstDet = $conexion->conexionEstDetenidos();
		$this->conEstAcc = $conexion->conexionEstAccidentes();
		$this->conEstSeg = $conexion->conexionEstSeg();
	}

	//Devuelve el numero total de alertas en un distrito con una categoria dada 
	public function obtenerEstadisticas($distrito, $categorias, $mes) {
   		//Query para ver alertas de distritos -> habria que añadir la condicion de fecha
   		$documento = $this->conEst->count(['distrito' => $distrito, 'categoria'=> $categorias , 'mes' => $mes]);
    	return $documento;
    }

   //Devuelve el numero total de alertas en un distrito con una categoria dada 
	public function obtenerEstadisticasSinMes($distrito, $categorias) {
      //Query para ver alertas de distritos -> habria que añadir la condicion de fecha
      $documento = $this->conEst->count(['distrito' => $distrito, 'categoria'=> $categorias ]);
      return $documento;
    }

    public function obtenerEstSeguridad($distrito, $tipo) {
      $documento = $this->conEstSeg->findOne(array('distrito' => $distrito), array($tipo));
      return $documento[$tipo];
    }

    public function obtenerEstDetenidos($distrito, $detenidos){
      $documento = $this->conEstDet->findOne(array('distrito' => $distrito), array($detenidos));      
      return $documento[$detenidos];
    }

    public function obtenerEstAccidentes($distrito, $conHeridos, $sinHeridos){
      $documento = $this->conEstAcc->findOne(array('distrito' => $distrito), array($conHeridos, $sinHeridos));
      return $documento;
    }

    public function obtenerMesEstPolicias($distrito, $mes){
      $documento = $this->conEstDet->findOne(array('distrito' => $distrito), array($mes));
      return $documento[$mes];
    }

    public function insertarEstadistica($doc){
    	$this->conEst->insertOne($doc);
    }

}

?>