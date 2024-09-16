const express = require("express");
const app = express();

app.get("/", function(req, res){
    res.send("be welcome")
});

app.get("/sobre", function(req, res){
    res.send("my test")
});

app.get("/ola/:nome/:cargo", function(req, res){
    res.send("<h1>Olá " + req.params.nome + "</h1>");
});

app.listen(8081, function(req, res) {
    console.log("Server started at URL: http://localhost:8081");
});