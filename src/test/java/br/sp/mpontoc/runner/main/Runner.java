package br.sp.mpontoc.runner.main;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import br.sp.mpontoc.commons.Functions;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)

@CucumberOptions(
		features = "src/test/resources/features", // local onde estão as features
		glue = "br.sp.mpontoc.steps", // package onde estão os steps
		tags = {"@consultaGoogle"},
		plugin = "pretty", // imprime a descrição da feature
		monochrome = true, // deixa o console s� com fonte cor preta
		snippets = SnippetType.UNDERSCORE, // underscore padrão snake | camelCase padrão java
		dryRun = false, // ao rodar quando true percorre toda automação verificando se faltam passos
		strict = true // quando está true ele falha o cenario inteiro caso estja faltando algum step
		)


public class Runner {
	
	
	@AfterClass
	
	public static void tearDown() {
		
		
		Functions.finalizaExecucao();
	}
	


}
