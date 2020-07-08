package br.com.mpontoc.commons.utils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.zeroturnaround.zip.ZipUtil;

public class Functions {

	static Runtime rt = Runtime.getRuntime();
	public static boolean feriado;

	public static String verifyOS() {
		String OS = null;

		if (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1) {
			OS = "WINDOWS";
		} else {
			OS = "LINUX";
		}

		assertNotNull(OS);

		return OS;
	}

	public static Boolean verificaFeriado() {

		feriado = false;
		String data = "";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-3:00"));
		data = dateFormat.format(new Date(System.currentTimeMillis())).substring(0, 10);
		Log.log("Data de hoje " + data);

		try {
			String responseGetDia = given().when().get("http://elekto.com.br/api/Calendars/br-SP").then()
					.statusCode(200).extract().body().asString();

			Log.log("imprimindo o get " + responseGetDia);

			if (responseGetDia.contains(data)) {
				feriado = true;
				Log.log("Hoje é feriado em São Paulo " + data);
			} else {
				feriado = false;
				Log.log("Hoje não é feriado em São Paulo " + data);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void waitSecunds(int segundos) {

		int segundosConvertidos = segundos * 1000;

		try {
			Thread.sleep(segundosConvertidos);
		} catch (InterruptedException e) {
			;
		}
	}


	public static void processKill() {

		String killBrowser = Setup.getProp("killBrowser");

		if (killBrowser.equals("true")) {

			// Runtime rt = Runtime.getRuntime();
			String Browser = Setup.getProp("browserOrDevice");

			if (verifyOS() == "LINUX") {

				String killFirefox = "pkill firefox";
				String killFirefoxDriver = "pkill geckodriver";
				String killChrome = "pkill chrome";
				String killChromeDriver = "pkill chromedriver";
				String verifyVersionChrome = "google-chrome -version";
				String verifyVersoinFirefox = "firefox -version";

				try {

					if (Browser == "firefox") {
						rt.exec(verifyVersoinFirefox).getOutputStream().toString();
						rt.exec(verifyVersionChrome).getOutputStream();
						rt.exec(killFirefox);
						rt.exec(killFirefoxDriver);
						Log.log(verifyVersoinFirefox);
						Log.log(verifyVersionChrome);

					} else {
						rt.exec(killChrome);
						rt.exec(killChromeDriver);
						rt.exec(verifyVersoinFirefox);
						rt.exec(verifyVersionChrome);
					}

				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {

				String killWinFirefox = "taskkill /f /im firefox.exe";
				String killWinFirefoxDriver = "taskkill /f /im geckodriver.exe";
				String killWinChrome = "taskkill /f /im chrome.exe";
				String killWinChromeDriver = "taskkill /f /im chromedriver.exe";

				try {

					if (Browser.contains("firefox")) {
						rt.exec(killWinFirefox);
						rt.exec(killWinFirefoxDriver);
					} else {
						rt.exec(killWinChrome);
						rt.exec(killWinChromeDriver);
					}

				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

	}

	public static void setPropDriver() {

		processKill();

		String OS = verifyOS();

		if (OS == "LINUX") {
			System.setProperty("webdriver.chrome.driver", "lib/webdriver/linux/chromedriver");
			System.setProperty("webdriver.chrome.verboseLogging", "false");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			System.setProperty("webdriver.gecko.driver", "lib/webdriver/linux/geckodriver");
			System.setProperty("webdriver.firefox.logfile", "/dev/null");

		} else {
			System.setProperty("webdriver.chrome.driver", ".\\lib\\webdriver\\chromedriver.exe");
			System.setProperty("webdriver.chrome.verboseLogging", "false");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			System.setProperty("webdriver.gecko.driver", ".\\lib\\webdriver\\geckodriver.exe");
			System.setProperty("webdriver.firefox.logfile", "/dev/null");
		}
	}

	public static void apagaReportAntesExecucao() {
		String pathReport = System.getProperty("user.dir") + File.separator + "target" + File.separator
				+ "cucumber-reports/";
		try {FileUtils.forceDelete(new File(pathReport + File.separator + "log")); } catch (IOException e) {;}
		try {
			File dir = null;
			dir = new File(pathReport);
			File[] listFiles = dir.listFiles();
			for (File file : listFiles) {
				if (file.getName().contains(".png") == false) {
					Log.log("Arquivo não será apagado " + file.getName());
				} else {
					Log.log("Deletando " + file.getName());
					file.delete();
				}
			}
			
			Log.log("Evidências apagadas com sucesso ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public static void zipReportFiles() {

		if (Setup.getProp("backupReports").equals("true")) {

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
			dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-3:00"));
			String data = dateFormat.format(new Date(System.currentTimeMillis()));
			String pathReport = System.getProperty("user.dir") + File.separator + "target" + File.separator
					+ "cucumber-reports";
			String pathReportBackup = System.getProperty("user.dir") + File.separator + "target" + File.separator
					+ "cucumber-reports-backup";
			String pathReportZip = pathReportBackup + File.separator + "Report_" + data + ".zip";

			File path = new File(pathReportBackup);
			if(!path.exists())
				path.mkdir();
			
			try {
				ZipUtil.pack(new File(pathReport), new File(pathReportZip));
				Log.log("Evidências salvas no caminho " + pathReportZip);
			} catch (Exception e) {
				Log.log("Não foi possível zipar a pasta de Report");
				e.printStackTrace();
			}
		}

	}
	
	public static void printOS() {
		
		String OS = null;
		OS = System.getProperty("os.name");
		Log.log("Running on " + OS);
	}

	public static void setUp() {
		
		printOS();
		apagaReportAntesExecucao();

	}
}
