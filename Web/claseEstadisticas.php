<?php
require 'vendor/autoload.php';
require_once("src/dao/DAOEstadisticas.php");

class claseEstadisticas {

	public $dbhost ="localHost";
  public $daoEst;

  function __construct() {
    $this->daoEst = new DAOEstadisticas();
  }

    //Funcion que inserta las alertas creadas por un usuario
    public function insertarEstadistica($categoria, $distrito, $mes){    	
    	$fecha = strftime("%m", time());
    	$documento = ['fecha'=>$fecha, 'distrito'=>$distrito, 'categoria'=>$categoria,'fuente'=>$nombre, 'veridico'=>false, 'url'=> NULL];
    	$this->daoEst->insertAlerta($documento);
    }

    /* Para estadisticasDistrito */

    public function obtenerDatos($distrito, $mes){
      $totalTerrorismo= $this->daoEst->obtenerEstadisticas($distrito, "Terrorismo", $mes);
      $totalDesastres = $this->daoEst->obtenerEstadisticas($distrito, "Desastres y accidentes", $mes);
      $totalEventos= $this->daoEst->obtenerEstadisticas($distrito, "Eventos",$mes);
      $totalTransporte= $this->daoEst->obtenerEstadisticas($distrito, "Transporte público", $mes);
      $totalCriminalidad= $this->daoEst->obtenerEstadisticas($distrito, "Criminalidad", $mes);
      $totalContaminacion= $this->daoEst->obtenerEstadisticas($distrito, "Contaminación", $mes);
      $totalTrafico= $this->daoEst->obtenerEstadisticas($distrito, "Tráfico",$mes);
      $lista = [$totalTerrorismo,$totalDesastres,$totalEventos,$totalTransporte,$totalCriminalidad,$totalContaminacion,$totalTrafico];
      return $lista;
    }
    
    public function crearCamposOcultosPrimerMes($distrito, $lista, $mes){
      echo ' <input type="hidden" name="distrito" value='.$distrito.' id="distrito"/>';
      echo ' <input type="hidden" name="terrorismo" value='.$lista[0].' id="terrorismo"/>';
      echo ' <input type="hidden" name="desastres" value='.$lista[1].' id="desastres"/>';
      echo ' <input type="hidden" name="eventos" value='.$lista[2].' id="eventos"/>';
      echo ' <input type="hidden" name="transporte" value='.$lista[3].' id="transporte"/>';
      echo ' <input type="hidden" name="criminalidad" value='.$lista[4].' id="criminalidad"/>';
      echo ' <input type="hidden" name="contaminacion" value='.$lista[5].' id="contaminacion"/>';
      echo ' <input type="hidden" name="trafico" value='.$lista[6].' id="trafico"/>';
      echo ' <input type="hidden" name="mes1" value='.$mes.' id="mes1"/>';

    }

    public function crearCamposOcultosSegundoMes($distrito, $lista , $mes){
      echo ' <input type="hidden" name="distrito" value='.$distrito.' id="distrito"/>';
      echo ' <input type="hidden" name="terrorismo2" value='.$lista[0].' id="terrorismo2"/>';
      echo ' <input type="hidden" name="desastres2" value='.$lista[1].' id="desastres2"/>';
      echo ' <input type="hidden" name="eventos2" value='.$lista[2].' id="eventos2"/>';
      echo ' <input type="hidden" name="transporte2" value='.$lista[3].' id="transporte2"/>';
      echo ' <input type="hidden" name="criminalidad2" value='.$lista[4].' id="criminalidad2"/>';
      echo ' <input type="hidden" name="contaminacion2" value='.$lista[5].' id="contaminacion2"/>';
      echo ' <input type="hidden" name="trafico2" value='.$lista[6].' id="trafico2"/>';
      echo ' <input type="hidden" name="mes2" value='.$mes.' id="mes2"/>';

    }

    public function noHayEstadisticas($lista){
      if($lista[0] == 0 && $lista[1]==0 && $lista[2]==0 && $lista[3]==0 && $lista[4]==0 && $lista[5]==0 && $lista[6]==0){
        return true;                  
      }
      else{
        return false;
      }
    }

