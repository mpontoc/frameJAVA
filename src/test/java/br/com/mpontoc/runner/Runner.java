package br.com.mpontoc.runner;

import br.com.mpontoc.commons.BaseTest;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
		
//		tags = { " @loginGmail" },
//		tags = { " @apagarEmails " },
//		tags = { " @TestUol1 " },
//		tags = { " @consultaGoogle , @testesUOL " },
		tags = { "  @testesUOL " },
//		tags = { "@testAppium" },
//		tags = { "@loginApps" },
		features = "src/test/resources/features", // local onde estão as features
		glue = { 
				"br.com.mpontoc.steps", // package onde estão os steps
				"br.com.mpontoc.conf" // chamada do spring
		}, 
		plugin = { "pretty", // imprime a descrição da feature
				// "json:target/reports/results.json",
				"html:target/cucumber-reports",
				}, 
		monochrome = true, // deixa o console só com fonte cor preta
		dryRun = false, // ao rodar quando true percorre toda automação verificando se faltam passos
		strict = true // quando está true ele falha o cenario inteiro caso estja faltando algum step
)

public class Runner extends BaseTest {


}
