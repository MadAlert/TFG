//dependencies
var express = require('express');
var mongoose = require('mongoose');
var bodyParser = require('body-parser');

//connect to mongoDB
mongoose.connect('mongodb://MadAlert:tfg20172018@ds235388.mlab.com:35388/noticias'); 

//express
var app = express();
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

//routes
app.use('/api', require('./api'));

//start server
app.listen(1000);
console.log('Servidor corriendo en el puerto 1000');
