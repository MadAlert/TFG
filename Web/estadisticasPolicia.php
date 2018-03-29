<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->    
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
    <script src="js/javaScriptEstadisticas.js"></script> <!-- nuevo script de estadisticas-->                

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
  
                <form method="post" name="alertas" action="estadisticasPolicia.php" id="buscarAlertas">
                <input type="hidden" id="busqueda" name="busqueda" value="busqueda"/>
                <?php                                  
                    
                echo '
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-block">
                                <h1 class="card-title"> Estadísticas de la Policía Municipal </h1>
                                    <div class="form-group">
                                        <label class="col-sm-12"> ¿Qué estadísticas quieres ver? </label>
                                            <div class="col-sm-12">
                                            <select class="form-control form-control-line" name="eleccion" id="eleccion" onchange="muestraDistritos()">
                                                <option> Estadísticas de detenidos por distritos </option>
                                                <option> Estadísticas de accidentes por distritos</option>
                                                <option> Estadísticas relacionadas con la seguridad</option>
                                            </select>';               
                                                   
                echo '
                </div>                                                              
                    </div>
                        </div>
                            <div class="form-group" style="margin: auto; margin-bottom: 20px;">
                                <div class="items col-sm-12">
                                   <button class="btn btn-success" id="elec">Mostrar</button>
                                </div>    
                            </div>                            
                        </div>
                    </div>
                </div>';

                echo '<p id="mostrarDistritos"> <p> ';
                    

                    include("claseEstadisticas.php");
                    $estadisticas = new claseEstadisticas();         

                    if(isset($_POST['busqueda'])){                    
       
                    $eleccion = $_POST['eleccion'];

                    $mes = $estadisticas->obtenerMesPolicia();
                    echo ' <div class="row">
                            <div class="col-12">
                                <div class="card">
                                        <div class="card-block form-center">';
                                            echo '<h2>Estadísticas del mes de '.$mes .' </h2>';
                                            echo ' </div>
                                </div>
                            </div>
                           </div>';            


                    if($eleccion == 'Estadísticas de detenidos por distritos'){
                    /* Detenidos */
                    $datosDetenidos = $estadisticas->obtenerDatosDetenidos();            
                    $totalDetenidos = $estadisticas->crearCamposOcultosDetenidos($datosDetenidos);

                    echo '
                   <div class="row">
                    <div class="col-12">
                        <div class="card">
                         <div class="card-block">                                                   
                               <h3> Estadísticas de detenidos por distrito </h3>
                                <div id="column_id"></div>                                                        
                            </div>
                        </div>
                    </div>
                    </div>
                    ';

                    echo "
                    <script type='text/javascript'>
                            google.charts.load('current', {'packages':['bar']});
                            google.charts.setOnLoadCallback(drawColumns);                            
                    </script>";

                }

                elseif ($eleccion == 'Estadísticas de accidentes por distritos') {
                   
                /* Accidentes */
                $datosAccidentes = $estadisticas->obtenerDatosAccidentes();

                    echo '
                   <div class="row">
                     <div class="col-12">
                        <div class="card">
                         <div class="card-block">                          
                         <h3> Estadísticas de accidentes por distrito </h3> 
                                <div id="doubleColumn_id"></div>
                        </div>
                        </div>
                        </div>                  
                    </div>';

                    echo "
                    <script type='text/javascript'>
                            google.charts.load('current', {'packages':['bar']});
                            google.charts.setOnLoadCallback(drawColumnsDouble);
                    </script>";

                }

                elseif ($eleccion == 'Estadísticas relacionadas con la seguridad') {                   
                    /* Seguridad */

                   /* echo '<form method="post" name="selecDist" action="estadisticasPolicia.php" id="buscarAlertas">
                    <input type="hidden" id="selecDist" name="selecDist" value="selecDist"/>';

                    if(isset($_POST['selecDist'])){     */               
                        $distrito = $_POST['distritos'];         

                        $datosSeguridad = $estadisticas->obtenerDatosSeguridad($distrito);
                        $totalSeguridad = $estadisticas->crearCamposOcultosSeguridad($distrito, $datosSeguridad);

                        echo '
                       <div class="row">
                         <div class="col-12">
                            <div class="card">
                             <div class="card-block">                              
                             <h3> Estadísticas relacionadas con la seguridad </h3> 
                                    <div id="piechart"></div>                                   
                                </div>
                            </div>                            
                        </div>
                    </div>
                    </form>';
                    
               
                    echo "                   
                    <script type='text/javascript'>
                            google.charts.load('current', {'packages':['corechart']});
                            google.charts.setOnLoadCallback(drawChartSeguridad);                            
                     </script>";

                    /*}*/
                }

                    echo "<p> Fuente: <a href='http://datos.madrid.es/portal/site/egob/menuitem.c05c1f754a33a9fbe4b2e4b284f1a5a0/?vgnextoid=bffff1d2a9fdb410VgnVCM2000000c205a0aRCRD&vgnextchannel=374512b9ace9f310VgnVCM100000171f5a0aRCRD'>Datos estadísticos actuaciones Policía Municipal </a> </p>";


                  }
              
                ?>
                </form>
                
                <!-- ============================================================== -->
                <!-- End Bread crumb and right sidebar toggle -->
                <!-- ============================================================== -->               
                <!-- ============================================================== -->
                <!-- End PAge Content -->
                <!-- ============================================================== -->
            </div>
            <!-- ============================================================== -->
            <!-- End Container fluid  -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- footer -->
            <!-- ============================================================== -->
            <footer class="footer">
                © 2017 MadAlert
            </footer>

            
            <!-- ============================================================== -->
            <!-- End footer -->
            <!-- ============================================================== -->
        </div>
        <!-- ============================================================== -->
        <!-- End Page wrapper  -->
        <!-- ============================================================== -->
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
    <!--stickey kit AQUI ESTA LO DE SELECCIONAR EL PRIMER ELEMENTOO CO?OO-->
    <script src="assets/plugins/sticky-kit-master/dist/sticky-kit.min.js"></script>   
    <!--Custom JavaScript -->
    <script src="js/custom.min.js"></script>
    <script>
        $(window).resize(function(){
            drawColumns();
            drawColumnsDouble();
            drawChartSeguridad();
        });
    </script>
</body>

</html>
