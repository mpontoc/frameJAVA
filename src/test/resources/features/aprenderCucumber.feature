# language: pt
@consultaGoogle

Funcionalidade: Consultas no Google

@Test
Cenario: Consulta Java Google

Dado que eu estou na tela do google
Quando eu busco por java e muito bom
Entao eu encontro diversos resultados

@Test11
Cenario: Consulta Java Google

Dado que eu estou na tela do uol
Quando eu busco por java e muito bom
Entao eu encontro diversos resultados


@Test1
Cenario: Consulta Java Google

Dado que eu estou na tela do google
Quando eu busco por java e muito bom
Entao eu encontro diversos resultados
E rodo pelo mvn

@Test2

Esquema do Cenario: Consulta Java Google grid

Dado que eu estou na tela do "<site>"
Quando eu busco por java e muito bom
Entao eu encontro diversos resultados
E rodo pelo mvn

Exemplos:

|site                                          |
|http://www.gmail.com                          |
|http://www.ig.com.br                          |
|http://www.bol.com.br                         |
|http://www.submarino.com.br                   |

