<?php
include ("claseAlertas.php");
include ("Clasificador.php");
$alertas = new claseAlertas();

$nombre = $_POST['nombre'];
$email = $_POST['email'];
$categoria = $_POST['categorias'];
$distrito = $_POST['distritos'];
$alerta = $_POST['alerta'];

$clasificador = new Clasificador();
$sol = $clasificador->obtenerClasificacion($alerta);

if($sol!=false && $sol==$categoria){
	$alertas->insertarAlerta($nombre, $categoria, $distrito, $alerta);
	echo"1";
}
else{
	echo"0";
}
?>