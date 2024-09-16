const express = require('express');
const app = express();
const PORT = process.env.PORT|| 8080;
const consulta = require('./consulta')

app.use(consulta);

app.listen(PORT, function(){
    console.log("Servidor iniciado");
})





        /*
        var manga = {
            "nome": root.querySelectorAll(".seriesList")[0]['_attrs']['title'],
            "link": root.querySelectorAll(".link-block")[0]['_rawAttrs']['href'],//resposta == /manga/yakyuu-de-sensou-suru-isekai-de-chou-koukou-kyuu-ace-ga-jakushou-kokka-wo-sukuu-you-desu-/12680
            "cap": root.querySelectorAll(".series-chapters")[0]['rawText'],
            "nota": root.querySelectorAll(".nota")[0]['rawText'],
            "genero01": root.querySelectorAll(".nota")[1]['rawText'],
            "genero02": root.querySelectorAll(".nota")[2]['rawText'],
            "descricao": root.querySelectorAll(".series-desc")[0]['rawText']
        }
        
        var seriesList = root.querySelector(".seriesList");
        var divLi = seriesList.querySelectorAll("li")[0];
        var divTitulo = divLi.querySelector("h1")['rawText'];//Titulo
        var divLink = divLi.querySelector(".link-block")['_attrs']['href'];//link
        var divCap = divLi.querySelector(".series-chapters")['rawText'];//chapter
        var divNota= divLi.querySelector(".nota")['rawText'];//nota

        var divDesc = divLi.querySelector(".series-desc")['rawText'];//Descrição

        com muito amor
        */