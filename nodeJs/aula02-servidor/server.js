// var http = require('fs') permite mexer nas pastas do computador
var http = require('http');

http.createServer(function(req, res){
    res.end("ola");
}).listen(8081);

console.log("Servidor rodando!");