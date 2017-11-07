/**
 * dependencias del proyecto.
 */
	
var express = require('express'),
	bodyParser = require('body-parser'),
	oauthserver = require('oauth2-server');


var response = "{status:200, error: '',msg:{forms:[{name:'formulariocomentario',inputs:[{name:'nombre',type:'text',required:true},{name:'Email',type:'email',required:true},{name:'Comentario',type:'text',required:true}]}]}}";
console.log(response);


var app = express();
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

/**
 * configuracion Oauth.
 */
app.oauth = oauthserver({
	model: require('./model.js'), // script donde se abtraen los tipos de autenticacion (user, password - clientid, secret - etc)
	grants: ['client_credentials'], // tipo de autenticacion
	accessTokenLifetime: 30, // tiempo expiracion token
	debug: true // modo debug
});

/**
 *  metodo para obtener token con confidentialClients.
 */
app.all('/oauth/token', app.oauth.grant());


/**
 *  metodo para obtener forms a utilizar en aplicacion móvil
 */
app.get('/getforms', app.oauth.authorise(), function (req, res) {
	res.writeHead(200, { 'Content-Type': 'application/json' }); 
    res.end(response);
});

app.use(app.oauth.errorHandler());

/**
 *  puerto en el cual se esperan las solicitudes al servicio.
 */
app.listen(3000, function() {
  console.log("Node server running on http://localhost:3000");

});