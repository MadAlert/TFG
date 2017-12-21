<?php
require 'vendor/autoload.php';
class claseAlertas {

	public $dbhost ="localHost";

	public function conexion(){
		$client = new MongoDB\Client;
		//DB
		$db = $client->noticias;
		$coleccion = $db->alertas;

		return $coleccion;
		
	}

   public function obtenerAlertas($distrito, $categorias) {
   		$coleccion = $this->conexion();
   		//echo "Hola";
    	//Query para ver alertas de distritos -> habria que añadir la condicion de fecha
    	$documento = $coleccion->find(['distrito' => $distrito, 'categoria'=>array('$in' => $categorias)]);
    	$total = $coleccion->count(['distrito' => $distrito, 'categoria'=>array('$in' => $categorias)]);
    	if($total > 0){
	    	foreach ($documento as $doc) {
	    		# code...
	    		$distrito = $doc->distrito;
	    		$texto = $doc->alerta;
	    		$fecha = $doc->fecha;
	    		$categoria = $doc->categoria;
	    		$autor = $doc->fuente;
	    		$url = $doc->url;
	    		if(isset($doc->veridico)){
	    			$verificado = $doc->veridico;
	    		}
	    		else{
	    			$verificado = true;
	    		}
	    		$this->mostrarAlertas($distrito, $fecha, $texto, $categoria, $autor, $url, $verificado);
	    		
	    	}
	    	return true;
	    }
	    else{
	    	return false;
	    }

    	
    }


    // Muestra todas las alertas de un distrito (sin tener en cuenta categorías)
    public function obtenerAlertasDistrito($distrito) {
   		$coleccion = $this->conexion();
   		
    	
    	$documento = $coleccion->find(['distrito' => $distrito]);
    	$total = $coleccion->count(['distrito' => $distrito]);
    	if($total > 0){
	    	foreach ($documento as $doc) {
	    		# code...
	    		$distrito = $doc->distrito;
	    		$texto = $doc->alerta;
	    		$fecha = $doc->fecha;
	    		$categoria = $doc->categoria;
	    		$autor = $doc->fuente;
	    		if(isset($doc->url)){
	    			$url = $doc->url;
	    		}
	    		else{
	    			$url = null;
	    		}
	    		if(isset($doc->veridico)){
	    			$verificado = $doc->veridico;
	    		}
	    		else{
	    			$verificado = true;
	    		}
	    		$this->mostrarAlertas($distrito, $fecha, $texto, $categoria, $autor, $url, $verificado);
	    		
	    	}
	    	return true;
	    }
	    else{
	    	return false;
	    }

    	
    }



    //Funcion que inserta las alertas creadas por un usuario
    public function insertarAlerta($nombre, $categoria, $distrito, $alerta){
    	$coleccion = $this->conexion();
    	$fecha = strftime("%d-%m-%Y %H:%M:%S", time());
    	$documento = ['alerta'=> $alerta, 'fecha'=>$fecha, 'distrito'=>$distrito, 'categoria'=>$categoria,'fuente'=>$nombre, 'veridico'=>false, 'url'=> NULL];
    	$coleccion->insertOne($documento);
    }

    //Funcion que muestra una alerta con el formato correspondiente
    public function mostrarAlertas($distrito, $fecha, $texto, $categoria, $autor, $url, $verificado){
    	echo '
    	
            <div class="profiletimeline">
                <div class="sl-item">
                    <div class="sl-left"></div>
                    <div class="sl-right">
                    <div>';
                    	echo '<a class="link"><u>'.$categoria.'</u></a>';
                        echo '<span class="sl-date"> '.$fecha.' </span>';
                        	echo "<p></p>";
                        	echo '<div class="m-t-20 row">
                        			<div class="col-md-1">';
                        	if($verificado != false){
                        		echo '<i class="mdi mdi-verified"></i></div>';
                        	}
                        	else{
                        		echo '</div>';
                        	}
                        	if ($url != Null){
                            	echo "<a href=".$url.">".$texto."</a>";
                            }else{
                            	echo "$texto";
                            }
                            echo '</div>';
                            echo '<p></p>';
                            echo '<div class="like-comm"> <a href="javascript:void(0)" class="link m-r-10">Fuente:</a><class="link m-r-10">' .$autor.'</div>';
                    echo '</div>';
                    echo '</div>';
                echo '</div>';
           echo ' </div>';
       echo '<hr>';
    }

    public function mostrarDistritos(){
    	echo '<html>
    		
                <div class="form-group">
                    <label class="col-sm-12">Selecciona un distrito</label>
                    <div class="col-sm-12">      
						<select class="form-control form-control-line" name="distritos" id="distritos">
							<option>Arganzuela</option>
							<option>Barajas</option>
							<option>Carabanchel</option>
							<option>Centro</option>
							<option>Chamartín</option>
							<option>Chamberí</option>
							<option>Ciudad Lineal</option>
							<option>Fuencarral-El Pardo</option>
							<option>General</option>
							<option>Hortaleza</option>
							<option>Latina</option>
							<option>Moncloa-Aravaca</option>
							<option>Moratalaz</option>
							<option>Puente de Vallecas</option>
							<option>Retiro</option>
							<option>Salamanca</option>
							<option>San Blas-Canillejas</option>
							<option>Tetuán</option>
							<option>Usera</option>
							<option>Vicálvaro</option>
							<option>Villa de Vallecas</option>
							<option>Villaverde</option>
						</select>
					</div>                                      
                </div>
                                    
			 </html>';
    }

   public function mostrarCategorias(){
    	echo '<html>
    		
                <div class="form-group">
                    <label class="col-sm-12">Selecciona un categoria</label>
                    <div class="col-sm-12">      
						<select class="form-control form-control-line" name="categorias" id="categorias">
                            <option>Contaminación</option>
                            <option>Criminalidad</option>
                            <option>Desastres y accidentes</option>
                            <option>Eventos</option>
                            <option>Terrorismo</option>
                            <option>Tráfico</option>
                            <option>Transporte público</option>
						</select>
					</div>                                      
                </div>
                                    
			 </html>';
    }

}
?>