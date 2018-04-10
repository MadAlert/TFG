//dependencies
var express = require('express');
var router = express.Router();

//get models
var Status = require('./alertas');
var Estadisticas = require('./estadisticas');
var Accidentes = require('./estAccidentes');
var Detenidos = require('./estDetenidos');
var Seguridad = require('./estSeguridad');


//routes
Status.methods(['get', 'post', 'put', 'delete']);
Status.register(router, '/alertas');
Estadisticas.methods(['get', 'post', 'put', 'delete']);
Estadisticas.register(router, '/estadisticas');
Accidentes.methods(['get', 'post', 'put', 'delete']);
Accidentes.register(router, '/estAccidentes');
Detenidos.methods(['get', 'post', 'put', 'delete']);
Detenidos.register(router, '/estDetenidos');
Seguridad.methods(['get', 'post', 'put', 'delete']);
Seguridad.register(router, '/estSeguridad');

//return router
module.exports = router;