<?php
include ("claseAlertas.php");
$alertas = new claseAlertas();

$nombre = $_POST['nombre'];
$email = $_POST['email'];
$categoria = $_POST['categorias'];
$distrito = $_POST['distritos'];
$alerta = $_POST['alerta'];

$alertas->insertarAlerta($nombre, $categoria, $distrito, $alerta);

header('Location: aniadirAlertas.php');
?>