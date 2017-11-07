/**
 * configuracion credenciales.
 */

var config = {
	clients: [],
	confidentialClients: [{
		clientId: 'BildCliente',
		clientSecret: 'secreto'
	}],
	tokens: [],
	users: []
};

/*
 * metodos usados por todos los tipos de autenticacion
 * 
 */

var getAccessToken = function(bearerToken, callback) {

	var tokens = config.tokens.filter(function(token) {

		return token.accessToken === bearerToken;
	});

	return callback(false, tokens[0]);
};

var getClient = function(clientId, clientSecret, callback) {

	var clients = config.clients.filter(function(client) {

		return client.clientId === clientId && client.clientSecret === clientSecret;
	});

	var confidentialClients = config.confidentialClients.filter(function(client) {

		return client.clientId === clientId && client.clientSecret === clientSecret;
	});

	callback(false, clients[0] || confidentialClients[0]);
};

var grantTypeAllowed = function(clientId, grantType, callback) {

	var clientsSource,
		clients = [];

	if (grantType === 'password') {
		clientsSource = config.clients;
	} else if (grantType === 'client_credentials') {
		clientsSource = config.confidentialClients;
	}

	if (!!clientsSource) {
		clients = clientsSource.filter(function(client) {

			return client.clientId === clientId;
		});
	}

	callback(false, clients.length);
};

var saveAccessToken = function(accessToken, clientId, expires, user, callback) {

	config.tokens.push({
		accessToken: accessToken,
		expires: expires,
		clientId: clientId,
		user: user
	});

	callback(false);
};

/*
 * metodo usado solo por autenticacion por credencial de clientes (la utilizada para este ejemplo)
 */

var getUserFromClient = function(clientId, clientSecret, callback) {

	var clients = config.confidentialClients.filter(function(client) {

		return client.clientId === clientId && client.clientSecret === clientSecret;
	});

	var user;
	if (clients.length) {
		user = {
			username: clientId
		};
	}

	callback(false, user);
};

/**
 *  exportacion del modelo.
 */

module.exports = {
	getAccessToken: getAccessToken,
	getClient: getClient,
	grantTypeAllowed: grantTypeAllowed,
	saveAccessToken: saveAccessToken,
	getUserFromClient: getUserFromClient
};