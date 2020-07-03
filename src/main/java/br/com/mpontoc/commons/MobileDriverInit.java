package br.com.mpontoc.commons;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class MobileDriverInit {

	public static AndroidDriver<AndroidElement> driverMobile;

	private static AndroidDriver<AndroidElement> createMobileDriver() {
		Functions.setUp();
		String Browser = Setup.getProp("browserOrDevice");
		String BROWSER_ENV = System.getenv("BROWSER");
		String userName = "mpontoc1";
		String accessKey = "NbqM27aR3TxXZzee4nxr";

		if (BROWSER_ENV != null) {
			Browser = BROWSER_ENV;
			Log.log(Browser);
			Log.log(BROWSER_ENV);
		}

		switch (Browser.trim()) {

		case "mobile":

			try {
				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setCapability("platformName", "Android");
				caps.setCapability("deviceName", "emulator-5554");
				caps.setCapability("automationName", "uiautomator2");
				// cap.setCapability("appPackage", "com.android.calculator2");
				// cap.setCapability("appActivity", "com.android.calculator2.Calculator");
				// caps.setCapability("appWaitActivity", "br.com.itau.cartoes.presentation.*");
				caps.setCapability("autoGrantPermissions", "true");
				String caminhoAPK = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
						+ File.separator + "resources" + File.separator + "apk" + File.separator + "original.apk";
				Log.log(caminhoAPK);
				// passar o apk para ser instalado no momento da execução
				// caps.setCapability(MobileCapabilityType.APP, caminhoAPK);
				URL urlAppium = new URL("http://127.0.1:4723/wd/hub");
				driverMobile = new AndroidDriver<AndroidElement>(urlAppium, caps);
				Thread.sleep(3000);
	//			((AndroidDriver) driverMobile).pressKey(AndroidKey.HOME);

				
			} catch (Exception e) {
				e.printStackTrace();
				Log.log("Não foi possível conectar ao Appium");
			}
			break;

		case "mobile-cloud":

			try {
				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setCapability("automationName", "uiautomator2");
				caps.setCapability("device", "Samsung Galaxy S10e");
				caps.setCapability("os_version", "9.0");
				caps.setCapability("name", "Bstack-[Java] Sample Test");
				caps.setCapability("app", "bs://31a0d7b4915c71b4e5ce5c10e536e1cf32afcb27");
				driverMobile = new AndroidDriver<AndroidElement>(
						new URL("https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"), caps);

				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
				Log.log("Não foi possível conectar ao Appium Remote");
			}
			break;
		}
		return driverMobile;

	}
	
	public static AndroidDriver<AndroidElement> driver() {
		
		return createMobileDriver();
	}

}
