//dependencies
'use strict';

const mongoose = require('mongoose');

const Schema = mongoose.Schema;

var alertasSchema = new mongoose.Schema({
	alerta: String,
	fecha: Date,
	url: String,
	distrito: String,
	categoria: String,
	fuente: String
});

mongoose.Promise = global.Promise;
mongoose.connect('mongodb://MadAlert:tfg20172018@ds235388.mlab.com:35388/noticias');

//return models
module.exports = mongoose.model('alertas', alertasSchema);
