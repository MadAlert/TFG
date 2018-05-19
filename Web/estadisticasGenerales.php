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
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
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
                                                    <button class="btn btn-danger" id="alertas">Buscar</button>
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
                                                 echo '<h2> Estadísticas de la categoría: '.$categoria .' </h2>';
                                            echo ' </div>
                                       </div>
                                </div>
                            </div>';
                         //Calculo los meses 
                        $mes1 = $estadisticas->obtenerMes("1");
                        $mes2 =  $estadisticas->obtenerMes("2");     
                        settype($mes1, 'int');
                        settype($mes2, 'int');                   


                        //Obtengo el numero total de alertas que hay segun la categoria seleccionada (y segun el distrito) 

                        // PARA EL  MES 1
                        $datosMes1 = $estadisticas->obtenerEstGenerales($categoria, $mes1);
                        
                         // PARA EL MES 2
                        $datosMes2 = $estadisticas->obtenerEstGenerales($categoria, $mes2);                

                        // los maximos de cada mes        
                        $maximo = $estadisticas->obtenerMaximoDistrito($datosMes1);
                        $maximo2 = $estadisticas->obtenerMaximoDistrito($datosMes2);

                        // Fuerzo los máximos para que salgan bien las gráficas y no con decimales
                        if ($maximo != 0){
                            if ($maximo < 4){ 
                                $maximo = 4; // Voy a ir poniendo valores múltiplos de 4
                            } elseif ($maximo >= 4 && $maximo < 12){
                                $maximo = 12;
                            } elseif ($maximo >= 12 && $maximo < 20){
                                $maximo = 20;
                            }  elseif ($maximo >= 20 && $maximo < 40 ){
                                $maximo = 40; 
                            } elseif ($maximo >= 40 && $maximo < 60 ){
                                $maximo = 60;
                            } elseif ($maximo >= 60 && $maximo < 80 ){
                                $maximo = 80;
                            } elseif ($maximo >= 80 && $maximo < 120){
                                $maximo = 120;
                            } elseif ($maximo > 120){
                                if ($maximo%2 == 0){ // par
                                    $maximo = $maximo + 12; // Dejo bastante margen por arriba, sino se sale
                                }
                                else{ // impar
                                    $maximo = $maximo + 13;
                                }
                            } 
                                
                        }


                        if ($maximo2 != 0){
                            if ($maximo2 < 4){ 
                                $maximo2 = 4; // Voy a ir poniendo valores múltiplos de 4
                            } elseif ($maximo2 >= 4 && $maximo2 < 12){
                                $maximo2 = 12;
                            } elseif ($maximo2 >= 12 && $maximo2 < 20){
                                $maximo2 = 20;
                            }  elseif ($maximo2 >= 20 && $maximo2 < 40){
                                $maximo2 = 40; 
                            } elseif ($maximo2 >= 40 && $maximo2 < 60){
                                $maximo2 = 60;
                            } elseif ($maximo2 >= 60 && $maximo2 < 80){
                                $maximo2 = 80;
                            } elseif ($maximo2 >= 80 && $maximo2 < 120){
                                $maximo2 = 120;
                            } elseif ($maximo2 > 120){
                                if ($maximo2%2 == 0){ // par
                                    $maximo2 = $maximo2 + 12; // Dejo bastante margen por arriba, sino se sale
                                }
                                else{ // impar
                                    $maximo2 = $maximo2 + 13;
                                }
                            } 
                                
                        }
            
                         // Sumo el total de las alertas de cada distrito para cada uno de los meses
                        $totalTodosMes1 = $estadisticas->sumaAlertasPorDistrito($datosMes1);
                        $totalTodosMes2 = $estadisticas->sumaAlertasPorDistrito($datosMes2);
                        
                         // Si la lista suma 0, será que no hay hay estadísticas y me devolverá true (la función está implementada en claseEstadisticas)

                        $noHayEstadisticasMes1 = $estadisticas->noHayEstadisticasGenerales($totalTodosMes1);
                        $noHayEstadisticasMes2 = $estadisticas->noHayEstadisticasGenerales($totalTodosMes2);

                        $totalGeneralesMes1 = $estadisticas->crearCamposOcultosGenerales($datosMes1);
                        $totalGeneralesMes2 = $estadisticas->crearCamposOcultosGenerales2($datosMes2);


                        //Muestro el mensaje de que no hay estadísticas en este mes
                        $noHayEstadisticasMes1 = $estadisticas->noHayEstadisticasGenerales($totalTodosMes1);
                        $noHayEstadisticasMes2 = $estadisticas->noHayEstadisticasGenerales($totalTodosMes2);


                         echo ' <input type="hidden" name="maximo" value='.$maximo.' id="maximo"/>';
                         /*echo "El maximo es : $maximo";
                         echo "<br>";*/

                         echo ' <input type="hidden" name="maximo2" value='.$maximo2.' id="maximo2"/>';
                         /*echo "El maximo del mes 2 es : $maximo2";
                         echo "<br>";*/

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
                                             echo "<h3>Estas son las estadísticas para el mes $mes2 </h3>";
                                             echo " 
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
                                             echo "<h3>Estas son las estadísticas para el mes $mes1 </h3>";
                                             echo "
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
                                            echo "<h3>Estas son las estadísticas para el mes $mes1 </h3>";
                                             echo "
                                              
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
                                                echo "<h3>Estas son las estadísticas para el mes de $mes2 </h3>";
                                                 echo " 
                                                  
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
    <script src="js/javaScriptEstadisticas.js"></script> <!-- nuevo script de estadisticas-->
    <script>
        $(window).resize(function(){
            drawMultSeriesMes1();
            drawMultSeriesMes2();
        });
    </script>

</body>

</html>
