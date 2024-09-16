const Sequelize = require('sequelize');
const sequelize = new Sequelize('test', 'root', 'T123456789t.', {
    host: "localhost",
    dialect: 'mysql'
});

const Postagem = sequelize.define('postagens', {
        titulo: {
            type: Sequelize.STRING
        },
        conteudo: {
            type: Sequelize.TEXT
        }
});

Postagem.create({
    titulo: "Titulito",
    conteudo: "conteudo de milhões"
})

//Postagem.sync({force: true})

// sequelize.authenticate().then(function() {
//     console.log("Conectado com DB!");
// }).catch(function(erro) {
//     console.log("Falha ao se conectar com DB:" + erro);
// });