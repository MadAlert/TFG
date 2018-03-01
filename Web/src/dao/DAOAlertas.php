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
    	$documento = $this->con->find(['distrito' => $distrito]);
    	return $documento;
	}

	public function totalAlertasDistrito($distrito){
		$total = $this->con->count(['distrito' => $distrito]);
		return $total;
	}

	public function obtenerAlertas($distrito, $categoria){
		$documento = $this->con->find(['distrito' => $distrito, 'categoria'=>array('$in' => $categorias)]);
		return $documento;
	}

	public function obtenerNumeroAlertas($distrito){
		$total = $this->con->count(['distrito' => $distrito]);
		return $total;
	}

    public function totalObtenerAlertas($distrito, $categoria){
    	$total = $this->con->count(['distrito' => $distrito, 'categoria'=>array('$in' => $categorias)]);
    	return $total;
    }

    public function insertarAlerta($doc){
    	$this->con->insertOne($doc);
    }
}
?>

    