package br.com.mpontoc.runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import br.com.mpontoc.commons.Functions;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(
		tags = {"@consultaGoogle"},
		features = "src/test/resources/features", // local onde estão as features
		glue = {
				"br.com.mpontoc.steps",
				"br.com.mpontoc.conf"
		},// package onde estão os steps
		plugin = "pretty", // imprime a descrição da feature
		monochrome = true, // deixa o console s� com fonte cor preta
		dryRun = false, // ao rodar quando true percorre toda automação verificando se faltam passos
		strict = true // quando está true ele falha o cenario inteiro caso estja faltando algum step
		)


public class Runner {
	
	
	@AfterClass
	
	public static void tearDown() {
		
		Functions.finalizaExecucao();
		
	}
	


}
