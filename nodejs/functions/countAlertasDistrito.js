'use strict';

const alerta = require('../alertas');

exports.getCountAlertasDistrito = (distrito,count,categorias) =>

	
	new Promise((resolve, reject) => {
		if(categorias == "Todas"){
			console.debug("entra en este metodo y categorias es TODAS ");
			alerta.aggregate([{$match: {distrito: {$in:distrito}}},{$group:{_id:"$distrito",total:{$sum:1}}},{$project:{distrito:1,total:1}}])

			.then((alertas) => {resolve(alertas);})
				
			.catch(err => reject({status: 500, message: 'Internal Server Error! countAlertasDistrito.js'}))
		}
		else{
			alerta.aggregate([{$match: {distrito: {$in:distrito},categoria:{$in:categorias}}},{$group:{_id:"$distrito",total:{$sum:1}}},
			{$project:{distrito:1,total:1}}])
			.then((alertas) => {resolve(alertas);})

			.catch(err => reject({status: 500, message: 'Internal Server Error! countAlertasDistrito.js'}))
		}
	});



