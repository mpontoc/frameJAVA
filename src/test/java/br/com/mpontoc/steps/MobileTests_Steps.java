package br.com.mpontoc.steps;

import static br.com.mpontoc.commons.BaseTest.driver;
import static br.com.mpontoc.commons.Selenium.waitExistClick;
import static br.com.mpontoc.commons.Selenium.waitExistGetText;
import static br.com.mpontoc.commons.Selenium.waitExistSet;
import static br.com.mpontoc.commons.Functions.waitSecunds;
import static br.com.mpontoc.commons.MobileDriverInit.driverMobile;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.com.mpontoc.commons.Mobile;
import br.com.mpontoc.commons.MobileDriverInit;
import br.com.mpontoc.commons.Selenium;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.StartsActivity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
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

    private String validaMenu;
    private String validaNome;

    @Dado("^que eu estou no device")
    public void que_eu_estou_no_device() throws Throwable {
    	  	
    	Selenium.newApp("hiperCard");
    	waitSecunds(2);
    	Selenium.newApp("itauCard");
    	waitSecunds(2);
    	Selenium.newApp("crediCard");
    	waitSecunds(2);
    	Selenium.newApp("luiza");
    	waitSecunds(2);
    	Selenium.newApp("calc");
    	waitSecunds(2);
    	waitExistClick("5", 3);
    	Selenium.newApp("ctAppium");
    	waitSecunds(2);
    	waitSecunds(2);

        validaMenu = Selenium.waitExistGetText("Formulário", 3, true);
        System.out.println("Texto obtido nos steps " + validaMenu);
        Selenium.waitExistClick("//*[@text='Formulário']", 3, true);

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
    
    @Dado("que eu estou no app {string}")
    public void que_eu_estou_no_app(String app) {

    	Mobile.setApp(app);
    	
    	String appAtual = Mobile.getApp();
    	
    	System.out.println("Este é o app " + appAtual);
    	
    	Selenium.newApp(app);
    	waitSecunds(2);
    	//driverMobile.pressKey(new KeyEvent(AndroidKey.HOME));    	
    	driverMobile.pressKey(new KeyEvent(AndroidKey.MENU));    	
    	waitSecunds(4);
    }
    
    @Quando("eu abro o app correspondente")
    public void eu_abro_o_app_correspondente() {
    	
    	System.out.println("Teste efetuado no app " + Mobile.getApp());

    }
}
