package br.com.mpontoc.commons.driverFactory;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import br.com.mpontoc.commons.mobile.Mobile;
import br.com.mpontoc.commons.utils.Functions;
import br.com.mpontoc.commons.utils.Log;
import br.com.mpontoc.commons.utils.Setup;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class MobileDriverInit {

//	public static AndroidDriver<AndroidElement> driverMobile;
	public static AppiumDriver<MobileElement> driverMobile;

	private static AppiumDriver<MobileElement> createMobileDriver() {
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
				String caminhoAPK = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
						+ File.separator + "resources" + File.separator + "apk" + File.separator + "original.apk";
				Log.log(caminhoAPK);
				// passar o apk para ser instalado no momento da execução
				// caps.setCapability(MobileCapabilityType.APP, caminhoAPK);
				URL urlAppium = new URL(Setup.getProp("baseAppium"));
				String device = Setup.getProp("device").toLowerCase().trim();
				if (device.equals("android"))
					driverMobile = new AndroidDriver<MobileElement>(urlAppium, Mobile.caps());
				else
					driverMobile = new IOSDriver<MobileElement>(urlAppium, Mobile.caps());
				Thread.sleep(3000);
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
				driverMobile = new AppiumDriver<MobileElement>(
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

	public static AppiumDriver<MobileElement> driver() {

		return createMobileDriver();
	}

}
