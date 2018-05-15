'use strict';

const alerta = require('../alertas');

exports.getCountAlertasDistrito = (distrito,count,categorias) =>

	
	new Promise((resolve, reject) => {
		if(categorias == "Todas"){
			console.debug("entra en este metodo y categorias es TODAS ");
			alerta.find({distrito: {$in:distrito}}).count()

			.then((alertas) => {resolve(alertas);})
				
			.catch(err => reject({status: 500, message: 'Internal Server Error! verAlertasDistrito.js'}))
		}
	});



