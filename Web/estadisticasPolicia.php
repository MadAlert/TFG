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
    <script src="js/javaScriptEstadisticas.js"></script> <!-- nuevo script de estadisticas--> 
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





                <!-- Start Page Content -->
                <!-- ============================================================== -->
                <form method="post" name="alertas" action="estadisticasPolicia.php" id="buscarAlertas">
                <input type="hidden" id="busqueda" name="busqueda" value="busqueda"/>
                <?php                 
                    include ("claseAlertas.php");
                    $alertas = new claseAlertas();

                    //$atributo = $_POST['atributo'];
                    echo '<div class="row">
                             <div class="col-12">
                                <div class="card">
                                 <div class="card-block">
                                 <h1 class="card-title"> Estadísticas de la Policía Municipal </h1>
                                    <div class="form-group">
                                        <label class="col-sm-12">Selecciona un distrito</label>
                                        <div class="col-sm-12">';
                                    $alertas->mostrarDistritos();
                                    
                                echo '  </div>                                      
                                      </div>
                                    </div>
                                
                                        <div class="form-group" style="margin: auto; margin-bottom: 20px;">
                                             <div class="items col-sm-12">
                                                 <button class="btn btn-success" id="alertas">Mostrar</button>
                                             </div>    
                                         </div>
                                    </div>
                            </div>
                        </div> 
                    </form>';

                    include("claseEstadisticas.php");
                    $estadisticas = new claseEstadisticas();
                    $mes = $estadisticas->obtenerMesEstPolicias("Centro", "mes");                    

                    echo '<div class="row">
                     <div class="col-12">
                        <div class="card">
                         <div class="card-block">                        
                                <h1> Estadísticas del mes de ' . $mes . '</h1>
                         </div>
                        </div>
                    </div>
                    </div>';
                    

                    $totalArganzuela= $estadisticas->obtenerEstDetenidos("Arganzuela", "detenidos");
                    $totalBarajas= $estadisticas->obtenerEstDetenidos("Barajas", "detenidos");
                    $totalCarabanchel= $estadisticas->obtenerEstDetenidos("Carabanchel", "detenidos");
                    $totalCentro= $estadisticas->obtenerEstDetenidos("Centro", "detenidos");
                    $totalChamartin= $estadisticas->obtenerEstDetenidos("Chamartín", "detenidos");
                    $totalChamberi= $estadisticas->obtenerEstDetenidos("Chamberí", "detenidos");   
                    $totalCiudadLineal= $estadisticas->obtenerEstDetenidos("Ciudad Lineal", "detenidos");
                    $totalFuencarral= $estadisticas->obtenerEstDetenidos("Fuencarral-El Pardo", "detenidos");                    
                    $totalHortaleza= $estadisticas->obtenerEstDetenidos("Hortaleza", "detenidos");
                    $totalLatina= $estadisticas->obtenerEstDetenidos("Latina", "detenidos");
                    $totalMoncloa= $estadisticas->obtenerEstDetenidos("Moncloa-Aravaca", "detenidos");
                    $totalMoratalaz= $estadisticas->obtenerEstDetenidos("Moratalaz", "detenidos");
                    $totalPuenteVallecas= $estadisticas->obtenerEstDetenidos("Puente de Vallecas", "detenidos");
                    $totalRetiro= $estadisticas->obtenerEstDetenidos("Retiro", "detenidos");
                    $totalSalamanca= $estadisticas->obtenerEstDetenidos("Salamanca", "detenidos");
                    $totalSanBlas= $estadisticas->obtenerEstDetenidos("San Blas-Canillejas", "detenidos");
                    $totalTetuan= $estadisticas->obtenerEstDetenidos("Tetuán", "detenidos");
                    $totalUsera= $estadisticas->obtenerEstDetenidos("Usera", "detenidos");
                    $totalVicalvaro= $estadisticas->obtenerEstDetenidos("Vicálvaro", "detenidos");
                    $totalVillaVallecas= $estadisticas->obtenerEstDetenidos("Villa de Vallecas", "detenidos");
                    $totalVillaverde= $estadisticas->obtenerEstDetenidos("Villaverde", "detenidos");                    

                    
                    echo ' <input type="hidden" name="arganzuela" value='.$totalArganzuela.' id="arganzuela"/>';     
                    echo ' <input type="hidden" name="barajas" value='.$totalBarajas.' id="barajas"/>';
                    echo ' <input type="hidden" name="carabanchel" value='.$totalCarabanchel.' id="carabanchel"/>';
                    echo ' <input type="hidden" name="centro" value='.$totalCentro.' id="centro"/>';
                    echo ' <input type="hidden" name="chamartin" value='.$totalChamartin.' id="chamartin"/>';
                    echo ' <input type="hidden" name="chamberi" value='.$totalChamberi.' id="chamberi"/>';
                    echo ' <input type="hidden" name="ciudadLineal" value='.$totalCiudadLineal.' id="ciudadLineal"/>';
                    echo ' <input type="hidden" name="fuencarral" value='.$totalFuencarral.' id="fuencarral"/>';                 
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

                    echo "
                    <script type='text/javascript'>
                            google.charts.load('current', {'packages':['bar']});
                            google.charts.setOnLoadCallback(drawColumns);
                    </script>";

                    echo '
                   <div class="row">
                     <div class="col-12">
                        <div class="card">
                         <div class="card-block">                        
                         <h2> Estadísticas de detenidos por distrito </h2> 
                                <div id="column_id" style="width: 900px; height: 500px;"></div>
                        </div>
                        </div>
                    </div>
                </div>';


                /*Accidentes */
                    $totalArganzuela= $estadisticas->obtenerEstAccidentes("Arganzuela", "conHeridos", "sinHeridos");
                    $totalBarajas= $estadisticas->obtenerEstAccidentes("Barajas", "conHeridos", "sinHeridos");
                    $totalCarabanchel= $estadisticas->obtenerEstAccidentes("Carabanchel", "conHeridos", "sinHeridos");
                    $totalCentro= $estadisticas->obtenerEstAccidentes("Centro", "conHeridos", "sinHeridos");
                    $totalChamartin= $estadisticas->obtenerEstAccidentes("Chamartín", "conHeridos", "sinHeridos");
                    $totalChamberi= $estadisticas->obtenerEstAccidentes("Chamberí", "conHeridos", "sinHeridos");   
                    $totalCiudadLineal= $estadisticas->obtenerEstAccidentes("Ciudad Lineal", "conHeridos", "sinHeridos");
                    $totalFuencarral= $estadisticas->obtenerEstAccidentes("Fuencarral-El Pardo", "conHeridos", "sinHeridos");     
                    $totalHortaleza= $estadisticas->obtenerEstAccidentes("Hortaleza", "conHeridos", "sinHeridos");
                    $totalLatina= $estadisticas->obtenerEstAccidentes("Latina", "conHeridos", "sinHeridos");
                    $totalMoncloa= $estadisticas->obtenerEstAccidentes("Moncloa-Aravaca", "conHeridos", "sinHeridos");
                    $totalMoratalaz= $estadisticas->obtenerEstAccidentes("Moratalaz", "conHeridos", "sinHeridos");
                    $totalPuenteVallecas= $estadisticas->obtenerEstAccidentes("Puente de Vallecas", "conHeridos", "sinHeridos");
                    $totalRetiro= $estadisticas->obtenerEstAccidentes("Retiro", "conHeridos", "sinHeridos");
                    $totalSalamanca= $estadisticas->obtenerEstAccidentes("Salamanca", "conHeridos", "sinHeridos");
                    $totalSanBlas= $estadisticas->obtenerEstAccidentes("San Blas-Canillejas", "conHeridos", "sinHeridos");
                    $totalTetuan= $estadisticas->obtenerEstAccidentes("Tetuán", "conHeridos", "sinHeridos");
                    $totalUsera= $estadisticas->obtenerEstAccidentes("Usera", "conHeridos", "sinHeridos");
                    $totalVicalvaro= $estadisticas->obtenerEstAccidentes("Vicálvaro", "conHeridos", "sinHeridos");
                    $totalVillaVallecas= $estadisticas->obtenerEstAccidentes("Villa de Vallecas", "conHeridos", "sinHeridos");
                    $totalVillaverde= $estadisticas->obtenerEstAccidentes("Villaverde", "conHeridos", "sinHeridos");                  

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



                   echo "
                    <script type='text/javascript'>
                            google.charts.load('current', {'packages':['bar']});
                            google.charts.setOnLoadCallback(drawColumnsDouble);
                    </script>";

                    echo '
                   <div class="row">
                     <div class="col-12">
                        <div class="card">
                         <div class="card-block">
                         <h2> Estadísticas de accidentes por distrito </h2> 
                                <div id="doubleColumn_id" style="width: 900px; height: 500px;"></div>
                        </div>
                        </div>
                    </div>
                </div>';


                    if(isset($_POST['busqueda'])){                    
                        $distrito = $_POST['distritos'];                        
                                                                                          
                        $totalPersonas= $estadisticas->obtenerEstSeguridad($distrito, 'personas');                        
                        $totalPatrimonio = $estadisticas->obtenerEstSeguridad($distrito, 'patrimonio');                        
                        $totalArmas= $estadisticas->obtenerEstSeguridad($distrito, 'armas');
                        $totalTenDrogas= $estadisticas->obtenerEstSeguridad($distrito, 'ten_drogas');
                        $totalConDrogas= $estadisticas->obtenerEstSeguridad($distrito, 'con_drogas');
                        echo ' <input type="hidden" name="distrito" value='.$distrito.' id="distrito"/>';
                        echo ' <input type="hidden" name="personas" value='.$totalPersonas.' id="personas"/>';
                        echo ' <input type="hidden" name="patrimonio" value='.$totalPatrimonio.' id="patrimonio"/>';
                        echo ' <input type="hidden" name="armas" value='.$totalArmas.' id="armas"/>';
                        echo ' <input type="hidden" name="ten_drogas" value='.$totalTenDrogas.' id="ten_drogas"/>';
                        echo ' <input type="hidden" name="con_drogas" value='.$totalConDrogas.' id="con_drogas"/>';                        
                        echo '
                       <div class="row">
                         <div class="col-12">
                            <div class="card">
                             <div class="card-block">
                             <h2> Estadísticas relacionadas con la seguridad</h2> 
                                    <div id="piechart" style="width: 900px; height: 500px;"></div>                                   
                            </div>
                            </div>
                        </div>
                    </div>';
                    
               
                  
                    echo "                   
                    <script type='text/javascript'>
                            google.charts.load('current', {'packages':['corechart']});
                            google.charts.setOnLoadCallback(drawChartSeguridad);
                     </script>

                      <p> Fuente: <a href='http://datos.madrid.es/portal/site/egob/menuitem.c05c1f754a33a9fbe4b2e4b284f1a5a0/?vgnextoid=bffff1d2a9fdb410VgnVCM2000000c205a0aRCRD&vgnextchannel=374512b9ace9f310VgnVCM100000171f5a0aRCRD'> Datos estadísticos actuaciones Policía Municipal </a> </p>";


                  }
                ?>
                
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
</body>

</html>
