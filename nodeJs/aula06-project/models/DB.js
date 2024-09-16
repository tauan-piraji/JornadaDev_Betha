const Sequelize = require('sequelize');

const sequelize = new Sequelize('test', 'root', 'T123456789t.', {
    host: "localhost",
    dialect: 'mysql'
});

module.exports = {
    Sequelize: Sequelize,
    sequelize: sequelize
}