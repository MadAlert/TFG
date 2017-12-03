        <!-- Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->
		<?php
		echo '<html>
        <aside class="left-sidebar">
            <!-- Sidebar scroll-->
            <div class="scroll-sidebar">
                <!-- Sidebar navigation-->
                <nav class="sidebar-nav">
                    <ul id="sidebarnav">
                        <li> <a class="waves-effect waves-dark" href="alertas.php" aria-expanded="false"><i class="mdi mdi-bell"></i><span class="hide-menu">Alertas</span></a>
                        </li>
                        <li> <a class="waves-effect waves-dark" aria-expanded="false"><i class="mdi mdi-format-list-numbers"></i><span class="hide-menu">Estadísticas</span></a>
							<ul>
								<li> <a class="waves-effect waves-dark" href="estadisticasGenerales.php" aria-expanded="false"><i class="mdi mdi-view-agenda"></i><span class="hide-menu"> Generales</span></a>
								</li>
								<li> <a class="waves-effect waves-dark" href="estadisticasDistritos.php" aria-expanded="false"><i class="mdi mdi-grid"></i><span class="hide-menu"> Distritos</span></a>
								</li>
							</ul>
						</li>
                        <li> <a class="waves-effect waves-dark" aria-expanded="false"><i class="mdi mdi-information"></i><span class="hide-menu">Información</span></a>
							<ul>
								<li> <a class="waves-effect waves-dark" href="soporte.php" aria-expanded="false"><i class="mdi mdi-help-circle"></i><span class="hide-menu"> Soporte</span></a>
								</li>
								<li> <a class="waves-effect waves-dark" href="aboutus.php" aria-expanded="false"><i class="mdi mdi-contacts"></i><span class="hide-menu"> About us</span></a>
								</li>
							</ul>						
						</li>
                    </ul>
                </nav>
                <!-- End Sidebar navigation -->
            </div>
            <!-- End Sidebar scroll-->
        </aside>
		</html>';
?>