    public function mesEnLetras($mes){
      $meses  = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
        return $meses[$mes-1];
    }                   


    /* Para estadisticaPolicia*/

    public function obtenerDatosDetenidos(){
      $totalArganzuela= $this->daoEst->obtenerEstDetenidos("Arganzuela", "detenidos");
      $totalBarajas= $this->daoEst->obtenerEstDetenidos("Barajas", "detenidos");
      $totalCarabanchel= $this->daoEst->obtenerEstDetenidos("Carabanchel", "detenidos");
      $totalCentro= $this->daoEst->obtenerEstDetenidos("Centro", "detenidos");
      $totalChamartin= $this->daoEst->obtenerEstDetenidos("Chamartín", "detenidos");
      $totalChamberi= $this->daoEst->obtenerEstDetenidos("Chamberí", "detenidos");   
      $totalCiudadLineal= $this->daoEst->obtenerEstDetenidos("Ciudad Lineal", "detenidos");
      $totalFuencarral= $this->daoEst->obtenerEstDetenidos("Fuencarral-El Pardo", "detenidos");                 
      $totalHortaleza= $this->daoEst->obtenerEstDetenidos("Hortaleza", "detenidos");
      $totalLatina= $this->daoEst->obtenerEstDetenidos("Latina", "detenidos");
      $totalMoncloa= $this->daoEst->obtenerEstDetenidos("Moncloa-Aravaca", "detenidos");
      $totalMoratalaz= $this->daoEst->obtenerEstDetenidos("Moratalaz", "detenidos");
      $totalPuenteVallecas= $this->daoEst->obtenerEstDetenidos("Puente de Vallecas", "detenidos");
      $totalRetiro= $this->daoEst->obtenerEstDetenidos("Retiro", "detenidos");
      $totalSalamanca= $this->daoEst->obtenerEstDetenidos("Salamanca", "detenidos");
      $totalSanBlas= $this->daoEst->obtenerEstDetenidos("San Blas-Canillejas", "detenidos");
      $totalTetuan= $this->daoEst->obtenerEstDetenidos("Tetuán", "detenidos");
      $totalUsera= $this->daoEst->obtenerEstDetenidos("Usera", "detenidos");
      $totalVicalvaro= $this->daoEst->obtenerEstDetenidos("Vicálvaro", "detenidos");
      $totalVillaVallecas= $this->daoEst->obtenerEstDetenidos("Villa de Vallecas", "detenidos");
      $totalVillaverde= $this->daoEst->obtenerEstDetenidos("Villaverde", "detenidos");   
      $lista = [$totalArganzuela, $totalBarajas, $totalCarabanchel, $totalCentro, $totalChamartin, $totalChamberi, $totalCiudadLineal, $totalFuencarral, $totalHortaleza, $totalLatina, $totalMoncloa, $totalMoratalaz, $totalPuenteVallecas, $totalRetiro, $totalSalamanca, $totalSanBlas, $totalTetuan, $totalUsera, $totalVicalvaro, $totalVillaVallecas, $totalVillaverde];
      return $lista;
    }

