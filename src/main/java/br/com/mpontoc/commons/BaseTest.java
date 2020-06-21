package br.com.mpontoc.commons;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

public class BaseTest {

	public static WebDriver driver = DriverInit.driver();

	@BeforeClass
	public static void setUp() {

	}

	@AfterClass
	public static void finalizaExecucao() {
		try {
			driver.quit();
		} catch (Exception e) {
		}
		Functions.waitSecunds(3);
		Functions.zipReportFiles();
		Log.log("driver finalizado [ " + Setup.getProp("browserOrDevice") + " ]");
	}

}
