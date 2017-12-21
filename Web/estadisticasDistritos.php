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
                        $distrito = $_POST['distritos'];
                        include ("claseEstadisticas.php");
                        $estadisticas = new claseEstadisticas();
                        $totalTerrorismo= $estadisticas->obtenerEstadisticas($distrito, "Terrorismo");
                        $totalDesastres = $estadisticas->obtenerEstadisticas($distrito, "Desastres y accidentes");
                        $totalEventos= $estadisticas->obtenerEstadisticas($distrito, "Eventos");
                        $totalTransporte= $estadisticas->obtenerEstadisticas($distrito, "Transporte público");
                        $totalCriminalidad= $estadisticas->obtenerEstadisticas($distrito, "Criminalidad");
                        $totalContaminacion= $estadisticas->obtenerEstadisticas($distrito, "Contaminación");
                        $totalTrafico= $estadisticas->obtenerEstadisticas($distrito, "Tráfico");
                        echo ' <input type="hidden" name="distrito" value='.$distrito.' id="distrito"/>';
                        echo ' <input type="hidden" name="terrorismo" value='.$totalTerrorismo.' id="terrorismo"/>';
                        echo ' <input type="hidden" name="desastres" value='.$totalDesastres.' id="desastres"/>';
                        echo ' <input type="hidden" name="eventos" value='.$totalEventos.' id="eventos"/>';
                        echo ' <input type="hidden" name="transporte" value='.$totalTransporte.' id="transporte"/>';
                        echo ' <input type="hidden" name="terrorismo" value='.$totalTerrorismo.' id="terrorismo"/>';
                        echo ' <input type="hidden" name="criminalidad" value='.$totalCriminalidad.' id="criminalidad"/>';
                        echo ' <input type="hidden" name="contaminacion" value='.$totalContaminacion.' id="contaminacion"/>';
                        echo ' <input type="hidden" name="trafico" value='.$totalTrafico.' id="trafico"/>';
                        echo '
                       <div class="row">
                         <div class="col-12">
                            <div class="card">
                             <div class="card-block">
                                    <div id="piechart" style="width: 900px; height: 500px;"></div>
                            </div>
                            </div>
                        </div>
                    </div>';
                    
               
                  
                    echo "
                    <script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'>
                    google.load()</script>
                    <script type='text/javascript'>
                            google.charts.load('current', {'packages':['corechart']});
                            google.charts.setOnLoadCallback(drawChart);
                        </script>";
                  }
                ?>

                
               
                
                <!-- ============================================================== -->
                <!-- End Bread crumb and right sidebar toggle -->
                <!-- ============================================================== -->
                <!-- ============================================================== -->
                <!-- Start Page Content -->
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
    <script src="js/javaScriptEstadisticas.js"></script> <!-- nuevo script de estadisticas-->
    

   


</body>

</html>
