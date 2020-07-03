package br.com.mpontoc.commons;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.android.AndroidDriver;

public class BaseTest {
	
	
	public static WebDriver driver;

	@BeforeClass
	public static void setUp() {

		if (Setup.getProp("browserOrDevice").contains("mobile")) {
			driver = MobileDriverInit.driver();
		}

		else {
			driver = DriverInit.driver();
		}
	}

	@AfterClass
	public static void finalizaExecucao() {
		try {
			driver.quit();
		} catch (Exception e) {
		}
		Functions.waitSecunds(3);
		Log.log("Report salvo no caminho: " + System.getProperty("user.dir") + File.separator + "target"
				+ File.separator + "cucumber-reports");
		Functions.zipReportFiles();
		Log.log("driver finalizado [ " + Setup.getProp("browserOrDevice") + " ]");
	}

}
