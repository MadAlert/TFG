<?php
	require 'vendor/autoload.php';

    $client = new MongoDB\Client;
    //DB
    $db = $client->noticias;
    $coleccion = $db->estSeguridad;
     
   $total = $coleccion->findOne(array('distrito' => 'Centro'), array('personas'));  
   //$total = $coleccion->findOne(['distrito' => "CENTRO"]).personas;
   print_r($total['personas']);
?>