    public function crearCamposOcultosDetenidos($lista){
      echo ' <input type="hidden" name="arganzuela" value='.$lista[0].' id="arganzuela"/>';     
      echo ' <input type="hidden" name="barajas" value='.$lista[1].' id="barajas"/>';
      echo ' <input type="hidden" name="carabanchel" value='.$lista[2].' id="carabanchel"/>';
      echo ' <input type="hidden" name="centro" value='.$lista[3].' id="centro"/>';
      echo ' <input type="hidden" name="chamartin" value='.$lista[4].' id="chamartin"/>';
      echo ' <input type="hidden" name="chamberi" value='.$lista[5].' id="chamberi"/>';
      echo ' <input type="hidden" name="ciudadLineal" value='.$lista[6].' id="ciudadLineal"/>';
      echo ' <input type="hidden" name="fuencarral" value='.$lista[7].' id="fuencarral"/>';              
      echo ' <input type="hidden" name="hortaleza" value='.$lista[8].' id="hortaleza"/>';
      echo ' <input type="hidden" name="latina" value='.$lista[9].' id="latina"/>';
      echo ' <input type="hidden" name="moncloa" value='.$lista[10].' id="moncloa"/>';
      echo ' <input type="hidden" name="moratalaz" value='.$lista[11].' id="moratalaz"/>';
      echo ' <input type="hidden" name="puenteVallecas" value='.$lista[12].' id="puenteVallecas"/>';
      echo ' <input type="hidden" name="retiro" value='.$lista[13].' id="retiro"/>';
      echo ' <input type="hidden" name="salamanca" value='.$lista[14].' id="salamanca"/>';
      echo ' <input type="hidden" name="sanblas" value='.$lista[15].' id="sanblas"/>';
      echo ' <input type="hidden" name="tetuan" value='.$lista[16].' id="tetuan"/>';
      echo ' <input type="hidden" name="usera" value='.$lista[17].' id="usera"/>';
      echo ' <input type="hidden" name="vicalvaro" value='.$lista[18].' id="vicalvaro"/>';
      echo ' <input type="hidden" name="villaVallecas" value='.$lista[19].' id="villaVallecas"/>';
      echo ' <input type="hidden" name="villaverde" value='.$lista[20].' id="villaverde"/>';
    }

