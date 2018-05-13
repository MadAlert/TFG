'use strict';

const alerta = require('../alertas');

exports.getAlertasDistrito = distrito =>

	new Promise((resolve, reject) => {
		
		alerta.find({distrito: {$in:distrito}).sort({fecha:-1})

		.then(alertas => resolve(alertas))
			
		.catch(err => reject({status: 500, message: 'Internal Server Error! verAlertasDistrito.js'}))
			

	});
