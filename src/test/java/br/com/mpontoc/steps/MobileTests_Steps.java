package br.com.mpontoc.steps;

import static br.com.mpontoc.commons.BaseTest.driver;
import static br.com.mpontoc.commons.Selenium.waitExist;
import static br.com.mpontoc.commons.Selenium.waitExistClick;
import static br.com.mpontoc.commons.Selenium.waitExistGetText;
import static br.com.mpontoc.commons.Selenium.waitExistSet;
import static br.com.mpontoc.commons.Selenium.waitSecunds;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.com.mpontoc.commons.Selenium;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class MobileTests_Steps {

    @BeforeStep("@testAppium")
    public void reportClear(Scenario scenario) {
        Selenium.isFirstRun = true;
    }

    @AfterStep("@testAppium")
    public void report(Scenario scenario) {
        Selenium.printScreenAfterStep(scenario);
        Selenium.writeReportAfterStep(scenario);
    }

    private WebElement menu = null;
    private WebElement nome = null;
    private String validaMenu;
    private String validaNome;

    @Dado("^que eu estou no device")
    public void que_eu_estou_no_device() throws Throwable {

        validaMenu = Selenium.waitExistGetText("Formulário", 3, true);
        Selenium.waitExistClick("//*[@text='Formulário']", 3, true);
        System.out.println("Texto obtido nos steps " + validaMenu);

    }

    @Quando("eu abro o app CT_Appium")
    public void eu_abro_o_app_CT_Appium() throws Throwable {

        if (Selenium.waitExist("//android.widget.FrameLayout", 3, true) == true)
            System.out.println("Estou no app");
        else
            System.out.println("Problema para abrir o app");
    }

    @Quando("entro no formulário")
    public void entro_no_formulário() throws Throwable {
        Assert.assertEquals("Formulário", validaMenu);
    }

    @Entao("preencho os dados")
    public void preencho_os_dados() throws Throwable {

        waitExistSet("Nome", "Cleber", 3, true);
        Thread.sleep(1000);
        validaNome = waitExistGetText("Cleber", 3, true);
        System.out.println(validaNome);
        waitExistClick("XBox One", 3, true);
        Thread.sleep(3000);
        waitExistClick("Nintendo Switch", 3, true);
        waitExistClick("//android.widget.CheckBox", 3, true);
        waitExistClick("//android.widget.Switch", 3, true);
        Assert.assertEquals("Cleber", validaNome);
        waitSecunds(2);
        waitExistClick("06:00", 3);
        waitExistClick("10", 3);
        waitExistClick("20",3);
        waitExistClick("OK",2);
        waitExistClick("SALVAR", 3);
        waitSecunds(2);
       // Assert.assertTrue(waitExist("10:20",2));
    }

    @Quando("eu abro a calculador")
    public void eu_abro_a_calculador() throws Throwable {
        System.out.println("test");
        String Resultado = driver.findElement(By.id("result")).getText();
        Assert.assertEquals("4", Resultado);
        System.out.println(Resultado);
    }

    @Entao("é apresentado o app calculadora")
    public void é_apresentado_o_app_calculadora() throws Throwable {
        System.out.println("test");
    }
}
