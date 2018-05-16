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
               
                <script type="text/javascript">
                    function reFresh() 
                    location.reload(true)
                    }
                    /* Establece el tiempo 1 minuto = 60000 milliseconds. */
                    window.setInterval("reFresh()",100); 
                </script>

                <?php 
                    include ("claseAlertas.php");
                    $alertas = new claseAlertas();

                    if(isset($_POST['distritoM'])) {
                        $distritoMapa = $_POST['distritoM'];
                        $_SESSION['distrito']="$distritoMapa";
                    }
                                                          ?> 

<?php
                    if(!isset($_POST['atributo'])){
                        if(isset($distritoMapa)) {
                ?>
                      <?php     

                    
                     echo '<div class="row">
                                 <div class="col-12">
                                    <div class="card">
                                        <div class="card-block">
                                            <div class="form-group">
                                            <label class="col-sm-12">Selecciona un distrito</label>
                                                <div class="col-sm-12">';
                                                    $alertas->mostrarDistritos();
                                        
                                    echo '      </div>                                      
                                            </div>
                                    </div>
                                    
                                            <div class="form-group" style="margin: auto; margin-bottom: 20px;">
                                                 <div class="items col-sm-12">
                                                     <button class="btn btn-danger" id="alertas">Mostrar</button>
                                                 </div>    
                                             </div>

                                        </div>
                                </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                             <div class="card">
                              <div class="tab-content">  
                                <div class="tab-pane active" id="home" role="tabpanel">
                                    <div class="card-block">';
                                     ?>
                                       

                                    <form method="POST" action="alertas.php">
                                          <?php 
                                           echo '<input type="hidden" name="distritoMapa" id="distritoMapa" value="'.$distritoMapa.'"/>';
                                           
                            
                                          echo "<button class='btn btn-success' type='submit'> Actualizar alertas<i class='mdi mdi-refresh'></i></button> ";
                                                      
                                          ?>
                                    </form>

                                     <ul class="nav nav-tabs profile-tab" role="tablist">
                                        <?php
                                        
                                        echo "<li class='nav-item'> <a class='nav-link active' data-toggle='tab' role='tab'><b>Distrito: $distritoMapa</b></a> </li>";
                                        //echo "PUNTO 1";
                                        ?>
                                    </ul>


                                    <div class="tab-content">
                                        <div class="tab-pane active" id="home" role="tabpanel">
                                            <div class="card-block">

                                                <?php
                                                    if ($distritoMapa == "Todos"){
                                                                $num = $alertas->obtenerAlertasTodos();
                                                            }
                                                    else{
                                                      $num = $alertas->obtenerAlertasDistrito($distritoMapa);
                                                    }
                                                ?>

                                            </div>  
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </div>
                        </div>
                    </div>
                </div>
                    <?php
                        } else {
                         ?> 
<?php              
                        if (isset($_POST['distritoSilvia'])){
                            $distritoMapa = $_POST['distritoSilvia'];
                            
                         echo ' 
                          <div class="row">
                            <div class="col-12">
                             <div class="card">
                              <div class="tab-content">
                                    <div class="tab-pane active" id="home" role="tabpanel">
                                    <div class="card-block"> ';
?>
                                    <?php
                                    
                                    echo "<button class='btn btn-success' onclick='javascript:window.location.reload();'' > Actualizar alertas <i class='mdi mdi-refresh'></i></button> ";
                                ?>
                                   
                                    <ul class="nav nav-tabs profile-tab" role="tablist">

                                        <?php
                                        //echo "punto 2";
                                        echo "<li class='nav-item'> <a class='nav-link active' data-toggle='tab' role='tab'><b>Distrito: $distritoMapa</b></a> </li>";?>
                                        
                                    </ul>
                                    <div class="tab-content">
                                        <div class="tab-pane active" id="home" role="tabpanel">
                                            <div class="card-block">
                                                <?php
                                                    if(isset($_POST['distritoSilvia4'])){
                                                        $distrito = $_POST['distritoSilvia4'];
                                                        
                                                    }
                                                    if ($distritoMapa == "Todos"){
                                                                $num = $alertas->obtenerAlertasTodos();
                                                            }
                                                    else{
                                                        $num = $alertas->obtenerAlertasDistrito($distritoMapa);
                                                 }
                                                ?>
                                            </div>  
                                        </div>
                                    </div>

                                </div>
                            </div>

                       <?php }
                       else{

                        if ( !isset($_POST['distritoSilvia4']) ){
                        $entraEnMostrar = "si";
                       // echo "Esto en el punto 3.....";
                        echo '<div class="row">
                                 <div class="col-12">
                                    <div class="card">
                                        <div class="card-block">
                                            <div class="form-group">
                                            <label class="col-sm-12">Selecciona un distrito</label>
                                                <div class="col-sm-12">';
                                                    $alertas->mostrarDistritos();
                                        
                                    echo '      </div>                                      
                                            </div>
                                    </div>
                                    ';?>
                                     <form method="post" action="alertas.php">
                                     <?php 
                                    
                                    echo '
                                            <input type="hidden" name="entraEnMostrar" id="entraEnMostrar" value="'.$entraEnMostrar.'"/>
                                            <div class="form-group" style="margin: auto; margin-bottom: 20px;">
                                                 <div class="items col-sm-12">
                                                     <button class="btn btn-danger" id="alertas">Mostrar</button>
                                                 </div>    
                                             </div>
                                             ';
                                             ?>
                                         </form>
                                         <?php
                                         echo '
                                        </div>
                                </div>
                            </div> ';
                        }

                                        if(isset($_POST['distritos']) || isset($_POST['distritoSilvia4']) || isset($_POST['distritoPrueba'])){
                                           //echo "Entro en el punto 3 en el 2 if ";
                                            if (!isset($_POST['distritoSilvia4']) ){ 
                                                $entra = true;

                                            }
                                            else{
                                                $entra = false;
                                                //echo "Entro en distritosilvia4";
                                            }


                                            if (!$entra){
                                            $entraEnMostrar = "si";
                                            echo '<div class="row">
                                             <div class="col-12">
                                                <div class="card">
                                                    <div class="card-block">
                                                        <div class="form-group">
                                                        <label class="col-sm-12">Selecciona un distrito</label>
                                                            <div class="col-sm-12">';
                                                                $alertas->mostrarDistritos();
                                                    
                                                echo '      </div>                                      
                                                        </div>
                                                </div>

                                            <input type="hidden" name="entraEnMostrar" id="entraEnMostrar" value="'.$entraEnMostrar.'"/>;
                                            <div class="form-group" style="margin: auto; margin-bottom: 20px;">
                                                 <div class="items col-sm-12">
                                                     <button class="btn btn-danger" id="alertas">Mostrar</button>
                                                 </div>    
                                             </div>

                                        </div>
                                </div>
                            </div> ';}
                                    $entra = false;
                                            
                                            echo ' 
                                              <div class="row">
                                                <div class="col-12">
                                                 <div class="card">
                                                  <div class="tab-content">
                                                        <div class="tab-pane active" id="home" role="tabpanel">
                                                        <div class="card-block"> ';

                                                            
                                                            //echo "PUNTO 3";
                                    ?>
                                                            <?php
                                                            
                                                            
                                                            if ((isset($_POST['distritos'])) && !isset($_POST['distritoPrueba'])){
                                                                 
                                                                $distrito = $_POST['distritos'];
                                                                
                                                                unset($_POST['distritos']);
                                                              

                                                                //Viene del mapa
                                                                if(isset($_POST['distritosilvia4']) || isset($_POST['categorias']) || (isset($_POST['$categ'] ) && $_POST['$categ'] == "si")){
                                                                    
                                                                    
                                                                    $distrito = $_POST['distritosilvia4'];

                                                                    $categorias = $_POST['categorias']; 


                                                                    $count = count($categorias);
                                                                    for ($i = 0; $i < $count; $i++) {
                                                                       $categorias[$i];
                                                                    }

                                                                    echo ' <input type="hidden" name="categ" value="si" id="categ"/>';
                                                                    

                                                                }
                                                                if(isset($_POST['distritoMapa'])){
                                                                   
                                                                   
                                                                    $distrito = $_POST['distritoMapa'];
                                                                }
                                                                
                                                            }

                                                            else if ( isset($_POST['distritoPrueba'])) {

                                                                $distrito = $_POST['distritoPrueba'];
                                                    
                                                                if (isset($_POST['categorias'])){

                                                                   $categorias = $_POST['categorias']; 

                                                                    $count = count($categorias);
                                                                    for ($i = 0; $i < $count; $i++) {
                                                                       $categorias[$i];
                                                                    }

                                                                    echo ' <input type="hidden" name="categ" value="si" id="categ"/>';
                                                                }

                                                                unset($_POST['distritoPrueba']);
                                                            }
                                                               
                                                            
?>
                                                           
                                                              <form method="post" action="alertas.php">
                                                              <?php 
                                                            $var = "si";
                                                              echo "<button class='btn btn-success' type='submit'> Actualizar alertas<i class='mdi mdi-refresh'></i></button> ";


                                                            echo ' <input type="hidden" name="actualizaP" value="'.$var.'" id="actualizaP"/>';
                                                            echo ' <input type="hidden" name="distritoPrueba" value="'.$distrito.'" id="distritoPrueba"/>';
                                                                                  
                                                            //Pasar array x hidden
                                                            if (isset($categorias)){
                                                            
                                                                foreach($categorias as $value){
                                                                    echo ' <input type="hidden" name="categorias[]" value="'.$value.'" id="categorias[]"/>';
                                                                    //echo $value;
                                                                    //$categ == "si";
                                                                }
                                                                
                                                                echo ' <input type="hidden" name="categ" value="si" id="categ"/>';
                                                            }
                                                                                     
                                                                
                                                              ?>

                                                            </form>


                                                            <ul class="nav nav-tabs profile-tab" role="tablist">

                                                            <?php
                                                           
                                                            echo "<li class='nav-item'> <a class='nav-link active' data-toggle='tab' role='tab'><b>Distrito: $distrito</b></a> </li>";?>
                                                            
                                                           </ul>
                                                        
                                                           <div class="tab-content">
                                                                <div class="tab-pane active" id="home" role="tabpanel">
                                                                    <div class="card-block">
                                                        

                                                          
<?php
                                                            //Si categorias es = si copiar las funciones k buscan x categorias
                                                            //else todo lo de abajo
                                                            if (isset($categorias) || (isset($_POST['$categ'] ) && $_POST['$categ'] == "si")){
                                                                //echo "SI HAY CATEGORIAS";

                                                                if ($distrito == "Todos"){
                                                                    $num = $alertas->obtenerAlertasTodosCat($categorias);
                                                                }
                                                                else{
                                                                    $num = $alertas->obtenerAlertas($distrito, $categorias);
                                                                }
                                                                if($num == false){
                                                                    echo "<p><p><h4>Este distrito no dispone de alertas con esos filtros todavía</h4></p></p>";
                                                                }

                                                       
                                                                echo ' <input type="hidden" name="categ" value="si" id="categ"/>';
                                                            }
                                                            else{
                                                                //echo "NO HAY CATEGORIAS";
                                                                if ($distrito == "Todos"){
                                                                    $num = $alertas->obtenerAlertasTodos();
                                                                }
                                                                else{
                                                                    $num = $alertas->obtenerAlertasDistrito($distrito);
                                                                }
                                                                if($num == false){
                                                                    echo "<p><p><h4>Este distrito no dispone de alertas con esos filtros todavía</h4></p></p>";
                                                                }
                                                            }
                                                        }
                                    
                                             echo '        </div>
                                                         </div>
                                                     </div>
                                                 </div>
                                             </div>

                                      </div> 
                              </div>';
                          }
                       echo ' </form>';
                        }
                    }
                    if(isset($_POST['atributo'])){
?>
                        <!--Recupero los campos de index.php -->

                        <?php
                        echo '<div class="row">
                                 <div class="col-12">
                                    <div class="card">
                                        <div class="card-block">
                                            <div class="form-group">
                                            <label class="col-sm-12">Selecciona un distrito</label>
                                                <div class="col-sm-12">';
                                                    $alertas->mostrarDistritos();
                                        
                                    echo '      </div>                                      
                                            </div>
                                    </div>
                                    
                                            <div class="form-group" style="margin: auto; margin-bottom: 20px;">
                                                 <div class="items col-sm-12">
                                                     <button class="btn btn-danger" id="alertas">Mostrar</button>
                                                 </div>    
                                             </div>

                                        </div>
                                </div>
                            </div> ';?>

                        <div class="row">
                            <div class="col-12">
                             <div class="card">
                              <div class="tab-content">
                                    <div class="tab-pane active" id="home" role="tabpanel">
                                    <div class="card-block">                 
                                    <!-- Nav tabs -->

                                    
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
                                        ?>
                                         <form method="post" action="alertas.php">
                                        <?php 
                                        $var = "si";
                                          echo "<button class='btn btn-success' type='submit'> Actualizar alertas<i class='mdi mdi-refresh'></i></button> ";


                                        $distritoSilvia4 =  $distrito;?>
                                        <ul class="nav nav-tabs profile-tab" role="tablist">
                                    
                                    <?php
                                        echo ' <input type="hidden" name="actualizaP" value="'.$var.'" id="actualizaP"/>';
                                        echo ' <input type="hidden" name="distritosilvia4" value="'.$distritoSilvia4.'" id="distritosilvia4"/>';
                                        //Pasar array x hidden
                                        foreach($categorias as $value){
                                            echo ' <input type="hidden" name="categorias[]" value="'.$value.'" id="categorias"/>';
                                        }
                                          ?>

                                        </form>
                                        <?php
                                        echo "<li class='nav-item'> <a class='nav-link active' data-toggle='tab' role='tab'><b>Distrito: $distrito</b></a> </li>"; ?>
                                        </ul>
                                        <div class="tab-content">
                                            <div class="tab-pane active" id="home" role="tabpanel">
                                                <div class="card-block">

                                                <form method="POST" action="alertas.php">
                                                  <?php 
                                                   echo '<input type="hidden" name="distritoSilvia4" value="'.$distrito.'"/>';
                                                  ?>

                                                </form>
                                        
                                    
                                                 <?php
                                                //Compruebo de nuevo si NO es INDEX LLAMO A EsTA FUNCION
                                                if(!isset($distritoMapa)) {
                                                    if ($distrito == "Todos"){
                                                        $num = $alertas->obtenerAlertasTodosCat($categorias);
                                                     }
                                                     else{
                                                    $num = $alertas->obtenerAlertas($distrito, $categorias);
                                                    }
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
        </div>
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
