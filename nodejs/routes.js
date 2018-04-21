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
			alertasDistritoCategoria.getAlertasDistritoCategoria(req.params.distrito, req.params.arr.split(','))
			
			.then(result => res.json(result))
			.catch(err => res.status(err.status).json({ message: err.message }));
	});
	
	//Para añadir alertas
	router.post('/alertas', (req, res) => {

		const alerta = req.body.alerta;
		const distrito = req.body.distrito;
		const fuente = req.body.fuente;
		const categoria = req.body.categoria;
		

		if (!alerta || !distrito || !fuente || !categoria) {

			res.status(400).json({message: 'Invalid Request !'});

		} else {

			addAlerta.addAlertaBD(alerta,distrito,fuente,categoria)

			.then(result => {

				//res.setHeader('Location', '/users/'+email);
				res.status(result.status).json({ message: result.message })
			})

			.catch(err => res.status(err.status).json({ message: err.message }));
		}
	});
}

