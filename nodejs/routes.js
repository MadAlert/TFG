'use strict';

const jwt = require('jsonwebtoken');

const alertasDistrito = require('./functions/verAlertasDistrito');
/*const login = require('./functions/login');
const profile = require('./functions/profile');
const password = require('./functions/password');
const config = require('./config/config.json');*/

module.exports = router => {

	router.get('/', (req, res) => res.end('Bienvenido a MadAlert !'));

	router.get('/alertas/:distrito', (req,res) => {

			alertasDistrito.getAlertasDistrito(req.params.distrito)

			.then(result => res.json(result))

			.catch(err => res.status(err.status).json({ message: err.message }));
	});
}