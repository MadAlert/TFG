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
                                                 <?php 
                                                     include ("claseAlertas.php");
                                                     $alertas = new claseAlertas();
                                                     $alertas->mostrarCategorias();
                                                ?>                              
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


                        //Obtengo el total por la categoria seleccionada (segun el distrito)
                       /*  $totalAravaca= $estadisticas->obtenerEstadisticas("Aravaca", $categoria);
                         $totalBarajas= $estadisticas->obtenerEstadisticas("Barajas", $categoria);
                         $totalCarabanchel= $estadisticas->obtenerEstadisticas("Carabanchel", $categoria);
                         $totalCentro= $estadisticas->obtenerEstadisticas("Centro", $categoria);
                         $totalChamartin= $estadisticas->obtenerEstadisticas("Chamartin", $categoria);
                         $totalChamberi= $estadisticas->obtenerEstadisticas("Chamberi", $categoria);   
                         $totalCiudadLineal= $estadisticas->obtenerEstadisticas("CiudadLineal", $categoria);
                         $totalFuencarral= $estadisticas->obtenerEstadisticas("Fuencarral", $categoria);
                         $totalGeneral= $estadisticas->obtenerEstadisticas("General", $categoria);
                         $totalHortaleza= $estadisticas->obtenerEstadisticas("Hortaleza", $categoria);
                         $totalLatina= $estadisticas->obtenerEstadisticas("Latina", $categoria);
                         $totalMoncloa= $estadisticas->obtenerEstadisticas("Moncloa", $categoria);
                         $totalMoratalaz= $estadisticas->obtenerEstadisticas("Moratalaz", $categoria);
                         $totalPuenteVallecas= $estadisticas->obtenerEstadisticas("Puente Vallecas", $categoria);
                         $totalRetiro= $estadisticas->obtenerEstadisticas("Retiro", $categoria);
                         $totalSalamanca= $estadisticas->obtenerEstadisticas("Salamanca", $categoria);
                         $totalSanBlas= $estadisticas->obtenerEstadisticas("San Blas", $categoria);
                         $totalTetuan= $estadisticas->obtenerEstadisticas("Tetuan", $categoria);
                         $totalUsera= $estadisticas->obtenerEstadisticas("Usera", $categoria);
                         $totalVicalvaro= $estadisticas->obtenerEstadisticas("Vicalvaro", $categoria);
                         $totalVillaVallecas= $estadisticas->obtenerEstadisticas("Villa de Vallecas", $categoria);
                         $totalVillaverde= $estadisticas->obtenerEstadisticas("Villaverde", $categoria);




                         echo ' <input type="hidden" name="categoria" value='.$categoria.' id="categoria"/>';
                         echo ' <input type="hidden" name="aravaca" value='.$totalAravaca.' id="aravaca"/>';     
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
                         echo ' <input type="hidden" name="villaverde" value='.$totalVillaverde.' id="villaverde"/>';*/



                         /* echo ' <div class="row">
                            <div class="col-12">
                                <div class="card">
                                        <div class="card-block">';
                                            echo "<b> Categoria:  $categoria </b>";
                                             echo '<div id="chart_div" style="width: 900px; height: 300px;"></div>';


                                       echo ' </div>
                                </div>
                            </div>
                        </div>';*/



/*echo '
                         <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <div id="chart_div"></div>';

                         echo "
                            <script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'>
                            google.load()</script>
                            <script type='text/javascript'>
                                    google.charts.load('current', {'packages':['corechart']});
                                    google.charts.setOnLoadCallback(drawChart);
                                </script>";*/



                                 echo '
                       <div class="row">
                         <div class="col-12">
                            <div class="card">
                             <div class="card-block">
                                    <div id="columnchart_values></div>
                            </div>
                            </div>
                        </div>
                    </div>';
                    
               
                  
                    echo "
                    <script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'>
                    google.load()</script>
                    <script type='text/javascript'>
                            google.charts.load('current', {'packages':['corechart']});
                            google.charts.setOnLoadCallback(drawMultSeries);
                        </script>";
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


</body>

</html>
