package br.com.mpontoc.steps;

import static br.com.mpontoc.commons.PageHub.driver;

import org.junit.Assert;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class Consulta_Steps {

	@Before
	public void setUp(Scenario scenario) {
		
		
	}
	
	@Dado("^que eu estou na tela do google$")
	public void que_eu_estou_na_tela_do_google() throws Throwable {
		System.out.println("test");

		driver.get("http://www.uol.com.br");

		Thread.sleep(2000);

		System.out.println(driver.getCurrentUrl().toString());
	}

	@Dado("^que eu estou na tela do \"([^\"]*)\"$")
	public void que_eu_estou_na_tela_do(String site) throws Throwable {

		Thread.sleep(2000);

		driver.navigate().to(site);

		Thread.sleep(2000);

		System.out.println(driver.getCurrentUrl().toString());
	}

	@Quando("^eu busco por java e muito bom$")
	public void eu_busco_por_java_e_muito_bom() throws Throwable {
		System.out.println("test1");
	}

	@Entao("^eu encontro diversos resultados$")
	public void eu_encontro_diversos_resultados() throws Throwable {
		System.out.println("test3");
	}

	@Entao("^rodo pelo mvn$")
	public void rodo_pelo_mvn() throws Throwable {
		String a = "teste automatizado";
		Assert.assertTrue(a.contains("teste"));
		Assert.assertEquals(a, "teste automatizado");
		System.out.println(a);
	}

}
