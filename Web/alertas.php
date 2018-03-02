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

                <form method="post" action="alertas.php" id="buscarAlertas" ">
                <!-- ============================================================== -->
                <!-- Start Page Content -->
                <!-- ============================================================== -->
               


                <?php 
                    include ("claseAlertas.php");
                    $alertas = new claseAlertas();

                    if(isset($_POST['distritoM'])) {
                        $distritoMapa = $_POST['distritoM'];
                    }
                    
                    if(!isset($_POST['atributo'])){
                        if(isset($distritoMapa)) {
                ?>
                            <div class="col-lg-13 col-xlg-14 col-md-12">
                                <div class="card">
                                    <ul class="nav nav-tabs profile-tab" role="tablist">
                                        <?php
                                        echo "<li class='nav-item'> <a class='nav-link active' data-toggle='tab' role='tab'><b>Distrito $distritoMapa</b></a> </li>"
                                        ?>
                                    </ul>
                                    <div class="tab-content">
                                        <div class="tab-pane active" id="home" role="tabpanel">
                                            <div class="card-block">
                                                <?php
                                                    $num = $alertas->obtenerAlertasDistrito($distritoMapa);
                                                ?>
                                            </div>  
                                        </div>
                                    </div>
                                </div>
                            </div>
                    <?php
                        } else {
                        //$atributo = $_POST['atributo'];
                        echo '<div class="row">
                                 <div class="col-12">
                                    <div class="card">
                                        <div class="card-block">
                                            <div class="form-group">
                                            <label class="col-sm-12">Selecciona un distrito</label>
                                                <div class="col-sm-12">';
                                                    $alertas->mostrarDistritos();
                                        //$alertas->obtenerAlertasDistrito(atributo);
                                        //echo $atributo;
                                    echo '      </div>                                      
                                            </div>
                                    </div>
                                    
                                            <div class="form-group" style="margin: auto; margin-bottom: 20px;">
                                                 <div class="items col-sm-12">
                                                     <button class="btn btn-success" id="alertas">Mostrar</button>
                                                 </div>    
                                             </div>

                                        </div>
                                </div>
                            </div> ';

                                        if(isset($_POST['distritos'])){
                                            echo ' 
                                              <div class="row">
                                                <div class="col-12">
                                                 <div class="card">
                                                  <div class="tab-content">
                                                        <div class="tab-pane active" id="home" role="tabpanel">
                                                        <div class="card-block"> 
                                                            <ul class="nav nav-tabs profile-tab" role="tablist">';
                                                                
                                                            $distrito = $_POST['distritos'];
                                                            echo "<li class='nav-item'> <a class='nav-link active' data-toggle='tab' role='tab'><b>Distrito $distrito</b></a> </li>";
                                                            
                                                            echo '</ul>';
                                                            $num = $alertas->obtenerAlertasDistrito($distrito);
                                                            if($num == false){
                                                                echo "<p><p><h4>Este distrito no dispone de alertas con esos filtros todavía</h4></p></p>";
                                                            }
                                                        }
                                    
                                    
                                             echo '        </div>
                                                         </div>
                                                     </div>
                                                 </div>
                                             </div>
                                      </div> 
                              </div>
                        </form>';
                        }
                    }
                    

                    if(isset($_POST['atributo'])){
                ?>

                        <!--Recupero los campos de index.php -->

                        <div class="col-lg-13 col-xlg-14 col-md-12">
                                <div class="card">
                                    <!-- Nav tabs -->
                                    <ul class="nav nav-tabs profile-tab" role="tablist">
                                        <?php
                                        //Recupero los campos de index.php
                                        //En index pasar un atributo hidden atributo, hidden distrito y hidden index
                                        //Si es index no hago lo de categorias
                                        if(!isset($distritoMapa)) {
                                            $distrito = $_POST['distritos'];
                                            $categorias = $_POST['var_id'];
                                            $count = count($categorias);

                                            for ($i = 0; $i < $count; $i++) {
                                               $categorias[$i];
                                            }
                                        }
                                        //esto de arriba no se hace si es index
                                        echo "<li class='nav-item'> <a class='nav-link active' data-toggle='tab' role='tab'><b>Distrito $distrito</b></a> </li>"
                                        ?>
                                    </ul>
                                    <!-- Tab panes -->
                                    <div class="tab-content">
                                        <div class="tab-pane active" id="home" role="tabpanel">
                                            <div class="card-block">
                                            <?php
                                                //Compruebo de nuevo si NO es INDEX LLAMO A EsTA FUNCION
                                                if(!isset($distritoMapa)) {
                                                    $num = $alertas->obtenerAlertas($distrito, $categorias);
                                                }
                                                if($num == false){
                                                    echo "<p>Este distrito no dispone de alertas con esos filtros todavía</p>";
                                                }
                                            ?>
                                            </div> 	
                                        </div>
                                    </div>
                                </div>
                        </div>
                <?php
                    }
                ?>
            </form>
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
    <!--stickey kit AQUI ESTA LO DE SELECCIONAR EL PRIMER ELEMENTOO COÑOOO-->
    <script src="assets/plugins/sticky-kit-master/dist/sticky-kit.min.js"></script>
    <!--Custom JavaScript -->
    <script src="js/custom.min.js"></script>
</body>

</html>
