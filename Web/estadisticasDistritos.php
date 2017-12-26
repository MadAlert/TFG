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
                <!-- Bread crumb and right sidebar toggle -->
                <!-- ============================================================== -->

            

                
                <!-- ============================================================== -->
                <!-- Start Page Content -->
                <!-- ============================================================== -->
                <form method="post" name="alertas" action="estadisticasDistritos.php" id="buscarAlertas">
                <input type="hidden" id="busqueda" name="busqueda" value="busqueda"/>
                <?php 
                    include ("claseAlertas.php");
                    $alertas = new claseAlertas();

                    //$atributo = $_POST['atributo'];
                    echo '<div class="row">
                             <div class="col-12">
                                <div class="card">
                                 <div class="card-block">';
                                    $alertas->mostrarDistritos();
                                echo '</div>
                                        <div class="form-group" style="margin: auto; margin-bottom: 20px;">
                                             <div class="items col-sm-12">
                                                 <button class="btn btn-success" id="alertas">Mostrar</button>
                                             </div>    
                                         </div>
                                    </div>
                            </div>
                        </div> 
                    </form>';
                    
                    if(isset($_POST['busqueda'])){
                        echo'
                        <div class="row">
                         <div class="col-12">
                            <div class="card">
                             <div class="card-block">';
                        $distrito = $_POST['distritos'];
                        include ("claseEstadisticas.php");
                        echo '<h3>Estadisticas en el distrito: '.$distrito .' </h3>';
                        $estadisticas = new claseEstadisticas();
                        //Calculos para el mes 1
                        $mes1 = $estadisticas->obtenerMes("1");
                        $lista = $estadisticas->obtenerDatos($distrito, $mes1);
                        $noHayEstadisticas1 = $estadisticas->noHayEstadisticas($lista);
                        //Calculos para el mes 2
                        $mes2 =  $estadisticas->obtenerMes("2");
                        $lista2 = $estadisticas->obtenerDatos($distrito, $mes2);
                        $noHayEstadisticas2 = $estadisticas->noHayEstadisticas($lista2);
                        $mostrado = false;
                        //No hay estadisticas de ningun mes
                        if($noHayEstadisticas1 && $noHayEstadisticas2){
                            $mostrado = true;
                            $mes1 = $estadisticas->mesEnLetras($mes1);
                            $mes2 = $estadisticas->mesEnLetras($mes2);
                            echo '<table class="columns">
                                        <td>
                                        <tr><p>No hay estadísticas para el mes de ' .$mes1.'</p></tr>
                                         <hr></hr>
                                        <tr><p>No hay estadísticas para el mes de ' .$mes2.'</p></tr>
                                        </td>
                                    </table>';
                        }
                        //Hay estadisticas de ambos meses
                        if(!$noHayEstadisticas1 && !$noHayEstadisticas2){
                            $mostrado = true;
                            $estadisticas->crearCamposOcultosPrimerMes($distrito, $lista , $mes1);
                            $estadisticas->crearCamposOcultosSegundoMes($distrito, $lista2, $mes2);
                            echo '<table class="columns">
                                        <td>
                                        <tr><div id="piechart" ></div></tr>
                                        <hr></hr>
                                        <tr><div id="piechart2"></div></tr>
                                        </td>
                                    </table>';
                            echo "
                            <script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'>
                            google.load()</script>
                            <script type='text/javascript'>
                                    google.charts.load('current', {'packages':['corechart']});
                                    google.charts.setOnLoadCallback(drawChart);
                                    google.charts.setOnLoadCallback(drawChart2);
                            </script>";
                        }
                        //No hay estadisticas del mes 1 , pero si del 2
                        if($noHayEstadisticas1 && !$mostrado){
                           $estadisticas->crearCamposOcultosSegundoMes($distrito, $lista2, $mes2);
                           $mes1 = $estadisticas->mesEnLetras($mes1);
                           echo '<table class="columns">
                                        <td>
                                        <tr><p>No hay estádisticas para el mes de ' .$mes1.'</p></tr>
                                         <hr></hr>
                                        <tr><div id="piechart2"></div></tr>
                                        </td>
                                    </table>';
                            echo "
                            <script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'>
                            google.load()</script>
                            <script type='text/javascript'>
                                    google.charts.load('current', {'packages':['corechart']});
                                    google.charts.setOnLoadCallback(drawChart2);
                            </script>";
                        }
                        //No hay estadisticas del mes 2 pero si del 1
                        if($noHayEstadisticas2 && !$mostrado){
                           $estadisticas->crearCamposOcultosPrimerMes($distrito, $lista , $mes1);
                           $mes2 = $estadisticas->mesEnLetras($mes2);
                           echo '<table class="columns">
                                        <td>
                                        <tr><div id="piechart"></div></tr>
                                        <hr></hr>
                                        <tr><p>No hay estádisticas para el mes de ' .$mes2.'</p></tr>
                                        </td>
                                    </table>';
                            echo "
                            <script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'>
                            google.load()</script>
                            <script type='text/javascript'>
                                    google.charts.load('current', {'packages':['corechart']});
                                    google.charts.setOnLoadCallback(drawChart);
                            </script>";
                        }
                  echo '</div>
                            </div>
                        </div>
                    </div>';
              }
                ?>
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
    <script src="js/javaScriptEstadisticas.js"></script> <!-- nuevo script de estadisticas-->
</body>
</html>
