'use strict';

const alerta = require('../alertas');

exports.getAlertasDistritoCategoria = (distrito, categorias) =>

    new Promise((resolve, reject) => {

		console.debug(categorias)
        alerta.find({distrito: distrito, categoria:{$in:categorias}}).sort({fecha:-1})

        .then(alertas => categorias)
            
        .catch(err => reject({status: 500, message: 'Internal Server Error! verAlertasDistritoCategoria.js'}))
            

    });