package br.com.mpontoc.steps;

import static br.com.mpontoc.commons.BaseTest.driver;
import static br.com.mpontoc.commons.Selenium.waitExistClick;
import static br.com.mpontoc.commons.Selenium.waitSecunds;

import org.junit.Assert;

import br.com.mpontoc.commons.Functions;
import br.com.mpontoc.commons.Log;
import br.com.mpontoc.commons.Selenium;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

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
            Log.log("Hoje é feriado");
        else
            Log.log("Hoje não é feriado");

        Log.log("test");

        driver.get("http://www.uol.com.br");

        waitSecunds(2);

        Log.log(driver.getCurrentUrl().toString());

        waitExistClick("//span[contains(.,'Economia')]", 3, true);

        Log.log(driver.getCurrentUrl().toString());

        Selenium.cucumberReport("Este é o titulo da url " + driver.getCurrentUrl().toString());
    }

    @Dado("que eu estou na tela do {string}")
    public void que_eu_estou_na_tela_do(String site) {
        waitSecunds(2);

        driver.navigate().to(site);

        waitSecunds(2);

        Log.log(driver.getCurrentUrl().toString());
    }

    @Quando("eu busco por java e muito bom")
    public void eu_busco_por_java_e_muito_bom() {
        Log.log("test1");
        String textoObtido = Selenium.waitExistGetText("//h4[contains(.,'Economia') or (contains(.,'Líderes'))]", 4);
        Log.log("Texto Obtido nos steps " + textoObtido);
    }

    @Entao("eu encontro diversos resultados")
    public void eu_encontro_diversos_resultados() {
        Log.log("test3");
    }

    @Entao("rodo pelo mvn")
    public void rodo_pelo_mvn() {
        String a = "teste automatizado";
        Assert.assertTrue(a.contains("teste"));
        Assert.assertEquals(a, "teste automatizado");
        Log.log(a);
    }

}
