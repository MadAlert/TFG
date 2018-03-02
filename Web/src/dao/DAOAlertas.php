<?php
require 'vendor/autoload.php';
require_once("Conexion.php");


class DAOAlertas {
	public $dbhost ="localHost";
	private $con;

	function __construct() {
		$conexion = new Conexion();
        $this->con = $conexion->conexionAlertas();
    }
	
	public function obtenerAlertasDistrito($distrito) {
    	$documento = $this->con->find(['distrito' => $distrito],['sort' => ['fecha' => -1]]);
    	return $documento;
	}

	public function totalAlertasDistrito($distrito){
		$total = $this->con->count(['distrito' => $distrito]);
		return $total;
	}

	public function obtenerAlertas($distrito, $categorias){
		$documento = $this->con->find(['distrito' => $distrito, 'categoria'=>array('$in' => $categorias)], ['sort' => ['fecha' => -1]]);
		return $documento;
	}

	public function obtenerNumeroAlertas($distrito){
		$total = $this->con->count(['distrito' => $distrito]);
		return $total;
	}

    public function totalObtenerAlertas($distrito, $categorias){
    	$total = $this->con->count(['distrito' => $distrito, 'categoria'=>array('$in' => $categorias)]);
    	return $total;
    }

    public function insertarAlerta($doc){
    	$this->con->insertOne($doc);
    }
}
?>

    