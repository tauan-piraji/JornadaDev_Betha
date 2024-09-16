let numbers = [1,2,3,4]
let pessoas = [
    {nome: 'pessoa1'},
    {nome: 'pessoa2'},
    {nome: 'pessoa3'}
]

pessoas.push({nome: 'liciely'})

console.log(numbers)
console.log(numbers[0])
console.log(pessoas)
console.log(pessoas[3])
pessoas.forEach(el =>{
    console.log(`alá aluno ${el.nome}`)
})