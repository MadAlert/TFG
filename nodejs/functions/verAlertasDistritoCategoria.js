'use strict';

const alerta = require('../alertas');

exports.getAlertasDistritoCategoria = (distrito, categorias) =>

    new Promise((resolve, reject) => {
		alerta.find({distrito: distrito, categoria:{$in:categorias}}).sort({fecha:-1})
        .then((alertas) => {resolve(alertas);})
            
        .catch(err => reject({status: 500, message: 'Internal Server Error! verAlertasDistritoCategoria.js'}))
            

    });