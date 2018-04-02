'use strict'

const alerta = require('../alertas');

exports.getAlertasDistrito = distrito =>

	new Promise((resolve, reject) => {
		
		alerta.find({distrito: distrito})

		.then(alertas => {
			if(alertas.length == 0) {
				reject({status: 404, message: 'No hay alertas disponibles.'})
			} else {
				return alertas;
			}
		})

	});
