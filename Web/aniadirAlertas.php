<!DOCTYPE html>
<html lang="en">

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
    <link href="css/contacto.css" id="theme" rel="stylesheet">
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
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-block">
                                <h1 class="card-title"> Añadir alertas en tiempo real </h1>

                                <form class="form-horizontal form-material" class="contacto" action="procesarAniadir.php" method="post">
                                    <div class="form-group">
                                        <div class="col-md-6">
                                            <input type="text" placeholder="Nombre" class="form-control form-control-line" name="nombre" id="nombre" required/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-6">
                                            <input type="email" placeholder="Email" class="form-control form-control-line" name="email" id="email" required/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-6">Selecciona una categoria</label>
                                        <div class="col-md-6">
                                            <?php 
                                            include ("claseAlertas.php");
                                            $alertas = new claseAlertas();
                                            $alertas->mostrarCategorias();
                                            ?>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-6">Selecciona un distrito</label>
                                        <div class="col-md-6">
                                            <?php
                                            $alertas->mostrarDistritos();
                                            ?>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-6">
                                            <textarea rows="5" placeholder="Introduce la alerta..."
                                            class="form-control form-control-line" name="alerta" id="alerta" required/></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-6">
                                            <button class="btn btn-danger" type="submit" id="botonAlert">Añadir alerta</button><!--data-toggle="modal" data-target="#bsModal3"-->
                                        </div>
                                    </div>
                                    <div class="correcto" id="correcto">Su alerta se ha insertado correctamente</div>
                                    <div class="incorrecto" id="incorrecto">La categoría elegida no es la correspondiente a su alerta, intentelo de nuevo </div>
                                </form>  
                                
                                <!-- Modal
                                <div class="modal fade" id="bsModal3" tabindex="0" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                                  <div class="modal-dialog modal-sm">
                                    <div class="modal-content">
                                      <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                                      </div>
                                      <div class="modal-body">
                                        Su alerta ha sido creada correctamente.
                                      </div>
                                      <div class="modal-footer">
                                        <button type="button" class="btn btn-info" data-dismiss="modal">Aceptar</button>
                                      </div>
                                    </div>
                                  </div>
                                </div> -->                                                             
                            </div>
                        </div>
                    </div>
                </div>
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
    <script src="js/javaScript.js"></script>
    <!-- google maps api -->
</body>

</html>
