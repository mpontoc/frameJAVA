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
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverInit {

	private WebDriver driver;

	@SuppressWarnings("rawtypes")
	public void createDriver() {

		String Browser = Setup.getProp("browser");
		String baseURL = Setup.getProp("baseURL");

		switch (Browser) {
		
		case "firefox-headless":

			try {
				Functions.setPropDriver();
				FirefoxOptions options = new FirefoxOptions();
				options.setHeadless(true);
				driver = new FirefoxDriver(options);
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				
				System.out.println("Problema para rodar em headless");
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		break;
		
		case "firefox-remote":

			try {
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setBrowserName("firefox");
				cap.setPlatform(Platform.LINUX);
				// Chrome option
				FirefoxOptions options = new FirefoxOptions();
				options.merge(cap);
				options.setHeadless(true);
				// Capabilities chromeCapabilities = DesiredCapabilities.chrome();
				driver = new RemoteWebDriver(new URL(baseURL), options);
				Thread.sleep(10000);
				driver.get(baseURL);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Não foi possível conectar o selenium hub firefox");
				e.printStackTrace();
			}
			/*
			 * FirefoxBinary firefoxBinary = new FirefoxBinary();
			 * firefoxBinary.addCommandLineOptions("--headless"); FirefoxOptions
			 * firefoxOptions = new FirefoxOptions();
			 * firefoxOptions.setBinary(firefoxBinary); driver = new
			 * FirefoxDriver(firefoxOptions);
			 */
			break;

		case "chrome-remote":
			
			try {
				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.LINUX);
				// Chrome option
				ChromeOptions options = new ChromeOptions();
				options.merge(cap);
				options.setHeadless(true);
				// Capabilities chromeCapabilities = DesiredCapabilities.chrome();
				driver = new RemoteWebDriver(new URL(baseURL), options);
				Thread.sleep(30000);
				driver.get(baseURL);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Não foi possível conectar o selenium hub chrome");
				e.printStackTrace();
			}
			/*
			 * ChromeOptions options = new ChromeOptions();
			 * options.addArguments("headless");
			 * options.addArguments("window-size=1200x600"); driver = new
			 * ChromeDriver(options);
			 */
			break;

		case "chrome":

			Functions.setPropDriver();

			driver = new ChromeDriver();

			break;

		case "firefox":

			Functions.setPropDriver();

			driver = new FirefoxDriver();

			break;
			
		case "mobile":
			
			try {
				DesiredCapabilities cap = new DesiredCapabilities();
				
			    cap.setCapability("platformName", "Android");
			    cap.setCapability("deviceName", "emulator-5554 ");
			    cap.setCapability("automationName", "uiautomator2");
		//	    cap.setCapability("appPackage", "com.android.calculator2");
		//	    cap.setCapability("appActivity", "com.android.calculator2.Calculator");
			    
		//	    cap.setCapability("deviceName", "Android Emulator");

			    String caminhoAPK = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + 
			    		"resources" + File.separator + "apk" + File.separator + "original.apk" 
			    		;
			    
			    System.out.println(caminhoAPK);
			    
			    //passar o apk para ser instalado no momento da execução
				cap.setCapability(MobileCapabilityType.APP, caminhoAPK);
			
				URL urlAppium = new URL ("http://127.0.1:4723/wd/hub");

//				driver = new AppiumDriver<MobileElement>(urlAppium, cap);
				driver = new AndroidDriver(urlAppium, cap);
				
				Thread.sleep(5000);
				//driver.get(baseURL);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Não foi possível conectar ao Appium");
			
			break;

			}
		}
			
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public DriverInit() {
		createDriver();
	}

}
