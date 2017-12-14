
//Para validar el registro
function valida() {
	var suma =0;
	var ok = true;
	var msg = "Debe seleccionar al menos una categoría\n";
	$("input:checkbox:checked").each(function() {
			suma++;
		});
		
		if(suma == 0){
			
			ok=false;
		}


	if(ok == false){
		alert(msg);
	}
	return ok;
}


// Función usada para mostrar todas las alertas de un distrito seleccionado
function distritoSilvia() {
	
	var ok = true;
	var msg = "Mensaje 1: \n";
	
	alert(msg);

	return ok;
}
