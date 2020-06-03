# language: pt
@testesUOL

Funcionalidade: Brincando UOL

@TestUol1
Cenario: Consulta Uol Líderes

Dado que eu estou na tela do uol
Quando eu acesso o menu Economia
Entao eu acesso o link Líderes

@TestUolGrid
Esquema do Cenario: Consulta Uol Líderes

Dado que eu estou no "<site>"
Quando eu pego a url e imprimo no cucumber report

Exemplos:

|site                                          |
|http://www.gmail.com                          |
|http://www.ig.com.br                          |
|http://www.bol.com.br                         |
|http://www.submarino.com.br                   |