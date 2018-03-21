//dependencies
var restful = require('node-restful');
var mongoose = restful.mongoose;

var detenidosSchema = new mongoose.Schema({
	distrito: String,
	detenidos: Number,
	mes: String
});

//return models
module.exports = restful.model('estDetenidos', detenidosSchema);