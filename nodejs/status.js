//dependencies
var restful = require('node-restful');
var mongoose = restful.mongoose;

var statusSchema = new mongoose.Schema({
	alertas: String,
	fecha: Date,
	url: String,
	distrito: String,
	categoria: String,
	fuente: String
});

//return models
module.exports = restful.model('alertas', statusSchema);