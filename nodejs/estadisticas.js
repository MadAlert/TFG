//dependencies
var restful = require('node-restful');
var mongoose = restful.mongoose;

var estadisticasSchema = new mongoose.Schema({
	distrito: String,
	categoria: String,
	fuente: String,
	mes: String
});

//return models
module.exports = restful.model('estadisticas', estadisticasSchema);