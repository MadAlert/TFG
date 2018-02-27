<?php
require 'vendor/autoload.php';

class Clasificador {
	



    public function obtenerClasificacion($alerta){
    	$curl = curl_init();
		curl_setopt_array($curl, array(
		  CURLOPT_URL => 'http://api.meaningcloud.com/class-1.1',
		  CURLOPT_RETURNTRANSFER => true,
		  CURLOPT_ENCODING => "",
		  CURLOPT_MAXREDIRS => 10,
		  CURLOPT_TIMEOUT => 30,
		  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
		  CURLOPT_CUSTOMREQUEST => "POST",
		  CURLOPT_POSTFIELDS => "key=05ed9a7c754aeee5d5f99470a756a5f8&txt=$alerta&model=news",
		  CURLOPT_HTTPHEADER => array(
		    "content-type: application/x-www-form-urlencoded"
		  ),
		));

		$response = curl_exec($curl);
		$err = curl_error($curl);

		curl_close($curl);

		if ($err) {
		  $var = "cURL Error #:" + $err;
		  echo $var;
		} else {
		  return $response;
		}
	}

}
?>
