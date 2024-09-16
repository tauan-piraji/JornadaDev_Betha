from datetime import date

dataAtual = date.today().year
festa = 0

for pess in range(1, 8):
    nasc = int(input('Em que ano você nasceu? '))
    if nasc >= (dataAtual-18):
        print('Menor de idade n bebe')
    else:
        print('Vira')
        festa += 1
print('Festa vai ter {} pessoinhas'.format(festa))
R = 'S'
while R == 'S':
    R = str(input('A festavai ta boa[S/N]')).upper()
print('PT')