    public function obtenerDatosAccidentes(){
      $totalArganzuela= $this->daoEst->obtenerEstAccidentes("Arganzuela", "conHeridos", "sinHeridos");
      $totalBarajas= $this->daoEst->obtenerEstAccidentes("Barajas", "conHeridos", "sinHeridos");
      $totalCarabanchel= $this->daoEst->obtenerEstAccidentes("Carabanchel", "conHeridos", "sinHeridos");
      $totalCentro= $this->daoEst->obtenerEstAccidentes("Centro", "conHeridos", "sinHeridos");
      $totalChamartin= $this->daoEst->obtenerEstAccidentes("Chamartín", "conHeridos", "sinHeridos");
      $totalChamberi= $this->daoEst->obtenerEstAccidentes("Chamberí", "conHeridos", "sinHeridos");   
      $totalCiudadLineal= $this->daoEst->obtenerEstAccidentes("Ciudad Lineal", "conHeridos", "sinHeridos");
      $totalFuencarral= $this->daoEst->obtenerEstAccidentes("Fuencarral-El Pardo", "conHeridos", "sinHeridos");     
      $totalHortaleza= $this->daoEst->obtenerEstAccidentes("Hortaleza", "conHeridos", "sinHeridos");
      $totalLatina= $this->daoEst->obtenerEstAccidentes("Latina", "conHeridos", "sinHeridos");
      $totalMoncloa= $this->daoEst->obtenerEstAccidentes("Moncloa-Aravaca", "conHeridos", "sinHeridos");
      $totalMoratalaz= $this->daoEst->obtenerEstAccidentes("Moratalaz", "conHeridos", "sinHeridos");
      $totalPuenteVallecas= $this->daoEst->obtenerEstAccidentes("Puente de Vallecas", "conHeridos", "sinHeridos");
      $totalRetiro= $this->daoEst->obtenerEstAccidentes("Retiro", "conHeridos", "sinHeridos");
      $totalSalamanca= $this->daoEst->obtenerEstAccidentes("Salamanca", "conHeridos", "sinHeridos");
      $totalSanBlas= $this->daoEst->obtenerEstAccidentes("San Blas-Canillejas", "conHeridos", "sinHeridos");
      $totalTetuan= $this->daoEst->obtenerEstAccidentes("Tetuán", "conHeridos", "sinHeridos");
      $totalUsera= $this->daoEst->obtenerEstAccidentes("Usera", "conHeridos", "sinHeridos");
      $totalVicalvaro= $this->daoEst->obtenerEstAccidentes("Vicálvaro", "conHeridos", "sinHeridos");
      $totalVillaVallecas= $this->daoEst->obtenerEstAccidentes("Villa de Vallecas", "conHeridos", "sinHeridos");
      $totalVillaverde= $this->daoEst->obtenerEstAccidentes("Villaverde", "conHeridos", "sinHeridos");

      echo ' <input type="hidden" name="arganzuelaC" value='.$totalArganzuela['conHeridos'].' id="arganzuelaC"/>';
      echo ' <input type="hidden" name="arganzuelaS" value='.$totalArganzuela['sinHeridos'].' id="arganzuelaS"/>';    
      echo ' <input type="hidden" name="barajasC" value='.$totalBarajas['conHeridos'].' id="barajasC"/>';
      echo ' <input type="hidden" name="barajasS" value='.$totalBarajas['sinHeridos'].' id="barajasS"/>';
      echo ' <input type="hidden" name="carabanchelC" value='.$totalCarabanchel['conHeridos'].' id="carabanchelC"/>';
      echo ' <input type="hidden" name="carabanchelS" value='.$totalCarabanchel['sinHeridos'].' id="carabanchelS"/>';
      echo ' <input type="hidden" name="centroC" value='.$totalCentro['conHeridos'].' id="centroC"/>';
      echo ' <input type="hidden" name="centroS" value='.$totalCentro['sinHeridos'].' id="centroS"/>';
      echo ' <input type="hidden" name="chamartinC" value='.$totalChamartin['conHeridos'].' id="chamartinC"/>';
      echo ' <input type="hidden" name="chamartinS" value='.$totalChamartin['sinHeridos'].' id="chamartinS"/>';
      echo ' <input type="hidden" name="chamberiC" value='.$totalChamberi['conHeridos'].' id="chamberiC"/>';
      echo ' <input type="hidden" name="chamberiS" value='.$totalChamberi['sinHeridos'].' id="chamberiS"/>';
      echo ' <input type="hidden" name="ciudadLinealC" value='.$totalCiudadLineal['conHeridos'].' id="ciudadLinealC"/>';
      echo ' <input type="hidden" name="ciudadLinealS" value='.$totalCiudadLineal['sinHeridos'].' id="ciudadLinealS"/>';
      echo ' <input type="hidden" name="fuencarralC" value='.$totalFuencarral['conHeridos'].' id="fuencarralC"/>';          
      echo ' <input type="hidden" name="fuencarralS" value='.$totalFuencarral['sinHeridos'].' id="fuencarralS"/>';            
      echo ' <input type="hidden" name="hortalezaC" value='.$totalHortaleza['conHeridos'].' id="hortalezaC"/>';
      echo ' <input type="hidden" name="hortalezaS" value='.$totalHortaleza['sinHeridos'].' id="hortalezaS"/>';
      echo ' <input type="hidden" name="latinaC" value='.$totalLatina['conHeridos'].' id="latinaC"/>';
      echo ' <input type="hidden" name="latinaS" value='.$totalLatina['sinHeridos'].' id="latinaS"/>';
      echo ' <input type="hidden" name="moncloaC" value='.$totalMoncloa['conHeridos'].' id="moncloaC"/>';
      echo ' <input type="hidden" name="moncloaS" value='.$totalMoncloa['sinHeridos'].' id="moncloaS"/>';
      echo ' <input type="hidden" name="moratalazC" value='.$totalMoratalaz['conHeridos'].' id="moratalazC"/>';
      echo ' <input type="hidden" name="moratalazS" value='.$totalMoratalaz['sinHeridos'].' id="moratalazS"/>';
      echo ' <input type="hidden" name="puenteVallecasC" value='.$totalPuenteVallecas['conHeridos'].' id="puenteVallecasC"/>';
      echo ' <input type="hidden" name="puenteVallecasS" value='.$totalPuenteVallecas['sinHeridos'].' id="puenteVallecasS"/>';
      echo ' <input type="hidden" name="retiroC" value='.$totalRetiro['conHeridos'].' id="retiroC"/>';
      echo ' <input type="hidden" name="retiroS" value='.$totalRetiro['sinHeridos'].' id="retiroS"/>';
      echo ' <input type="hidden" name="salamancaC" value='.$totalSalamanca['conHeridos'].' id="salamancaC"/>';
      echo ' <input type="hidden" name="salamancaS" value='.$totalSalamanca['sinHeridos'].' id="salamancaS"/>';
      echo ' <input type="hidden" name="sanblasC" value='.$totalSanBlas['conHeridos'].' id="sanblasC"/>';
      echo ' <input type="hidden" name="sanblasS" value='.$totalSanBlas['sinHeridos'].' id="sanblasS"/>';
      echo ' <input type="hidden" name="tetuanC" value='.$totalTetuan['conHeridos'].' id="tetuanC"/>';
      echo ' <input type="hidden" name="tetuanS" value='.$totalTetuan['sinHeridos'].' id="tetuanS"/>';
      echo ' <input type="hidden" name="useraC" value='.$totalUsera['conHeridos'].' id="useraC"/>';
      echo ' <input type="hidden" name="useraS" value='.$totalUsera['sinHeridos'].' id="useraS"/>';
      echo ' <input type="hidden" name="vicalvaroC" value='.$totalVicalvaro['conHeridos'].' id="vicalvaroC"/>';
      echo ' <input type="hidden" name="vicalvaroS" value='.$totalVicalvaro['sinHeridos'].' id="vicalvaroS"/>';
      echo ' <input type="hidden" name="villaVallecasC" value='.$totalVillaVallecas['conHeridos'].' id="villaVallecasC"/>';
      echo ' <input type="hidden" name="villaVallecasS" value='.$totalVillaVallecas['sinHeridos'].' id="villaVallecasS"/>';
      echo ' <input type="hidden" name="villaverdeC" value='.$totalVillaverde['conHeridos'].' id="villaverdeC"/>';
      echo ' <input type="hidden" name="villaverdeS" value='.$totalVillaverde['sinHeridos'].' id="villaverdeS"/>';

    }

