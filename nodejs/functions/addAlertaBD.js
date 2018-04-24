'use strict';

const alertas = require('../alertas');

exports.addAlertaBD = (alerta, distrito , fuente , categoria) => 
	
	new Promise((resolve,reject) => {
		const newAlerta = new alertas({
			alerta: alerta,
			fecha: new Date(),
			url: null,
			distrito: distrito,
			categoria: categoria,
			fuente: fuente,
			verificado: false
		});
		
		newAlerta.save()
		
		.then(() => resolve ({status: 201, message: "Se ha añadio ok"}))
		.catch(err => {
			reject({status: 500, message: "mierda error"});
		});
	});