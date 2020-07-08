package br.com.mpontoc.commons.commands;

import java.net.MalformedURLException;
import java.net.URL;

import br.com.mpontoc.commons.utils.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class AppiumCommands {
	
	
	public static AndroidDriver<AndroidElement> androidDriver;
	public static IOSDriver<IOSElement> iosDriver;
	
	
	public static AndroidDriver<AndroidElement> androidDriver() {
		try {
			androidDriver = (AndroidDriver<AndroidElement>) BaseTest.driver;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return androidDriver;
	}
	
	public static void pressKeyAndroidHome() {
		
		try {
			AppiumCommands.androidDriver().pressKey(new KeyEvent(AndroidKey.HOME));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	

}
