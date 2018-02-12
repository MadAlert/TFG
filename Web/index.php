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
                                    <div id="mapa" class="gmaps"></div>
                                    <form method="post" action="alertas.php" id="buscarAlertas" onsubmit="return valida()">
                                         <input type="hidden" name="atributo" id="atributo" value="si"/>
                                         <div class="form-group">
                                             <?php 
                                                 include ("claseAlertas.php");
                                                 $alertas = new claseAlertas();
                                                 $alertas->mostrarDistritos();
                                            ?>                              
                                         </div>
                                        <div class="form-group">
                                                <label class="col-sm-12">Selecciona las categorías</label>
                                                <div class="items-collection">
                                                    <div class="items col-xs-12 col-md-4 col-lg-3">
                                                        <div class="info-block block-info clearfix">
                                                            <div data-toggle="buttons" class="btn-group bizmoduleselect">
                                                               <label class="btn btn-default">
                                                                   <div class="itemcontent">
                                                                        <input type="checkbox" id="var_id[]" name="var_id[]" autocomplete="off" value="Desastres y accidentes">
                                                                         <h6>Desastres y accidentes</h6>
                                                                    </div>
                                                                 </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                
                                                    <div class="items col-xs-12 col-md-4 col-lg-3">
                                                        <div class="info-block block-info clearfix">
                                                            <div data-toggle="buttons" class="btn-group itemcontent">
                                                                <label class="btn btn-default">
                                                                    <div class="itemcontent">
                                                                        <input type="checkbox" id="var_id[]" name="var_id[]" autocomplete="off" value="Terrorismo">
                                                                        <h6>Terrorismo</h6>
                                                                    </div>
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="items col-xs-12 col-md-4 col-lg-3">
                                                        <div class="info-block block-info clearfix">
                                                            <div data-toggle="buttons" class="btn-group itemcontent">
                                                                <label class="btn btn-default">
                                                                    <div class="itemcontent">
                                                                        <input type="checkbox" name="var_id[]" autocomplete="off" value="Criminalidad">
                                                                        <h6>Criminalidad</h6>
                                                                    </div>
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="items col-xs-12 col-md-4 col-lg-3">
                                                        <div class="info-block block-info clearfix">
                                                            <div data-toggle="buttons" class="btn-group itemcontent">
                                                                <label class="btn btn-default">
                                                                    <div class="itemcontent">
                                                                        <input type="checkbox" name="var_id[]" autocomplete="off" value="Tráfico">
                                                                        <h6>Tráfico</h6>
                                                                    </div>
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="items col-xs-12 col-md-4 col-lg-3">
                                                        <div class="info-block block-info clearfix">
                                                            <div data-toggle="buttons" class="btn-group itemcontent">
                                                                <label class="btn btn-default">
                                                                    <div class="itemcontent">
                                                                        <input type="checkbox" name="var_id[]" autocomplete="off" value="Eventos">
                                                                        <h6>Eventos</h6>
                                                                    </div>
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="items col-xs-12 col-md-4 col-lg-3">
                                                        <div class="info-block block-info clearfix">
                                                            <div data-toggle="buttons" class="btn-group itemcontent">
                                                                <label class="btn btn-default">
                                                                    <div class="itemcontent">
                                                                        <input type="checkbox" name="var_id[]" autocomplete="off" value="Transporte público">
                                                                        <h6>Transporte público</h6>
                                                                    </div>
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="items col-xs-12 col-md-4 col-lg-3">
                                                        <div class="info-block block-info clearfix">
                                                            <div data-toggle="buttons" class="btn-group itemcontent">
                                                                <label class="btn btn-default">
                                                                    <div class="itemcontent">
                                                                        <input type="checkbox" name="var_id[]" autocomplete="off" value="Contaminación">
                                                                        <h6>Contaminación</h6>
                                                                    </div>
                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>    
                                        </div>                              
                                    
                                </div>
                                    <div class="form-group" style="margin: auto; margin-bottom: 20px;">
                                        <div class="items col-sm-12">
                                            <button class="btn btn-success" id="alertas">Buscar</button>
                                        </div>    
                                    </div>
                                    </form>
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
    <script src="javaScript.js"></script>
    <!-- google maps api -->
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
    <script>
      function initMap() {
        var marcadores = [
            ['Arganzuela', 40.400861, -3.699350], //Arganzuela
            ['Barajas', 40.4839402, -3.5701402], //Barajas
            ['Carabanchel', 40.381607, -3.735203], //Carabanchel
            ['Centro', 40.4169416, -3.7083759], //Centro
            ['Chamartín', 40.460367, -3.676567], //Chamartín
            ['Chamberí', 40.438656, -3.704180], //Chamberí
            ['Ciudad Lineal', 40.455531, -3.656119], //Ciudad Lineal
            ['Fuencarral-ElPardo', 40.494289, -3.693477], //Fuencarral-ElPardo
            ['Hortaleza', 40.485152, -3.634796], //Hortaleza
            ['Latina', 40.387812, -3.773530], //Latina
            ['Moncloa-Aravaca', 40.443568,  -3.742829], //Moncloa-Aravaca
            ['Moratalaz', 40.407016, -3.644330], //Moratalaz
            ['Puente de Vallecas', 40.386887, -3.658476], //Puente de Vallecas
            ['Retiro', 40.4101076, -3.6736514], //Retiro
            ['Salamanca', 40.429807, -3.673778], //Salamanca
            ['San Blas-Canillejas', 40.436229, -3.599431], //San Blas-Canillejas
            ['Tetuán', 40.460158, -3.698835], //Tetuán
            ['Usera', 40.377026, -3.701982], //Usera
            ['Vicálvaro', 40.393974, -3.581134], //Vicálvaro
            ['Villa de Vallecas', 40.355089, -3.621192], //Villa de Vallecas
            ['Villaverde', 40.345987, -3.693332] //Villaverde
        ];

        var map = new google.maps.Map(document.getElementById('mapa'),{
          zoom: 12,
          zoomControl: false,
          streetViewControl: false,
          scrollwheel: false,
          center: new google.maps.LatLng(40.422163, -3.689101),
        });

        var infowindow = new google.maps.InfoWindow(); //Abre ventana del marcador
        // Create an array of alphabetical characters used to label the markers.
        //var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';

        /*var markers = locations.map(function(location, i) {
          return new google.maps.Marker({
            position: locations,
            label: labels[i % labels.length]
          });
        });*/

        var marker, i;
        for (i = 0; i < marcadores.length; i++) {  
        marker = new google.maps.Marker({
          position: new google.maps.LatLng(marcadores[i][1], marcadores[i][2]),
          map: map
        });
        google.maps.event.addListener(marker, 'click', (function(marker, i) {
          return function() {
            infowindow.setContent(marcadores[i][0]);
            infowindow.open(map, marker);
          }
        })(marker, i));
      }

        //Muestra todas las marcas del array locations en el mapa
        /*var markersCluster = new MarkerClusterer(map, markers,
            {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
        //marker.setMap(map);
        */
      }
      //google.maps.event.addDomListener(window, 'load', initMap);
    </script>
    <script async defer src="https://maps.google.com/maps/api/js?key=AIzaSyAgXFPH3QXZqFd571ptZm6p2dNYK18aSQQ&callback=initMap"></script>
    <script src="assets/plugins/gmaps/gmaps.min.js"></script>
    <!--script src="assets/plugins/gmaps/jquery.gmaps.js"></script-->
</body>

</html>
