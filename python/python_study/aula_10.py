temp = []
princ = []
maior = 0

while True:
    temp.append(str(input('Seu nome: ')))
    temp.append(float(input('Sua idade: ')))
    princ.append(temp[:])
    if len(princ) == 0:
        maior = temp[1]
        name = temp[0]
    else:
        if temp[1] > maior:
            maior = temp[1]
            name = temp[0]
    temp.clear()
    STOP = str(input('Quer parar[S/N]: ')).upper()
    if STOP == 'S':
        break
print('-=-' * 20)
print(f'Voce intrevistou {len(princ)}')
print(f'A mais velha era {name} com {maior}')