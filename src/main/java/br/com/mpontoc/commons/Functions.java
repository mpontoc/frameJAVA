package br.com.mpontoc.commons;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

public class Functions extends PageHub {

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

	public static void processKill() {

		Runtime rt = Runtime.getRuntime();
		String Browser = Setup.getProp("browser");

		if (verifyOS() == "LINUX") {
						
			String killFirefox = "pkill firefox";
			String killFirefoxDriver = "pkill geckodriver";
			String killChrome = "pkill chrome";
			String killChromeDriver = "pkill chromedriver";
			String verifyVersionChrome = "google-chrome -version";
			String verifyVersoinFirefox = "firefox -version";

			try {
				
				if(Browser == "firefox") {
					rt.exec(killFirefox);
					rt.exec(killFirefoxDriver);
					rt.exec(verifyVersoinFirefox).getOutputStream().toString();
					rt.exec(verifyVersionChrome).getOutputStream();
					System.out.println(verifyVersoinFirefox);
					System.out.println(verifyVersionChrome);
					
				}else{
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
				
				if(Browser.contains("firefox")) {
					rt.exec(killWinFirefox);
					rt.exec(killWinFirefoxDriver);
				}else{
					rt.exec(killWinChrome);
					rt.exec(killWinChromeDriver);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public static void setPropDriver() {

		processKill();

		String OS = verifyOS();

		System.out.println(OS);

		if (OS == "LINUX") {
			System.setProperty("webdriver.chrome.driver", "lib/webdriver/linux/chromedriver");
			System.setProperty("webdriver.gecko.driver", "lib/webdriver/linux/geckodriver");

		} else {
			System.setProperty("webdriver.chrome.driver", ".\\lib\\webdriver\\chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", ".\\lib\\webdriver\\geckodriver.exe");
		}
	}
	
	
	
	public static void finalizaExecucao() {
		
		driver.quit();
		System.out.println("driver finalizado");
		
	}

}
