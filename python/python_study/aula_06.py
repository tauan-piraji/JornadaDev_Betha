import math
num = float(input('digite um valor: '))
print('você digitou {} e a sua porçao inteira é {}'.format(num, math.trunc(num)))
co = float(input('Medida do cateto oposto: '))
ca = float(input('Medida do cateto adjacente: '))
print('A hipotenuza é {}'.format(math.hypot(co, ca)))