
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

//$("#botonAlert").modal('show')

$("#botonAlert").click(function(){
			  $("#correcto").hide();
			  $("#incorrecto").hide();

			  nombre=$("#nombre").val();
			  email=$("#email").val();
			  categoria=$("#categorias").val();
			  distrito=$("#distritos").val();
			  alerta=$("#alerta").val();

			  $.ajax({
			   type: "POST",
			   url: "procesarAniadir.php",
			   data: "nombre="+nombre+"&email="+email+"&categorias="+categoria+"&distritos="+distrito+"&alerta="+alerta,
			   success: function(html){   
				if($.trim(html) == '1'){
					$("#correcto").slideDown();
					$("#correcto").delay(2000);
					$("#correcto").slideUp();
					$("#alerta").val('');
					$("#nombre").val('');
					$("#email").val('');
				} else {
					$("#incorrecto").slideDown();
					$("#incorrecto").delay(2000);
					$("#incorrecto").slideUp();
					$("#alerta").val('');
					$("#nombre").val('');
					$("#email").val('');
				}
				//Falta que si es 0 -----> mostrar mensaje de error y no se añade
			   },
			   error: function() {
    				console.error('Ha fallado el proceso Ajax!');
  			   }
			  });
			  
		return false;
	});