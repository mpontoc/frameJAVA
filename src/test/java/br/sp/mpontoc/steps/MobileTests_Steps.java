package br.sp.mpontoc.steps;

import static org.hamcrest.CoreMatchers.hasItem;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.sp.mpontoc.commons.PageHub;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import io.appium.java_client.MobileBy;

public class MobileTests_Steps extends PageHub {
	
	PageHub page = new PageHub(); 
	
	private WebElement menu = null;
	private WebElement nome = null;
	private String validaMenu;
	private String validaNome;

	@Dado("^que eu estou no device$")
	public void que_eu_estou_no_device() throws Throwable {

		menu = driver.findElement(By.xpath("//*[@text='Formulário']"));
		validaMenu = menu.getText().toString();
		menu.click();
		
		System.out.println(validaMenu);

	}
	
	@Quando("^eu abro o app CT_Appium$")
	public void eu_abro_o_app_CT_Appium() throws Throwable {
		
		if(driver.findElement(MobileBy.xpath("//android.widget.FrameLayout")) != null) 
			System.out.println("Estou no app");
		else
			System.out.println("Problema para abrir o app");
	}

	@Quando("^entro no formulário$")
	public void entro_no_formulário() throws Throwable {

		Assert.assertEquals("Formulário", validaMenu);

	}

	@Entao("^preencho os dados$")
	public void preencho_os_dados() throws Throwable {
		
		nome = driver.findElement(By.xpath("//*[@text='Nome']"));
		nome.sendKeys("Cleber");
		validaNome = nome.getText();
		System.out.println(validaNome);
		driver.findElement(By.xpath("//*[@text='XBox One']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@text='Nintendo Switch']")).click();
		driver.findElement(MobileBy.xpath("//android.widget.CheckBox")).click();
		driver.findElement(MobileBy.xpath("//android.widget.Switch")).click();
		Assert.assertEquals("Cleber", validaNome);
	}

	@Quando("^eu abro a calculador$")
	public void eu_abro_a_calculador() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("test");
		String Resultado = driver.findElement(By.id("result")).getText();
		Assert.assertEquals("4", Resultado); 
		System.out.println(Resultado);
	}

	@Entao("^é apresentado o app calculadora$")
	public void é_apresentado_o_app_calculadora() throws Throwable {
		System.out.println("test");
	    // Write code here that turns the phrase above into concrete actions
	}
}