    public function obtenerDatosSeguridad($distrito){
      $totalPersonas= $this->daoEst->obtenerEstSeguridad($distrito, 'personas');                        
      $totalPatrimonio = $this->daoEst->obtenerEstSeguridad($distrito, 'patrimonio');                       
      $totalArmas= $this->daoEst->obtenerEstSeguridad($distrito, 'armas');
      $totalTenDrogas= $this->daoEst->obtenerEstSeguridad($distrito, 'ten_drogas');
      $totalConDrogas= $this->daoEst->obtenerEstSeguridad($distrito, 'con_drogas');
      $lista = [$totalPersonas, $totalPatrimonio, $totalArmas, $totalTenDrogas, $totalConDrogas];
      return $lista;
    }

    public function crearCamposOcultosSeguridad($distrito, $lista){
      echo ' <input type="hidden" name="distrito" value='.$distrito.' id="distrito"/>';
      echo ' <input type="hidden" name="personas" value='.$lista[0].' id="personas"/>';
      echo ' <input type="hidden" name="patrimonio" value='.$lista[1].' id="patrimonio"/>';
      echo ' <input type="hidden" name="armas" value='.$lista[2].' id="armas"/>';
      echo ' <input type="hidden" name="ten_drogas" value='.$lista[3].' id="ten_drogas"/>';
      echo ' <input type="hidden" name="con_drogas" value='.$lista[4].' id="con_drogas"/>';
    }

    public function obtenerMesPolicia(){
      return $this->daoEst->obtenerMesEstPolicias("Centro", "mes");
    }


    /*Para estadisticasGenerales */

     public function noHayEstadisticasGenerales($lista){
      if($lista==0){
        return true;                  
      }
      else{
        return false;
      }
    }
     
