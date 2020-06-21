package br.com.mpontoc.steps;

import br.com.mpontoc.commons.Functions;
import br.com.mpontoc.commons.Selenium;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;

import static br.com.mpontoc.commons.BaseTest.driver;
import static br.com.mpontoc.commons.Selenium.waitExistClick;
import static br.com.mpontoc.commons.Functions.waitSecunds;

public class Consulta_Steps {

    @BeforeStep("@consultaGoogle")
    public void reportClear(Scenario scenario) {
        Selenium.isFirstRun = true;
    }

    @AfterStep("@consultaGoogle")
    public void report(Scenario scenario) {
        Selenium.printScreenAfterStep(scenario);
        Selenium.writeReportAfterStep(scenario);
    }

    @Dado("que eu estou na tela do google")
    public void que_eu_estou_na_tela_do_google() {

        if (Functions.verificaFeriado() == true)
            System.out.println("Hoje é feriado");
        else
            System.out.println("Hoje não é feriado");

        System.out.println("test");

        driver.get("http://www.uol.com.br");

        waitSecunds(2);

        System.out.println(driver.getCurrentUrl().toString());

        waitExistClick("//span[contains(.,'Economia')]", 3, true);

        System.out.println(driver.getCurrentUrl().toString());

        Selenium.cucumberReport("Este é o titulo da url " + driver.getCurrentUrl().toString());
    }

    @Dado("que eu estou na tela do {string}")
    public void que_eu_estou_na_tela_do(String site) {

        waitSecunds(2);

        driver.navigate().to(site);

        waitSecunds(2);

        System.out.println(driver.getCurrentUrl().toString());
    }

    @Quando("eu busco por java e muito bom")
    public void eu_busco_por_java_e_muito_bom() {
        System.out.println("test1");
        String textoObtido = Selenium.waitExistGetText("//h4[contains(.,'Economia') or (contains(.,'Líderes'))]", 4);
        System.out.println("Texto Obtido nos steps " + textoObtido);
    }

    @Entao("eu encontro diversos resultados")
    public void eu_encontro_diversos_resultados() {
        System.out.println("test3");
    }

    @Entao("rodo pelo mvn")
    public void rodo_pelo_mvn() {
        String a = "teste automatizado";
        Assert.assertTrue(a.contains("teste"));
        Assert.assertEquals(a, "teste automatizado");
        System.out.println(a);
    }

}
