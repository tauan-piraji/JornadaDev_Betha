const express = require('express');
const axios = require('axios').default;
const parser = require('node-html-parser').parse;
const router = express.Router();

router.get('/consulta', function(req, res){
    var mangas = [];

    var url = "https://mangalivre.net/lista-de-mangas/ordenar-por-atualizacoes";
    var requisicao = axios.get(url);
    requisicao.then(function(resposta){
        var root = parser(resposta.data);
        var seriesList = root.querySelector(".seriesList");
        var divLi = seriesList.querySelectorAll("li");

        mangas = divLi.map(function(manga){
            var link = "https://mangalivre.net";
            if(manga == null || manga.querySelector("h1") == null){
                return{}
            }
            var generos = [];
            var divGeneros= manga.querySelectorAll(".touch-carousel-item");
            if(divGeneros == null){
                return{}
            }
            generos = divGeneros.map(function(genero){
                if(genero == null){
                    return{};
                }
                return genero.querySelector("span").querySelector("span")['rawText'];
            })

            return{
                "nome": manga.querySelector("h1")['rawText'],
                "link": link + manga.querySelector(".link-block")['_attrs']['href'],
                "capitulo": manga.querySelector(".series-chapters")['rawText'].trim(),
                "nota": manga.querySelector(".nota")['rawText'],
                "genero": generos.slice(1,generos.length),
                "descri": manga.querySelector(".series-desc")['rawText'].trim().replace('/r/','').replace('/n', '')
            }
        })
        res.json(mangas);
    })
})
module.exports = router