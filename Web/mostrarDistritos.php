<?php 
    include ("claseAlertas.php");
    $alertas = new claseAlertas();

    echo '<div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-block">
                    <h1 class="card-title"> Estadísticas de la Policía Municipal </h1>
                        <div class="form-group">
                            <label class="col-sm-12">Selecciona un distrito</label>
                                <div class="col-sm-12">';
                                        $alertas->mostrarDistritos();
                                        
                          echo'      </div>                                      
                        </form>
                        </div>
                </div>
                <div class="form-group" style="margin: auto; margin-bottom: 20px;">
                    <div class="items col-sm-12">
                       <button class="btn btn-success" id="alertas">Mostrar</button>
                    </div>    
                </div>
            </div>
        </div>
</div>';
?>