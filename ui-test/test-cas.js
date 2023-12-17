var pem = require('pem');
var https = require('https');
var express = require('express');
var session = require('express-session');
var CASAuthentication = require('cas-authentication');

var cas = new CASAuthentication({
    cas_url     : 'https://cas-dev.tamu.edu/cas',
    service_url : 'https://localhost',
    cas_version     : '2.0',
    renew           : false,
    is_dev_mode     : false,
    dev_mode_user   : '',
    dev_mode_info   : {},
    session_name    : 'cas_user',
    session_info    : 'cas_userinfo',
    destroy_session : false
});
 
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

  app.get('/', cas.bounce, function (request, response) {
    response.sendFile('index-cas.html', { 
      root: __dirname
    });
  });

  app.get( '/logout', cas.logout );
 
  https.createServer({
    key: keys.serviceKey,
    cert: keys.certificate
  }, app).listen(443, function() {
      console.log('Listening on port 443');
  });

});
