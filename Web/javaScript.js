
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
