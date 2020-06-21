package br.com.mpontoc.steps;

import static br.com.mpontoc.commons.BaseTest.driver;
import static br.com.mpontoc.commons.Selenium.cucumberReport;
import static br.com.mpontoc.commons.Selenium.waitExist;
import static br.com.mpontoc.commons.Selenium.waitExistClick;
import static br.com.mpontoc.commons.Selenium.waitExistSet;
import static br.com.mpontoc.commons.Functions.waitSecunds;

import java.util.List;

import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import br.com.mpontoc.commons.JsonFileReader;
import br.com.mpontoc.commons.Log;
import br.com.mpontoc.commons.Selenium;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.AfterStep;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class Gmail_Steps {

    @BeforeStep("@gmail")
    public void reportClear(Scenario scenario) {
        Selenium.isFirstRun = true;
    }

    @AfterStep("@gmail")
    public void report(Scenario scenario) {
        Selenium.printScreenAfterStep(scenario);
        Selenium.writeReportAfterStep(scenario);
    }

    @Dado("que eu estou na tela do gmail")
    public void que_eu_estou_na_tela_do_gmail() {

        driver.get("http:www.gmail.com");

        waitSecunds(2);

        Selenium.cucumberReport(driver.getCurrentUrl());
        Log.log(driver.getCurrentUrl());
    }

    @Quando("efetuo o login")
    public void efetuo_o_login() throws JSONException {
    	
		String user = JsonFileReader.getJsonSingleField("gmail.json", "gmail", "user");
		String passwd = JsonFileReader.getJsonSingleField("gmail.json", "gmail", "passwd");

//        waitExistSet("//input[@id='identifierId']", user, 5, true);
        waitExistSet("identifierId", user, 5, true);
//        waitExistClick("//div[@id='identifierNext']", 5, true);
        waitExistClick("identifierNext", 5, true);
        waitSecunds(2);
        waitExistSet("//input[@name='password']", passwd, 5, true);
        waitExistClick("//span[contains(.,'Próxima')]", 5);
        try {
            driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Keys.ENTER);
        } catch (Exception e) {
        }

    }

    @Entao("apago todos os emails")
    public void apago_todos_os_emails() {

        while (waitExist("//div[@aria-checked='false' and @role='checkbox']", 3) == true) {

            waitSecunds(4);

            try {
                List<WebElement> selecionar = driver.findElements(By.xpath("//div[@data-tooltip='Selecionar']"));

                for (int i = 0; i < selecionar.size(); i++) {

                    try {
                        selecionar.get(i).click();
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e) {
                System.out.println("Não existem e-mails para selecionar");
            }


            if (waitExist("//div[@aria-checked='true' and @role='checkbox']", 1) == true) {
                waitSecunds(2);
                waitExistClick("//div[@data-tooltip='Excluir']", 3);
                waitSecunds(2);
            } else {

                break;

            }

        }

        waitExistClick("//a[@title='Lixeira']", 3);
        waitSecunds(2);
        waitExistClick("//span[contains(.,'Esvaziar a lixeira agora')]", 3);
        waitSecunds(2);
        // driver.findElement(By.xpath("//button[contains(.,'OK')]")).sendKeys(Keys.ENTER);

        try {
            List<WebElement> buttonOK = driver.findElements(By.xpath("//button[contains(.,'OK')]"));

            for (int i = 0; i < buttonOK.size(); i++) {

                try {
                    buttonOK.get(i).click();
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            System.out.println("Não existem e-mails para excluir");
        }

        waitSecunds(2);
        cucumberReport("Email limpo " + driver.getCurrentUrl().toString());

    }

    @Entao("seleciono aba das redes sociais")
    public void seleciono_aba_das_redes_sociais() {
        waitExistClick("//a[@title='Caixa de entrada']", 3);
        waitSecunds(2);
        waitExistClick(
                "//div[@data-tooltip='Mensagens de redes sociais, sites de compartilhamento de mídia, serviços de namoro on-line e outros sites sociais.']",
                3);
        waitSecunds(2);

    }

    @Entao("seleciono aba das promocoes")
    public void seleciono_aba_das_promocoes() {
        waitExistClick("//a[@title='Caixa de entrada']", 3);
        waitSecunds(2);
        waitExistClick("//div[@data-tooltip='Promoções, ofertas e outros e-mails de marketing.']", 3);
        waitSecunds(2);
    }

}
