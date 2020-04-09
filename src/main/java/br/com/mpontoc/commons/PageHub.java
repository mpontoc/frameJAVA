package br.com.mpontoc.commons;

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

@Service
public class PageHub {
	

	public static WebDriver driver = new DriverInit().getDriver();
	
}