    public function obtenerEstGenerales($categoria, $mes){
      $totalArganzuela= $this->daoEst->obtenerEstadisticas("Arganzuela", $categoria, $mes);
      $totalBarajas= $this->daoEst->obtenerEstadisticas("Barajas", $categoria, $mes);
      $totalCarabanchel= $this->daoEst->obtenerEstadisticas("Carabanchel", $categoria, $mes);
      $totalCentro= $this->daoEst->obtenerEstadisticas("Centro", $categoria, $mes);
      $totalChamartin= $this->daoEst->obtenerEstadisticas("Chamartín", $categoria, $mes);
      $totalChamberi= $this->daoEst->obtenerEstadisticas("Chamberí", $categoria, $mes);   
      $totalCiudadLineal= $this->daoEst->obtenerEstadisticas("CiudadLineal", $categoria, $mes);
      $totalFuencarral= $this->daoEst->obtenerEstadisticas("Fuencarral", $categoria, $mes);
      $totalGeneral= $this->daoEst->obtenerEstadisticas("General", $categoria, $mes);
      $totalHortaleza= $this->daoEst->obtenerEstadisticas("Hortaleza", $categoria, $mes);
      $totalLatina= $this->daoEst->obtenerEstadisticas("Latina", $categoria, $mes);
      $totalMoncloa= $this->daoEst->obtenerEstadisticas("Moncloa", $categoria, $mes);
      $totalMoratalaz= $this->daoEst->obtenerEstadisticas("Moratalaz", $categoria, $mes);
      $totalPuenteVallecas= $this->daoEst->obtenerEstadisticas("Puente Vallecas", $categoria, $mes);
      $totalRetiro= $this->daoEst->obtenerEstadisticas("Retiro", $categoria, $mes);
      $totalSalamanca= $this->daoEst->obtenerEstadisticas("Salamanca", $categoria, $mes);
      $totalSanBlas= $this->daoEst->obtenerEstadisticas("San Blas", $categoria, $mes);
      $totalTetuan= $this->daoEst->obtenerEstadisticas("Tetuan", $categoria, $mes);
      $totalUsera= $this->daoEst->obtenerEstadisticas("Usera", $categoria, $mes);
      $totalVicalvaro= $this->daoEst->obtenerEstadisticas("Vicálvaro", $categoria, $mes);
      $totalVillaVallecas= $this->daoEst->obtenerEstadisticas("Villa de Vallecas", $categoria, $mes);
      $totalVillaverde= $this->daoEst->obtenerEstadisticas("Villaverde", $categoria, $mes);
      $lista = [$totalArganzuela, $totalBarajas, $totalCarabanchel, $totalCentro, $totalChamartin, $totalChamberi, $totalCiudadLineal, $totalFuencarral, $totalGeneral, $totalHortaleza, $totalLatina, $totalMoncloa, $totalMoratalaz, $totalPuenteVallecas, $totalRetiro, $totalSalamanca, $totalSanBlas, $totalTetuan, $totalUsera, $totalVicalvaro, $totalVillaVallecas, $totalVillaverde];
      return $lista;
    }

    public function sumaAlertasPorDistrito($lista){
      $total = 0;
      foreach ($lista as $valor){
        $total += $valor;        
      }
      return $total;
    }

    public function obtenerMaximoDistrito($lista){
      $maximo = 0;
      foreach ($lista as $valor){
        if($valor > $maximo)
          $maximo = $valor;        
      }
      return $maximo; 
    }

