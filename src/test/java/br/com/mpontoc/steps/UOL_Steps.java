package br.com.mpontoc.steps;

import br.com.mpontoc.commons.Selenium;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

import static br.com.mpontoc.commons.BaseTest.driver;
import static br.com.mpontoc.commons.Selenium.*;

public class UOL_Steps {

	@BeforeStep("@testesUOL")
	public void reportClear(Scenario scenario) {
		Selenium.isFirstRun = true;
	}

	@AfterStep("@testesUOL")
	public void report(Scenario scenario) {
		Selenium.printScreenAfterStep(scenario);
		Selenium.writeReportAfterStep(scenario);
	}

	@Dado("que eu estou na tela do uol")
	public void que_eu_estou_na_tela_do_uol() {

		driver.get("http://www.uol.com.br");
		cucumberReport(driver.getCurrentUrl());

	}

	@Quando("eu acesso o menu Economia")
	public void eu_acesso_o_menu_Economia() {

		Selenium.waitExistClickAndPerform("//span[contains(.,'Economia')]", "Líderes", 3, true);

	}

	@Entao("eu acesso o link Líderes")
	public void eu_acesso_o_link_Líderes() {

		String textoObtido = Selenium.waitExistGetText("//h4[contains(.,'Economia') or (contains(.,'Líderes'))]", 4);
		System.out.println("Texto Obtido nos steps " + textoObtido);

		driver.get("http://www.institucional.jucesp.sp.gov.br/cidadao.php");
		waitSecunds(2);
		waitExistSelectComboBox("//select[@id='selectbox_goto']", "- Cultura", 3);
		waitExistClick("inputSubmit", 3);
		waitSecunds(2);
		waitExistSetNewWindow("s", "teste", 1, 3);
//        waitExistClickNewWindow("Formação", 1, 3);
//        waitExistClick("Formação", 3);
		waitSecunds(2);
		scrollDown(2);
		waitExistGetText("Ouvidoria", 3);

		cucumberReport(driver.getCurrentUrl());
	}

	@Dado("que eu estou no {string}")
	public void que_eu_estou_no(String site) {

		driver.get(site);

	}

	@Quando("eu pego a url e imprimo no cucumber report")
	public void eu_pego_a_url_e_imprimo_no_cucumber_report() {
		cucumberReport("Esta é a url desta imagem " + driver.getCurrentUrl());
	}

}
