package br.com.mpontoc.commons.mobile;

import org.openqa.selenium.remote.DesiredCapabilities;

import br.com.mpontoc.commons.utils.Log;

public class Mobile {

	private static String app = null;
	private static String packageApp = null;
	private static String activityApp = null;
	private static String[] packageActivities = { packageApp, activityApp };

	public static void setApp(String app) {
		Mobile.app = app;
	}

	public static String getApp() {
		return app;
	}

	public static String[] setPackageActivities(String app) {
		

		//packageActivities = { packageApp, activityApp };

		switch (app.trim()) {

		case "calc":

			try {

				packageActivities[0] = "com.android.calculator2";
				packageActivities[1] = "com.android.calculator2.Calculator";
				Log.log("Iniciando app " + app);
				return Mobile.packageActivities;

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "ctAppium":

			try {

				packageActivities[0] = "com.ctappium";
				packageActivities[1] = "com.ctappium.MainActivity";
				Log.log("Iniciando app " + app);
				return Mobile.packageActivities;

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "itaucard":

			try {

				packageActivities[0] = "com.itaucard.activity";
				packageActivities[1] = "br.com.itau.cartoes.presentation.splash.SplashActivity";
				Log.log("Iniciando app " + app);
				return Mobile.packageActivities;

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "hipercard":

			try {

				packageActivities[0] = "com.hipercard.app";
				packageActivities[1] = "br.com.itau.cartoes.presentation.splash.SplashActivity";
				Log.log("Iniciando app " + app);
				return Mobile.packageActivities;

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "credicard":

			try {

				packageActivities[0] = "com.credicard.app";
				packageActivities[1] = "br.com.itau.cartoes.presentation.splash.SplashActivity";
				Log.log("Iniciando app " + app);
				return Mobile.packageActivities;

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case "luizacard":

			try {

				packageActivities[0] = "com.cartaoluiza.app";
				packageActivities[1] = "br.com.itau.cartoes.presentation.splash.SplashActivity";
				Log.log("Iniciando app " + app);
				return Mobile.packageActivities;

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		}
		return packageActivities;

	}
	
	public static String[] getPackageActivities() {
		return Mobile.packageActivities;
		
	}
	
	public static DesiredCapabilities caps() {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("deviceName", "emulator-5554");
		caps.setCapability("automationName", "uiautomator2");
		caps.setCapability("autoGrantPermissions", "true");
		return caps;
	}
	

}