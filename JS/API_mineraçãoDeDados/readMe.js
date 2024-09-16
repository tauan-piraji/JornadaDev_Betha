/*
Primeiro foi criado a pasta index.jsn em seguida foi instalado via terminal as dependências, framework e bibliotecas 
necessárias (express, json, axios, parser, router), em seguida no arquivo package.json é alterado a estrutura "scripts" renomeando para 
"start": "node index.js", o que faz a aplicação rodar assim que subir ela para o Heroku.
 
A seguir uma breve explicação do código:
*/

//Aqui são passados os frameworks e dependências para constantes para serem utilizadas 
const express = require('express');  
const axios = require('axios').default;
const parser = require('node-html-parser').parse;
const app = express();

const PORT = process.env.PORT|| 8080;
//define a porta usada pelo node.


app.get('/', function(req, res){
    //Passa o array onde serão armazenados os dados retornados na requisição http
    var mangas = [];

    //URL passada em uma variável do sit onde foram minerados os dados
    var url = "https://mangalivre.net/lista-de-mangas/ordenar-por-atualizacoes";
    //Variável com a requisição 
    var requisicao = axios.get(url);
    requisicao.then(function(resposta){
        //var root que transforma a body da resposta em HTMLElement
        var root = parser(resposta.data);
        //var seriesList que faz a busca da classe pai contendo todas as informações necessárias
        var seriesList = root.querySelector(".seriesList");
        //var divLi que tras o conjunto de listas de mangas
        var divLi = seriesList.querySelectorAll("li");

        //realiza um map em divLi, mapeando todas as posições desse array
        mangas = divLi.map(function(manga){
            // var com uma string para completar os links
            var link = "https://mangalivre.net";
            //verifica se a divLi dentro de manga n esta retornando "li" null(sem nenhum obj), caso seja null ela retorna um array vazio
            if(manga == null || manga.querySelector("h1") == null){
                return{}
            }
            //criando array vazio generos
            var generos = [];
            //divGenero possui o endereço dos generos
            var divGeneros= manga.querySelectorAll(".touch-carousel-item");
            //verifica se o mesmo não esta vazio 
            if(divGeneros == null){
                return{}
            }
            //realiza o mapping de generos
            generos = divGeneros.map(function(genero){
                if(genero == null){
                    return{};
                }
                //retorna os generos para dentro do array vazio generos
                return genero.querySelector("span").querySelector("span")['rawText'];
            })

            //retorna as informações para de cada constante para o array mangas
            return{
                "nome": manga.querySelector("h1")['rawText'],
                //une a var link com o resto da URL retornada do HTMLElement
                "link": link + manga.querySelector(".link-block")['_attrs']['href'],
                "capitulo": manga.querySelector(".series-chapters")['rawText'].trim(),
                "nota": manga.querySelector(".nota")['rawText'],
                //cria um array com o mapping passando uma estrutura de repetição que passa do seu vetor 1 
                //até a ultima posição verificado pelo generos.length
                "genero": generos.slice(1,generos.length),
                "descri": manga.querySelector(".series-desc")['rawText'].trim().replace('/r/','').replace('/n', '')
            }
        })
        
        //resposta json com o array mangas preenchidos
        res.json(mangas);
    })
})

//responde no console/terminal se o servidor foi iniciado
app.listen(PORT, function(){
    console.log("Servidor iniciado");
})
/*
Após o termino do código ele foi dividido em duas partes criando o arquivo "consulta.js" onde foi passando uma rota  com
"router" para melhorar a organização, enquanto no  arquivo index.js foi chamada a biblioteca express, onde será possível buscar as rotas através das requisições.
*/