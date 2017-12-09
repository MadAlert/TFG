<?php
include ("claseAlertas.php");
$alertas = new claseAlertas();

$nombre = $_POST['nombre'];
$email = $_POST['email'];
$categoria = $_POST['categoria'];
$distrito = $_POST['distrito'];
$alerta = $_POST['alerta'];

$alertas->insertarAlerta($nombre, $categoria, $distrito, $alerta);

header('Location: aniadirAlertas.php');
?>