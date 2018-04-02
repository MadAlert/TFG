//dependencies
var express = require('express');
var router = express.Router();

//get models
var Status = require('./alertas');
var Estadisticas = require('./estadisticas');
var Accidentes = require('./estaccidentes');
var Detenidos = require('./estdetenidos');
var Seguridad = require('./estseguridad');


//routes
Status.methods(['get', 'post', 'put', 'delete']);
Status.register(router, '/alertas');
Estadisticas.methods(['get', 'post', 'put', 'delete']);
Estadisticas.register(router, '/estadisticas');
Accidentes.methods(['get', 'post', 'put', 'delete']);
Accidentes.register(router, '/estaccidentes');
Detenidos.methods(['get', 'post', 'put', 'delete']);
Detenidos.register(router, '/estdetenidos');
Seguridad.methods(['get', 'post', 'put', 'delete']);
Seguridad.register(router, '/estseguridad');

//return router
module.exports = router;