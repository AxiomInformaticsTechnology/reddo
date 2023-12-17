var pem = require('pem');
var https = require('https');
var express = require('express');
var session = require('express-session');

var app = express();

pem.createCertificate({
    days: 1,
    selfSigned: true
}, function(error, keys) {

  var app = express();

  app.use( session({
    secret: 'super secret key',
    resave: false,
    saveUninitialized: true
  }));

  app.use('/jquery', express.static(__dirname + '/node_modules/jquery/dist/'));
  app.use('/jquery.cookie', express.static(__dirname + '/node_modules/jquery.cookie'));

  app.get('/', function (request, response) {
    response.sendFile('index.html', { 
      root: __dirname
    });
  });

  https.createServer({
    key: keys.serviceKey,
    cert: keys.certificate
  }, app).listen(443, function() {
      console.log('Listening on port 443');
  });

});