    public function crearCamposOcultosGenerales($lista){
      echo ' <input type="hidden" name="arganzuela" value='.$lista[0].' id="arganzuela"/>';     
      echo ' <input type="hidden" name="barajas" value='.$lista[1].' id="barajas"/>';
      echo ' <input type="hidden" name="carabanchel" value='.$lista[2].' id="carabanchel"/>';
      echo ' <input type="hidden" name="centro" value='.$lista[3].' id="centro"/>';
      echo ' <input type="hidden" name="chamartin" value='.$lista[4].' id="chamartin"/>';
      echo ' <input type="hidden" name="chamberi" value='.$lista[5].' id="chamberi"/>';
      echo ' <input type="hidden" name="ciudadLineal" value='.$lista[6].' id="ciudadLineal"/>';
      echo ' <input type="hidden" name="fuencarral" value='.$lista[7].' id="fuencarral"/>';
      echo ' <input type="hidden" name="general" value='.$lista[8].' id="general"/>';
      echo ' <input type="hidden" name="hortaleza" value='.$lista[9].' id="hortaleza"/>';
      echo ' <input type="hidden" name="latina" value='.$lista[10].' id="latina"/>';
      echo ' <input type="hidden" name="moncloa" value='.$lista[11].' id="moncloa"/>';
      echo ' <input type="hidden" name="moratalaz" value='.$lista[12].' id="moratalaz"/>';
      echo ' <input type="hidden" name="puenteVallecas" value='.$lista[13].' id="puenteVallecas"/>';
      echo ' <input type="hidden" name="retiro" value='.$lista[14].' id="retiro"/>';
      echo ' <input type="hidden" name="salamanca" value='.$lista[15].' id="salamanca"/>';
      echo ' <input type="hidden" name="sanblas" value='.$lista[16].' id="sanblas"/>';
      echo ' <input type="hidden" name="tetuan" value='.$lista[17].' id="tetuan"/>';
      echo ' <input type="hidden" name="usera" value='.$lista[18].' id="usera"/>';
      echo ' <input type="hidden" name="vicalvaro" value='.$lista[19].' id="vicalvaro"/>';
      echo ' <input type="hidden" name="villaVallecas" value='.$lista[20].' id="villaVallecas"/>';
      echo ' <input type="hidden" name="villaverde" value='.$lista[21].' id="villaverde"/>';
    }

    public function crearCamposOcultosGenerales2($lista){
      echo ' <input type="hidden" name="arganzuela" value='.$lista[0].' id="arganzuela2"/>';     
      echo ' <input type="hidden" name="barajas" value='.$lista[1].' id="barajas2"/>';
      echo ' <input type="hidden" name="carabanchel" value='.$lista[2].' id="carabanchel2"/>';
      echo ' <input type="hidden" name="centro" value='.$lista[3].' id="centro2"/>';
      echo ' <input type="hidden" name="chamartin" value='.$lista[4].' id="chamartin2"/>';
      echo ' <input type="hidden" name="chamberi" value='.$lista[5].' id="chamberi2"/>';
      echo ' <input type="hidden" name="ciudadLineal" value='.$lista[6].' id="ciudadLineal2"/>';
      echo ' <input type="hidden" name="fuencarral" value='.$lista[7].' id="fuencarral2"/>';
      echo ' <input type="hidden" name="general" value='.$lista[8].' id="general2"/>';
      echo ' <input type="hidden" name="hortaleza" value='.$lista[9].' id="hortaleza2"/>';
      echo ' <input type="hidden" name="latina" value='.$lista[10].' id="latina2"/>';
      echo ' <input type="hidden" name="moncloa" value='.$lista[11].' id="moncloa2"/>';
      echo ' <input type="hidden" name="moratalaz" value='.$lista[12].' id="moratalaz2"/>';
      echo ' <input type="hidden" name="puenteVallecas" value='.$lista[13].' id="puenteVallecas2"/>';
      echo ' <input type="hidden" name="retiro" value='.$lista[14].' id="retiro2"/>';
      echo ' <input type="hidden" name="salamanca" value='.$lista[15].' id="salamanca2"/>';
      echo ' <input type="hidden" name="sanblas" value='.$lista[16].' id="sanblas2"/>';
      echo ' <input type="hidden" name="tetuan" value='.$lista[17].' id="tetuan2"/>';
      echo ' <input type="hidden" name="usera" value='.$lista[18].' id="usera2"/>';
      echo ' <input type="hidden" name="vicalvaro" value='.$lista[19].' id="vicalvaro2"/>';
      echo ' <input type="hidden" name="villaVallecas" value='.$lista[20].' id="villaVallecas2"/>';
      echo ' <input type="hidden" name="villaverde" value='.$lista[21].' id="villaverde2"/>';
    }


    /* Usado en varias*/

    public function obtenerMes($i){
      $mesAnterior1 = date('m', strtotime('-1 month'));
      $mesAnterior2 = date('m', strtotime('-2 month'));
      if($i==1){
        $mes = $mesAnterior1;
      }
      else{
        $mes = $mesAnterior2;
      }
      return $mes;
    }
}
?>