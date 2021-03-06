package br.com.mpontoc.commons;

import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverInit {

	private static WebDriver driver;

	private static WebDriver createDriver() {
		Functions.setUp();
		String Browser = Setup.getProp("browserOrDevice");
		String BROWSER_ENV = System.getenv("BROWSER");
		String baseURL = Setup.getProp("baseURL");

		if (BROWSER_ENV != null) {
			Browser = BROWSER_ENV;
			Log.log(Browser);
			Log.log(BROWSER_ENV);
		}

		switch (Browser.trim()) {

		case "firefox":

			try {
				Functions.setPropDriver();
				FirefoxOptions optionsFirefox = new FirefoxOptions();
				optionsFirefox.addArguments("--width=1024");
				optionsFirefox.addArguments("--height=768");
				optionsFirefox.addArguments("--start-maximized");
				driver = new FirefoxDriver(optionsFirefox);
				Thread.sleep(1000);
				Log.log("Window sizes " + driver.manage().window().getSize().toString());
			} catch (Exception e2) {
				Log.log("Não foi possível iniciar o driver " + Setup.getProp("browserOrDevice"));
				e2.printStackTrace();
			}

			break;

		case "firefox-headless":

			try {
				Functions.setPropDriver();
				FirefoxOptions optHeadlessFirefox = new FirefoxOptions();
				optHeadlessFirefox.setHeadless(true);
				optHeadlessFirefox.addArguments("--width=1024");
				optHeadlessFirefox.addArguments("--height=768");
				driver = new FirefoxDriver(optHeadlessFirefox);
				Thread.sleep(1000);
				Log.log("Window sizes " + driver.manage().window().getSize().toString());
			} catch (Exception e1) {
				Log.log("Problema para rodar com firefox headless");
				e1.printStackTrace();
			}

			break;

		case "firefox-hub":

			try {
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setBrowserName("firefox");
				cap.setPlatform(Platform.LINUX);
				FirefoxOptions optRemoteFirefox = new FirefoxOptions();
				optRemoteFirefox.merge(cap);
				optRemoteFirefox.setHeadless(true);
				optRemoteFirefox.addArguments("--width=1024");
				optRemoteFirefox.addArguments("--height=768");
				driver = new RemoteWebDriver(new URL(baseURL), optRemoteFirefox);
				Thread.sleep(2000);
				Log.log("Window sizes " + driver.manage().window().getSize().toString());
				driver.get(baseURL);
			} catch (Exception e) {
				Log.log("Não foi possível conectar o selenium hub firefox");
				e.printStackTrace();
			}
			break;

		case "chrome":

			try {
				Functions.setPropDriver();
				ChromeOptions optionsChrome = new ChromeOptions();
				optionsChrome.addArguments("--window-size=1024,768");
				driver = new ChromeDriver(optionsChrome);
				Thread.sleep(1000);
				Log.log("Window sizes " + driver.manage().window().getSize().toString());
			} catch (Exception e1) {
				Log.log("Não foi possível iniciar o driver " + Setup.getProp("browserOrDevice"));
				e1.printStackTrace();
			}
			break;

		case "chrome-headless":

			try {
				Functions.setPropDriver();
				ChromeOptions optHeadlessChrome = new ChromeOptions();
				optHeadlessChrome.setHeadless(true);
				optHeadlessChrome.addArguments("--window-size=1024,768");
				// optHeadlessChrome.addArguments("--log-level=3");
				driver = new ChromeDriver(optHeadlessChrome);
				Thread.sleep(1000);
				Log.log("Window sizes " + driver.manage().window().getSize().toString());
			} catch (Exception e1) {
				Log.log("Não foi possível iniciar o driver " + Setup.getProp("browserOrDevice"));
				e1.printStackTrace();
			}
			break;

		case "chrome-hub":

			try {
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.LINUX);
				ChromeOptions optRemoteChrome = new ChromeOptions();
				optRemoteChrome.merge(cap);
				optRemoteChrome.setHeadless(true);
				optRemoteChrome.addArguments("--window-size=1024,768");
				driver = new RemoteWebDriver(new URL(baseURL), optRemoteChrome);
				Thread.sleep(2000);
				Log.log("Window sizes " + driver.manage().window().getSize().toString());
				driver.get(baseURL);
			} catch (Exception e) {
				Log.log("Não foi possível conectar o selenium hub chrome");
				e.printStackTrace();
			}
			break;

		case "mobile-docker":

			try {
				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setCapability("platformName", "Android");
				caps.setCapability("deviceName", "Android Emulator");
				caps.setCapability("automationName", "UIAutomator2");
				caps.setCapability("avd", "nexus_5_7.1.1");
				caps.setCapability("browserName", "android");
				String caminhoAPK = "/root/tmp/original.apk";
				Log.log(caminhoAPK);
				// passar o apk para ser instalado no momento da execução
				caps.setCapability("app", caminhoAPK);
				driver = new RemoteWebDriver(new URL(baseURL), caps);
				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
				Log.log("Não foi possível conectar ao Docker-Hub Android");
			}
			break;
		}
		return driver;
	}

	public static WebDriver driver() {
		return createDriver();
	}

}
