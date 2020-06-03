package br.com.mpontoc.commons;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

public class BaseTest {

	public static WebDriver driver = new DriverInit().getDriver();

	@BeforeClass
	public static void setUp() {
		System.out.println("Running on " + Functions.verifyOS());
		Functions.setUp();
		
	}
	
	@AfterClass
	public static void finalizaExecucao() {
		driver.quit();
		Selenium.waitSecunds(3);
		Functions.zipReportFiles();
		System.out.println("driver finalizado [ " + Setup.getProp("browser" ) + " ]");

	}
	
}
