<?php
require 'vendor/autoload.php';
class Clasificador {
	public $api = "http://api.meaningcloud.com/class-1.1";
	public $key = "05ed9a7c754aeee5d5f99470a756a5f8";
	public $model = "news";

	private function obtenerResponse($api, $key, $model, $txt) {
	  $data = http_build_query(array('key'=>$key,
	                                 'model'=>$model,
	                                 'txt'=>$txt,
	                                 'src'=>'sdk-php-1.1')); // management internal parameter
	  $context = stream_context_create(array('http'=>array(
	        'method'=>'POST',
	        'header'=>
	          'Content-type: application/x-www-form-urlencoded'."\r\n".
	          'Content-Length: '.strlen($data)."\r\n",
	        'content'=>$data)));
	  
	  $fd = fopen($api, 'r', false, $context);
	  $response = stream_get_contents($fd);
	  fclose($fd);
	  return $response;
	}

	public function obtenerClasificacion($alerta){
		$response = $this->obtenerResponse($this->api, $this->key, $this->model, $alerta);
		$json = json_decode($response, true);
		if(isset($json['category_list']) && count($json['category_list'])>0) {
		  $i=0;
		  foreach($json['category_list'] as $categorie) {
		  		$label = $categorie['label'];
		  		return $label;
		  }
		}else{
		  return false;
		}
	}

}
?>


