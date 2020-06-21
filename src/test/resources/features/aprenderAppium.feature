# language: pt
@testAppium

Funcionalidade: Validar Fluxos e Elementos CT_Appium test

@validaFormulario
Cenario: Consulta APK Aquino

Dado que eu estou no device
Quando eu abro o app CT_Appium
E entro no formulário
Então preencho os dados 

@loginApps
Esquema do Cenario: Troca apps

Dado que eu estou no app "<app>"
Quando eu abro o app correspondente

Exemplos:

|app       |
|hipercard |
|itaucard  |
|credicard |
|luizacard |
|calc      |


