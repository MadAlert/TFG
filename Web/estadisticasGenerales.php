<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="assets/images/favicon.png">
    <title>MadAlert</title>
    <!-- Bootstrap Core CSS -->
    <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- chartist CSS -->
    <link href="assets/plugins/chartist-js/dist/chartist.min.css" rel="stylesheet">
    <link href="assets/plugins/chartist-js/dist/chartist-init.css" rel="stylesheet">
    <link href="assets/plugins/chartist-plugin-tooltip-master/dist/chartist-plugin-tooltip.css" rel="stylesheet">
    <!--This page css - Morris CSS -->
    <link href="assets/plugins/c3-master/c3.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/style.css" rel="stylesheet">
    <!-- You can change the theme colors from here -->
    <link href="css/colors/blue.css" id="theme" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body class="fix-header fix-sidebar card-no-border">
    <!-- ============================================================== -->
    <!-- Preloader - style you can find in spinners.css -->
    <!-- ============================================================== -->
    <div class="preloader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" /> </svg>
    </div>
    <!-- ============================================================== -->
    <!-- Main wrapper - style you can find in pages.scss -->
    <!-- ============================================================== -->
    <div id="main-wrapper">
        <!-- ============================================================== -->
        <!-- Topbar header - style you can find in pages.scss -->
        <!-- ============================================================== -->

        <?php 
            include('topbar.php'); 
        ?>


        <!-- ============================================================== -->
        
        <?php 
            include('sidebar.php');
        ?>

        <!-- ============================================================== -->
        <!-- End Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Page wrapper  -->
        <!-- ============================================================== -->
        <div class="page-wrapper">
            <!-- ============================================================== -->
            <!-- Container fluid  -->
            <!-- ============================================================== -->
            <div class="container-fluid">
                <!-- ============================================================== -->
                <!-- Start Page Content -->
                <!-- ============================================================== -->
             
                <!-- Row -->
                <!-- ============================================================== -->
              
                  <div class="row">
                    <div class="col-12">
                        <div class="card">
                                <div class="card-block">
                                    <h1 class="card-title"> Estadísticas Generales por categorías </h1>
                                
                                        <form method="post" action="estadisticasGenerales.php" id="buscarAlertas" >
                                             <input type="hidden" name="categorias" id="categorias" value="si"/>
                                             <div class="form-group">
                                                <label class="col-sm-12">Selecciona una categoria</label>
                                                <div class="col-sm-12">
                                                    <?php 
                                                        include ("claseAlertas.php");
                                                        $alertas = new claseAlertas();
                                                        $alertas->mostrarCategorias();
                                                    ?>
                                                </div>                              
                                             </div>
                                              </div>
                                            <div class="form-group" style="margin: auto; margin-bottom: 20px;">
                                                <div class="items col-sm-12">
                                                    <button class="btn btn-success" id="alertas">Buscar</button>
                                                </div>    
                                            </div>
                                        
                               
                                 </div>
                             </div>
                         </div>

                    

                <?php 
                   
                      if(isset($_POST['categorias'])){
                        $categoria = $_POST['categorias'];
                        include ("claseEstadisticas.php");
                        $estadisticas = new claseEstadisticas();


                        //Obtengo la categoría
                        echo ' <input type="hidden" name="categoria" value='.$categoria.' id="categoria"/>';
                        echo ' <div class="row">
                                <div class="col-12">
                                    <div class="card">
                                            <div class="card-block form-center">';
                                                 echo '<h2> Estadísticas de la categoria: '.$categoria .' </h2>';
                                            echo ' </div>
                                       </div>
                                </div>
                            </div>';
                         //Calculo los meses 
                        $mes1 = $estadisticas->obtenerMes("1");
                        $mes2 =  $estadisticas->obtenerMes("2");
                     

                        //Obtengo el numero total de alertas que hay segun la categoria seleccionada (y segun el distrito) 

                        // PARA EL  MES 1
                         $maximo = 0; // Inicializo el maximo del mes 1 a 0
                         $maximo2 = 0; // Inicializo el maximo del mes 2 a 0

                         $totalArganzuela= $estadisticas->obtenerEstadisticas("Arganzuela", $categoria, $mes1);
                         if ($totalArganzuela > $maximo){
                            $maximo = $totalArganzuela;
                         }
                         $totalBarajas= $estadisticas->obtenerEstadisticas("Barajas", $categoria, $mes1);
                         if ($totalBarajas > $maximo){
                            $maximo = $totalBarajas;
                         }
                         $totalCarabanchel= $estadisticas->obtenerEstadisticas("Carabanchel", $categoria, $mes1);
                         if ($totalCarabanchel > $maximo){
                            $maximo = $totalCarabanchel;
                         }
                         $totalCentro= $estadisticas->obtenerEstadisticas("Centro", $categoria, $mes1);
                         if ($totalCentro > $maximo){
                            $maximo = $totalCentro;
                         }
                         $totalChamartin= $estadisticas->obtenerEstadisticas("Chamartín", $categoria, $mes1);
                         if ($totalChamartin > $maximo){
                            $maximo = $totalChamartin;
                         }
                         $totalChamberi= $estadisticas->obtenerEstadisticas("Chamberí", $categoria, $mes1);   
                         if ($totalChamberi > $maximo){
                            $maximo = $totalChamberi;
                         }
                         $totalCiudadLineal= $estadisticas->obtenerEstadisticas("CiudadLineal", $categoria, $mes1);
                         if ($totalCiudadLineal > $maximo){
                            $maximo = $totalCiudadLineal;
                         }
                         $totalFuencarral= $estadisticas->obtenerEstadisticas("Fuencarral", $categoria, $mes1);
                         if ($totalFuencarral > $maximo){
                            $maximo = $totalFuencarral;
                         }
                         $totalGeneral= $estadisticas->obtenerEstadisticas("General", $categoria, $mes1);
                         if ($totalGeneral > $maximo){
                            $maximo = $totalGeneral;
                         }
                         $totalHortaleza= $estadisticas->obtenerEstadisticas("Hortaleza", $categoria, $mes1);
                         if ($totalHortaleza > $maximo){
                            $maximo = $totalHortaleza;
                         }
                         $totalLatina= $estadisticas->obtenerEstadisticas("Latina", $categoria, $mes1);
                         if ($totalLatina > $maximo){
                            $maximo = $totalLatina;
                         }
                         $totalMoncloa= $estadisticas->obtenerEstadisticas("Moncloa", $categoria, $mes1);
                         if ($totalMoncloa > $maximo){
                            $maximo = $totalMoncloa;
                         }
                         $totalMoratalaz= $estadisticas->obtenerEstadisticas("Moratalaz", $categoria, $mes1);
                         if ($totalMoratalaz > $maximo){
                            $maximo = $totalMoratalaz;
                         }
                         $totalPuenteVallecas= $estadisticas->obtenerEstadisticas("Puente Vallecas", $categoria, $mes1);
                         if ($totalPuenteVallecas > $maximo){
                            $maximo = $totalPuenteVallecas;
                         }
                         $totalRetiro= $estadisticas->obtenerEstadisticas("Retiro", $categoria, $mes1);
                         if ($totalRetiro > $maximo){
                            $maximo = $totalRetiro;
                         }
                         $totalSalamanca= $estadisticas->obtenerEstadisticas("Salamanca", $categoria, $mes1);
                         if ($totalSalamanca > $maximo){
                            $maximo = $totalSalamanca;
                         }
                         $totalSanBlas= $estadisticas->obtenerEstadisticas("San Blas", $categoria, $mes1);
                         if ($totalSanBlas > $maximo){
                            $maximo = $totalSanBlas;
                         }
                         $totalTetuan= $estadisticas->obtenerEstadisticas("Tetuan", $categoria, $mes1);
                         if ($totalTetuan > $maximo){
                            $maximo = $totalTetuan;
                         }
                         $totalUsera= $estadisticas->obtenerEstadisticas("Usera", $categoria, $mes1);
                         if ($totalUsera > $maximo){
                            $maximo = $totalUsera;
                         }
                         $totalVicalvaro= $estadisticas->obtenerEstadisticas("Vicálvaro", $categoria, $mes1);
                         if ($totalVicalvaro > $maximo){
                            $maximo = $totalVicalvaro;
                         }
                         $totalVillaVallecas= $estadisticas->obtenerEstadisticas("Villa de Vallecas", $categoria, $mes1);
                         if ($totalVillaVallecas > $maximo){
                            $maximo = $totalVillaVallecas;
                         }
                         $totalVillaverde= $estadisticas->obtenerEstadisticas("Villaverde", $categoria, $mes1);
                         if ($totalVillaverde > $maximo){
                            $maximo = $totalVillaverde;
                         }


                         // PARA EL MES 2
                        
                         $totalArganzuela2= $estadisticas->obtenerEstadisticas("Arganzuela", $categoria, $mes2);
                         if ($totalArganzuela2 > $maximo2){
                            $maximo2 = $totalArganzuela2;
                         }
                         $totalBarajas2= $estadisticas->obtenerEstadisticas("Barajas", $categoria, $mes2);
                         if ($totalBarajas2 > $maximo2){
                            $maximo2 = $totalBarajas2;
                         }
                         $totalCarabanchel2= $estadisticas->obtenerEstadisticas("Carabanchel", $categoria, $mes2);
                         if ($totalCarabanchel2 > $maximo2){
                            $maximo2 = $totalCarabanchel2;
                         }
                         $totalCentro2= $estadisticas->obtenerEstadisticas("Centro", $categoria, $mes2);
                         if ($totalCentro2 > $maximo2){
                            $maximo2 = $totalCentro2;
                         }
                         $totalChamartin2= $estadisticas->obtenerEstadisticas("Chamartín", $categoria, $mes2);
                         if ($totalChamartin2 > $maximo2){
                            $maximo2 = $totalChamartin2;
                         }
                         $totalChamberi2= $estadisticas->obtenerEstadisticas("Chamberí", $categoria, $mes2);   
                         if ($totalChamberi2 > $maximo2){
                            $maximo2 = $totalChamberi2;
                         }
                         $totalCiudadLineal2= $estadisticas->obtenerEstadisticas("CiudadLineal", $categoria, $mes2);
                         if ($totalCiudadLineal2 > $maximo2){
                            $maximo2 = $totalCiudadLineal2;
                         }
                         $totalFuencarral2= $estadisticas->obtenerEstadisticas("Fuencarral", $categoria, $mes2);
                         if ($totalFuencarral2 > $maximo2){
                            $maximo2 = $totalFuencarral2;
                         }
                         $totalGeneral2= $estadisticas->obtenerEstadisticas("General", $categoria, $mes2);
                         if ($totalGeneral2 > $maximo2){
                            $maximo2 = $totalGeneral2;
                         }
                         $totalHortaleza2= $estadisticas->obtenerEstadisticas("Hortaleza", $categoria, $mes2);
                         if ($totalHortaleza2 > $maximo2){
                            $maximo2 = $totalHortaleza2;
                         }
                         $totalLatina2= $estadisticas->obtenerEstadisticas("Latina", $categoria, $mes2);
                         if ($totalLatina2 > $maximo2){
                            $maximo2 = $totalLatina2;
                         }
                         $totalMoncloa2= $estadisticas->obtenerEstadisticas("Moncloa", $categoria, $mes2);
                         if ($totalMoncloa2 > $maximo2){
                            $maximo2 = $totalMoncloa2;
                         }
                         $totalMoratalaz2= $estadisticas->obtenerEstadisticas("Moratalaz", $categoria, $mes2);
                         if ($totalMoratalaz2 > $maximo2){
                            $maximo2 = $totalMoratalaz2;
                         }
                         $totalPuenteVallecas2= $estadisticas->obtenerEstadisticas("Puente Vallecas", $categoria, $mes2);
                         if ($totalPuenteVallecas2 > $maximo2){
                            $maximo2 = $totalPuenteVallecas2;
                         }
                         $totalRetiro2= $estadisticas->obtenerEstadisticas("Retiro", $categoria, $mes2);
                         if ($totalRetiro2 > $maximo2){
                            $maximo2 = $totalRetiro2;
                         }
                         $totalSalamanca2= $estadisticas->obtenerEstadisticas("Salamanca", $categoria, $mes2);
                         if ($totalSalamanca2 > $maximo2){
                            $maximo2 = $totalSalamanca2;
                         }
                         $totalSanBlas2= $estadisticas->obtenerEstadisticas("San Blas", $categoria, $mes2);
                         if ($totalSanBlas2 > $maximo2){
                            $maximo2 = $totalSanBlas2;
                         }
                         $totalTetuan2= $estadisticas->obtenerEstadisticas("Tetuan", $categoria, $mes2);
                         if ($totalTetuan2 > $maximo2){
                            $maximo2 = $totalTetuan2;
                         }
                         $totalUsera2= $estadisticas->obtenerEstadisticas("Usera", $categoria, $mes2);
                         if ($totalUsera2 > $maximo2){
                            $maximo2 = $totalUsera2;
                         }
                         $totalVicalvaro2= $estadisticas->obtenerEstadisticas("Vicálvaro", $categoria, $mes2);
                         if ($totalVicalvaro2 > $maximo2){
                            $maximo2 = $totalVicalvaro2;
                         }
                         $totalVillaVallecas2= $estadisticas->obtenerEstadisticas("Villa de Vallecas", $categoria, $mes2);
                         if ($totalVillaVallecas2 > $maximo2){
                            $maximo2 = $totalVillaVallecas2;
                         }
                         $totalVillaverde2= $estadisticas->obtenerEstadisticas("Villaverde", $categoria, $mes2);
                         if ($totalVillaverde2 > $maximo2){
                            $maximo2 = $totalVillaverde2;
                         }

                         if ($maximo != 0){
                             if ($maximo%2 == 0){ // par
                                    $maximo = $maximo + 2;
                             } 
                             else{
                                    $maximo = $maximo + 3;
                             }
                             if ($maximo == 10){
                                $maximo = 12; // Lo cambio a 12 para que no me muestre decimales 
                             }
                             if ($maximo > 30){
                                $maximo = 40;
                             }
                        }


                        if ($maximo2 != 0){
                            if ($maximo2%2 == 0){ // par
                                    $maximo2 = $maximo2 + 2;
                             } 
                             else{
                                    $maximo2 = $maximo2 + 3;
                             }
                             if ($maximo2 == 10){
                                $maximo2 = 12;
                             }
                             if ($maximo2 > 30){
                                $maximo2 = 40;
                             }
                        }

                         
                         // Sumo el total de las alertas de cada distrito para cada uno de los meses

                         $totalTodosMes1 = $totalArganzuela + $totalBarajas + $totalCarabanchel + $totalCentro + $totalChamartin + $totalChamberi + $totalCiudadLineal + $totalFuencarral + $totalGeneral + $totalHortaleza + $totalLatina +  $totalMoncloa + $totalMoratalaz + $totalPuenteVallecas + $totalRetiro + $totalSalamanca + $totalSanBlas + $totalTetuan + $totalUsera + $totalVicalvaro + $totalVillaVallecas + $totalVillaverde;

                         $totalTodosMes2 = $totalArganzuela2 + $totalBarajas2 + $totalCarabanchel2 + $totalCentro2 + $totalChamartin2 + $totalChamberi2 + $totalCiudadLineal2 + $totalFuencarral2 + $totalGeneral2 + $totalHortaleza2 + $totalLatina2 +  $totalMoncloa2 + $totalMoratalaz2 + $totalPuenteVallecas2 + $totalRetiro2 + $totalSalamanca2 + $totalSanBlas2 + $totalTetuan2 + $totalUsera2 + $totalVicalvaro2 + $totalVillaVallecas2 + $totalVillaverde2;

                         // Si la lista suma 0, será que no hay hay estadísticas y me devolverá true (la función está implementada en claseEstadisticas)

                         $noHayEstadisticasMes1 = $estadisticas->noHayEstadisticasGenerales($totalTodosMes1);
                         $noHayEstadisticasMes2 = $estadisticas->noHayEstadisticasGenerales($totalTodosMes2);

                         echo ' <input type="hidden" name="arganzuela" value='.$totalArganzuela.' id="arganzuela"/>';     
                         echo ' <input type="hidden" name="barajas" value='.$totalBarajas.' id="barajas"/>';
                         echo ' <input type="hidden" name="carabanchel" value='.$totalCarabanchel.' id="carabanchel"/>';
                         echo ' <input type="hidden" name="centro" value='.$totalCentro.' id="centro"/>';
                         echo ' <input type="hidden" name="chamartin" value='.$totalChamartin.' id="chamartin"/>';
                         echo ' <input type="hidden" name="chamberi" value='.$totalChamberi.' id="chamberi"/>';
                         echo ' <input type="hidden" name="ciudadLineal" value='.$totalCiudadLineal.' id="ciudadLineal"/>';
                         echo ' <input type="hidden" name="fuencarral" value='.$totalFuencarral.' id="fuencarral"/>';
                         echo ' <input type="hidden" name="general" value='.$totalGeneral.' id="general"/>';
                         echo ' <input type="hidden" name="hortaleza" value='.$totalHortaleza.' id="hortaleza"/>';
                         echo ' <input type="hidden" name="latina" value='.$totalLatina.' id="latina"/>';
                         echo ' <input type="hidden" name="moncloa" value='.$totalMoncloa.' id="moncloa"/>';
                         echo ' <input type="hidden" name="moratalaz" value='.$totalMoratalaz.' id="moratalaz"/>';
                         echo ' <input type="hidden" name="puenteVallecas" value='.$totalPuenteVallecas.' id="puenteVallecas"/>';
                         echo ' <input type="hidden" name="retiro" value='.$totalRetiro.' id="retiro"/>';
                         echo ' <input type="hidden" name="salamanca" value='.$totalSalamanca.' id="salamanca"/>';
                         echo ' <input type="hidden" name="sanblas" value='.$totalSanBlas.' id="sanblas"/>';
                         echo ' <input type="hidden" name="tetuan" value='.$totalTetuan.' id="tetuan"/>';
                         echo ' <input type="hidden" name="usera" value='.$totalUsera.' id="usera"/>';
                         echo ' <input type="hidden" name="vicalvaro" value='.$totalVicalvaro.' id="vicalvaro"/>';
                         echo ' <input type="hidden" name="villaVallecas" value='.$totalVillaVallecas.' id="villaVallecas"/>';
                         echo ' <input type="hidden" name="villaverde" value='.$totalVillaverde.' id="villaverde"/>';
                         




                          echo ' <input type="hidden" name="arganzuela2" value='.$totalArganzuela2.' id="arganzuela2"/>';   
                         echo ' <input type="hidden" name="barajas2" value='.$totalBarajas2.' id="barajas2"/>';
                         echo ' <input type="hidden" name="carabanchel2" value='.$totalCarabanchel2.' id="carabanchel2"/>';
                         echo ' <input type="hidden" name="centro2" value='.$totalCentro2.' id="centro2"/>';
                         echo ' <input type="hidden" name="chamartin2" value='.$totalChamartin2.' id="chamartin2"/>';
                         echo ' <input type="hidden" name="chamberi2" value='.$totalChamberi2.' id="chamberi2"/>';
                         echo ' <input type="hidden" name="ciudadLineal2" value='.$totalCiudadLineal2.' id="ciudadLineal2"/>';
                         echo ' <input type="hidden" name="fuencarral2" value='.$totalFuencarral2.' id="fuencarral2"/>';
                         echo ' <input type="hidden" name="general2" value='.$totalGeneral2.' id="general2"/>';
                         echo ' <input type="hidden" name="hortaleza2" value='.$totalHortaleza2.' id="hortaleza2"/>';
                         echo ' <input type="hidden" name="latina2" value='.$totalLatina2.' id="latina2"/>';
                         echo ' <input type="hidden" name="moncloa2" value='.$totalMoncloa2.' id="moncloa2"/>';
                         echo ' <input type="hidden" name="moratalaz2" value='.$totalMoratalaz2.' id="moratalaz2"/>';
                         echo ' <input type="hidden" name="puenteVallecas2" value='.$totalPuenteVallecas2.' id="puenteVallecas2"/>';
                         echo ' <input type="hidden" name="retiro2" value='.$totalRetiro2.' id="retiro2"/>';
                         echo ' <input type="hidden" name="salamanca2" value='.$totalSalamanca2.' id="salamanca2"/>';
                         echo ' <input type="hidden" name="sanblas2" value='.$totalSanBlas2.' id="sanblas2"/>';
                         echo ' <input type="hidden" name="tetuan2" value='.$totalTetuan2.' id="tetuan2"/>';
                         echo ' <input type="hidden" name="usera2" value='.$totalUsera2.' id="usera2"/>';
                         echo ' <input type="hidden" name="vicalvaro2" value='.$totalVicalvaro2.' id="vicalvaro2"/>';
                         echo ' <input type="hidden" name="villaVallecas2" value='.$totalVillaVallecas2.' id="villaVallecas2"/>';
                         echo ' <input type="hidden" name="villaverde2" value='.$totalVillaverde2.' id="villaverde2"/>';

                         echo ' <input type="hidden" name="maximo" value='.$maximo.' id="maximo"/>';
                         echo "El maximo es : $maximo";
                         echo "<br>";

                         echo ' <input type="hidden" name="maximo2" value='.$maximo2.' id="maximo2"/>';
                         echo "El maximo del mes 2 es : $maximo2";
                         echo "<br>";

                         //Muestro el mensaje de que no hay estadísticas en este mes

                        // No hay estadísticas del mes 1 y si del 2
                        if($noHayEstadisticasMes1 && !$noHayEstadisticasMes2){ 
                            
                            $mes1 = $estadisticas->mesEnLetras($mes1);
                            $mes2 = $estadisticas->mesEnLetras($mes2); // Pongo el mes con letras y no con números
                           echo ' <div class="row">
                            <div class="col-12">
                                <div class="card">
                                        <div class="card-block">';
                            echo '<table class="columns">
                                        <td>
                                        <tr><p>No hay estadísticas de '.$categoria.' para el mes de ' .$mes1.'</p></tr>
                                        </td>
                                    </table>';
                             echo ' </div>
                                       </div>
                                </div>
                            </div>';

                              // Gráfico del mes 2
                          echo ' <div class="row">
                            <div class="col-12">
                                <div class="card">
                                        <div class="card-block form-center">'; 
                                             echo "<p><h3>Estas son las estadísticas para el mes $mes2 </h3></p>";
                                             echo " <script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>
                                              <div id='chart_div'></div> <script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>
                                              <div id='chart_div2'></div>";
                                           
                                              


                                       echo ' </div>
                                       </div>
                                </div>
                            </div>';


                        }

                        // No hay estadísticas del mes 2 y si del 1
                        if($noHayEstadisticasMes2 && !$noHayEstadisticasMes1){ 
                            
                            $mes1 = $estadisticas->mesEnLetras($mes1);
                            $mes2 = $estadisticas->mesEnLetras($mes2); // Pongo el mes con letras y no con números

                            echo ' <div class="row">
                            <div class="col-12">
                                <div class="card">
                                        <div class="card-block">';
                            echo '<table class="columns">
                                        <td>
                                        <tr><p>No hay estadísticas de '.$categoria.' para el mes de ' .$mes2.'</p></tr>
                                        </td>
                                    </table>';
                            echo ' </div>
                                       </div>
                                </div>
                            </div>';


                            // Gráfico del mes 1
                          echo ' <div class="row">
                            <div class="col-12">
                                <div class="card">
                                        <div class="card-block form-center">';
                                             echo "<p><h3>Estas son las estadísticas para el mes $mes1 </h3></p>";
                                             echo " <script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>
                                              <div id='chart_div'></div> <script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>
                                              <div id='chart_div1'></div>";
                                           
                                              


                                       echo ' </div>
                                       </div>
                                </div>
                            </div>';
                        }


                        // Si no hay estadísticas de ninguno de los dos meses
                        if ($noHayEstadisticasMes1 && $noHayEstadisticasMes2){
                             $mes1 = $estadisticas->mesEnLetras($mes1);
                             $mes2 = $estadisticas->mesEnLetras($mes2); // Pongo el mes con letras y no con números
                             
                            echo ' <div class="row">
                            <div class="col-12">
                                <div class="card">
                                        <div class="card-block">';
                            echo '<table class="columns">
                                        <td>
                                        <tr><p>No hay estadísticas de '.$categoria.' para el mes de ' .$mes2.'</p></tr>
                                        </td>
                                    </table>';
                             echo ' </div>
                                       </div>
                                </div>
                            </div>';


                            echo ' <div class="row">
                            <div class="col-12">
                                <div class="card">
                                        <div class="card-block">';
                            echo '<table class="columns">
                                        <td>
                                        <tr><p>No hay estadísticas de '.$categoria.' para el mes de ' .$mes1.'</p></tr>
                                        </td>
                                    </table>';
                            echo ' </div>
                                       </div>
                                </div>
                            </div>';

                        }

                         // Si hay estadísticas de los dos meses, entonces si que muestro los dos charts

                        if (!$noHayEstadisticasMes1 && !$noHayEstadisticasMes2){
                             $mes1 = $estadisticas->mesEnLetras($mes1);
                             $mes2 = $estadisticas->mesEnLetras($mes2);

                             // Gráfico del mes 1
                            echo ' <div class="row">
                            <div class="col-12">
                                <div class="card">
                                        <div class="card-block form-center">';
                                           // echo "<b> Categoria:  $categoria </b>";
                                            echo "<p><h3>Estas son las estadísticas para el mes $mes1 </h3></p>";
                                             echo " <script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>
                                              <div id='chart_div'></div> <script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>
                                              <div id='chart_div1'></div>";
                                           
                                              


                                       echo ' </div>
                                       </div>
                                </div>
                            </div>';   



                            // Gráfico del mes 2
                              echo ' <div class="row">
                                <div class="col-12">
                                    <div class="card">
                                            <div class="card-block form-center">';
                                                //echo "<b> Categoria:  $categoria </b>";
                                                echo "<p><h3>Estas son las estadísticas para el mes de $mes2 </h3></p>";
                                                 echo " <script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>
                                                  <div id='chart_div'></div> <script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>
                                                  <div id='chart_div2'></div>";
                                               
                                                  


                                           echo ' </div>
                                           </div>
                                    </div>
                                </div>';   
                       }

                    }
                ?>  

        </div> 

            <!-- ============================================================== -->
          
        <!-- ============================================================== -->
        <!-- End Page wrapper  -->
        <!-- ============================================================== -->

        <footer class="footer">
                © 2017 MadAlert
        </footer>
        
     
    </div>
    <!-- ============================================================== -->
    <!-- End Wrapper -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- All Jquery -->
    <!-- ============================================================== -->
    <script src="assets/plugins/jquery/jquery.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="assets/plugins/bootstrap/js/tether.min.js"></script>
    <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <!-- slimscrollbar scrollbar JavaScript -->
    <script src="js/jquery.slimscroll.js"></script>
    <!--Wave Effects -->
    <script src="js/waves.js"></script>
    <!--Menu sidebar -->
    <script src="js/sidebarmenu.js"></script>
    <!--stickey kit AQUI ESTA LO DE SELECCIONAR EL PRIMER ELEMENTOO COÑOOO-->
    <script src="assets/plugins/sticky-kit-master/dist/sticky-kit.min.js"></script>
    <!--Custom JavaScript -->
    <script src="js/custom.min.js"></script>
    <!-- google maps api -->
    <script src="https://maps.google.com/maps/api/js?key=AIzaSyCUBL-6KdclGJ2a_UpmB2LXvq7VOcPT7K4&sensor=true"></script>
    <script src="assets/plugins/gmaps/gmaps.min.js"></script>
    <script src="assets/plugins/gmaps/jquery.gmaps.js"></script>

    <script src="js/javaScriptEstadisticas.js"></script> <!-- nuevo script de estadisticas-->
    <script>
        $(window).resize(function(){
            drawMultSeriesMes1();
            drawMultSeriesMes2();
        });
    </script>

</body>

</html>
