function fazersuco(ingrediente1, ingrediente2, leite){
    console.log ('seu suco será de ' + ingrediente1 + ', com' + ingrediente2 + (leite == true ? 'cremoso' : ''));
}
var fazersuco2 = (ingrediente1, ingrediente2, leite) => {
    console.log(`seu suco será de ${ ingrediente1 }, com ${ ingrediente2 } ${(leite == true ? 'cremoso' : '')}`);
}
// fazersuco('laranja', 'morango', false);
fazersuco2('laranja', 'morango', false);