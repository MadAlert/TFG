'use strict';

const alerta = require('../alertas');

exports.addAlertaBD = (alerta,distrito,fuente,categoria) => 

	new Promise((resolve,reject) => {
		const newAlerta = new alerta({

			alerta: alerta,
			distrito: distrito,
			fecha: new Date(),
			fuente: fuente,
			categoria: categoria
		});

		newAlerta.save()

		.then(() => resolve({ status: 201, message: 'Alerta añadida correctamente !' }))

		.catch(err => {

			if (err.code == 11000) {
						
				reject({ status: 409, message: 'Ya se habia registrao !' });

			} else {

				reject({ status: 500, message: 'Internal Server Error !' });
			}
		});
	});