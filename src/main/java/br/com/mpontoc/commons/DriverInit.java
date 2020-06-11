package br.com.mpontoc.commons;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverInit {

	private WebDriver driver;

	public void createDriver() {

		String Browser = Setup.getProp("browser");
		String BROWSER_ENV = System.getenv("BROWSER");
		String baseURL = Setup.getProp("baseURL");
		String userName = "mpontoc1";
		String accessKey = "NbqM27aR3TxXZzee4nxr";

		if (BROWSER_ENV != null) {
			Browser = BROWSER_ENV;
			Log.log(Browser);
			Log.log(BROWSER_ENV);
		}

		switch (Browser) {

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
				Log.log("Não foi possível iniciar o driver " + Setup.getProp("browser"));
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
				Log.log("Não foi possível iniciar o driver " + Setup.getProp("browser"));
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
				Log.log("Não foi possível iniciar o driver " + Setup.getProp("browser"));
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

		case "mobile":

			try {
				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setCapability("platformName", "Android");
				caps.setCapability("deviceName", "emulator-5554");
				caps.setCapability("automationName", "uiautomator2");
				// cap.setCapability("appPackage", "com.android.calculator2");
				// cap.setCapability("appActivity", "com.android.calculator2.Calculator");
				String caminhoAPK = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
						+ File.separator + "resources" + File.separator + "apk" + File.separator + "original.apk";
				Log.log(caminhoAPK);
				// passar o apk para ser instalado no momento da execução
				caps.setCapability(MobileCapabilityType.APP, caminhoAPK);
				URL urlAppium = new URL("http://127.0.1:4723/wd/hub");
				driver = new AndroidDriver<AndroidElement>(urlAppium, caps);
				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
				Log.log("Não foi possível conectar ao Appium");
			}
			break;

		case "mobile-hub":
			
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
				Log.log("Não foi possível conectar ao Appium");
			}
			break;

		case "mobile-remote":

			try {
				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setCapability("automationName", "uiautomator2");
				caps.setCapability("device", "Samsung Galaxy S10e");
				caps.setCapability("os_version", "9.0");
				caps.setCapability("name", "Bstack-[Java] Sample Test");
				caps.setCapability("app", "bs://31a0d7b4915c71b4e5ce5c10e536e1cf32afcb27");
				driver = new AndroidDriver<AndroidElement>(
						new URL("https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"), caps);

				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
				Log.log("Não foi possível conectar ao Appium Remote");
			}
			break;
		}

	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public DriverInit() {
		Functions.setUp();
		createDriver();
	}

}
