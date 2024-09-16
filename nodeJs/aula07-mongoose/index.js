const mongoose =  require("mongoose");


//configurando mongoose
    mongo.Promise = global.Promise;
    mongoose.connect("mongoose://localhost/test", {
        useMongoClient: true
    }).then(() => {
        console.log("Conectado no mongoDB")
    }).catch((err) => {
        console.log("Houve um erro no mongoDB: " + err)
    })
//Model

const UsuarioSchema = mongoose.Schema({

    name: {
        type: String,
        require: true
    },
    email: {
        type: String,
        require: true
    },
    senha: {
        type: String,
        require: true
    },
    idade: {
        type: Number
    }

})

//define nome da tabela que vai o schema
mongoose.model('usuarios', UsuarioSchema);

new UsuarioSchema({
    name: "Tauan",
    email: "t@hotm...",
    senha: "1234Abraba"
}).save().then(() => {
    console.log("Salvo no DB")
}).catch((err) => {
    console.log("Houve um erro ao salvar: " + err)
})