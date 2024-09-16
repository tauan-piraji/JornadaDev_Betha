const express = require("express");
const app = express();
const handlebars = require('express-handlebars')
const bodyParser = require('body-parser');
const Post = require('./models/Post');

//config
    //Template engine
        app.engine('handlebars', handlebars.engine({defaultLayout: 'main'}))
        app.set('view engine', 'handlebars')

    //Bodyparser
        app.use(bodyParser.urlencoded({extended: false}))
        app.use(bodyParser.json())


//rotas

//Mostra todos os registros
app.get("/", function(req, res){
    Post.all({order: [['id', 'ASC']]}).then(function(posts) {
        res.render('home', {posts: posts})
    })
});

app.get("/cad", function(req, res){
    res.render('formulario')
});

app.Post("/postForm", function(req, res){
    Post.create({
        titulo: req.body.titulo,
        conteudo: req.body.conteudo
    }).then(function(){
        res.render('sucesso')
        res.redirect('/')
    }).catch(function(err) {
        res.send(err)
    })
});

app.delete("/delete/:id", function(req,res) {
    Post.destroy({where: {'id': req.params.id}}).then(function(){
        res.render('sucesso')
        res.redirect('/')
    }).catch(function(err) {
        res.send(err)
    })
});

app.listen(8081, function(req, res) {
    console.log("Server started at URL: http://localhost:8081");
});