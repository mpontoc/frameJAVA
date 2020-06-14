package br.com.mpontoc.commons;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

public class BaseTest {

	public static WebDriver driver = DriverInit.driver();

	@BeforeClass
	public static void setUp() {
		Log.log("Running on " + Functions.verifyOS());
	}
	
	@AfterClass
	public static void finalizaExecucao() {
		driver.quit();
		Selenium.waitSecunds(3);
		Functions.zipReportFiles();
		Log.log("driver finalizado [ " + Setup.getProp("browserOrDevice" ) + " ]");
	}
	
}
