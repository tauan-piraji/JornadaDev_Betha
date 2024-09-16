try:
    a = int(input('Numero: '))
    b = int(input('Divisor: '))
    r = a / b
except ZeroDivisionError:
    print('Não existe divizão por zero')
except Exception as e:
    print(f'Erro: {e.__class__}')
else:
    print(f'Resultado da divisão é {r}')
finally:
    print('Volte sempre')