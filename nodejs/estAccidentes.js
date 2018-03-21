//dependencies
var restful = require('node-restful');
var mongoose = restful.mongoose;

var accidentesSchema = new mongoose.Schema({
	distrito: String,
	conHeridos: Number,
	sinHeridos: Number,
	mes: String
});

//return models
module.exports = restful.model('estAccidentes', accidentesSchema);