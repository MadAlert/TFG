//dependencies
var restful = require('node-restful');
var mongoose = restful.mongoose;

var seguridadSchema = new mongoose.Schema({
	distrito: String,
	personas: Number,
	patrimonio: Number,
	armas: Number,
	ten_drogas: Number,
	con_drogas: Number,
	mes: String
});

//return models
module.exports = restful.model('estseguridad', seguridadSchema);