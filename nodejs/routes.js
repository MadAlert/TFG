'use strict';

const jwt = require('jsonwebtoken');

const alertasDistrito = require('./functions/verAlertasDistrito');
const alertasDistritoCategoria = require('./functions/verAlertasDistritoCategoria.js');
const addAlerta = require('./functions/addAlertaBD.js');
/*const login = require('./functions/login');
const profile = require('./functions/profile');
const password = require('./functions/password');
const config = require('./config/config.json');*/

module.exports = router => {

	router.get('/', (req, res) => res.end('Bienvenido a MadAlert !'));

	//Para buscar alertas por distrito
	router.get('/alertas/:distrito', (req,res) => {

			alertasDistrito.getAlertasDistrito(req.params.distrito)

			.then(result => res.json(result))

			.catch(err => res.status(err.status).json({ message: err.message }));
	});
	
	//Para buscar alertas por distrito y categoria
	router.get('/alertas/:distrito/:arr', (req,res) => {
			console.debug(req.params.arr.split(','))
			alertasDistritoCategoria.getAlertasDistritoCategoria(req.params.distrito.split(','), req.params.arr.split(','))
			
			.then(result => res.json(result))
			.catch(err => res.status(err.status).json({ message: err.message }));
	});
	
	//Para añadir alertas
	router.post('/alertas/:titulo/:distrito/:fuente/:categoria', (req, res) => {

		const alerta = req.params.titulo;
		const distrito = req.params.distrito;
		const fuente = req.params.fuente;
		const categoria = req.params.categoria;

		console.log(req.params.titulo);
		
		console.log(req.params.distrito);
		

	    addAlerta.addAlertaBD(req.params.titulo,req.params.distrito,req.params.fuente,req.params.categoria)

		.then(result => {
				//res.setHeader('Location', '/users/'+email);
				res.status(result.status).json({ message: result.message })
		})

		.catch(err => res.status(err.status).json({ message: err.message }));
		
	});
}

