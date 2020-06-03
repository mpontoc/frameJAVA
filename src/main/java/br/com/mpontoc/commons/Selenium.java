package br.com.mpontoc.commons;

import io.appium.java_client.MobileBy;
import io.cucumber.core.api.Scenario;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.aspectj.bridge.IMessageHandler;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;

import static br.com.mpontoc.commons.BaseTest.driver;

public class Selenium {

	private static String cucumberReportMessage = "";
	public static JavascriptExecutor executor = (JavascriptExecutor) driver;
	public static Boolean located = false;
	public static Boolean[] assertObjReceved = null;
	public static Boolean isFirstRun = null;

	public static void printScreenAfterStep(Scenario scenario) {
		if (isFirstRun == true) {
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
			isFirstRun = false;
		} else {
			System.out.println("Já imprimiu no cucumber Report");
		}
	}

	private static void setCucumberReportMessage(String msg) {
		cucumberReportMessage = msg;
	}

	private static String getCucumberReportMessage() {
		return cucumberReportMessage;
	}

	public static void cucumberReport(String msg) {
		setCucumberReportMessage(msg);
	}

	public static void writeReportAfterStep(Scenario scenario) {
		scenario.write(getCucumberReportMessage());
		cucumberReportMessage = "";
	}

	public static void waitSecunds(int segundos) {

		int segundosConvertidos = segundos * 1000;

		try {
			Thread.sleep(segundosConvertidos);
		} catch (InterruptedException e) {
			;
		}
	}
	
	public static void scrollDown(int count) {
		for (int i = 0; i < count; i++) {
			executor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
			try {Thread.sleep(2000);} catch (Exception ex) {}
		}
	}

	public static WebElement findBy(String name) {

		WebElement element = null;

		///////////////////////////////////////////////////////////////////////////////////////////////////
		// -----------------------------------------------------------------------------------------------//
		//////// FIND BY
		///////////////////////////////////////////////////////////////////////////////////////////////////
		// XPATH//////////////////////////////////////////////////////////////////////////////
		try {
			element = driver.findElement(By.xpath(name));
			borderStyle(element);
			return element;
		} catch (Exception e) {
		}

		///////////////////////////////////////////////////////////////////////////////////////////////////
		// -----------------------------------------------------------------------------------------------//
		//////// FIND BY
		///////////////////////////////////////////////////////////////////////////////////////////////////
		// XPATH @TEXT
		/////////////////////////////////////////////////////////////////////////////////////////////////// //////////////////////////////////////////////////////////////////////////////
		try {
			element = driver.findElement(By.xpath("//*[@text='" + name + "']"));
			System.out.println("Elemento idenficado pelo xpath @text " + name);
			borderStyle(element);
			return element;
		} catch (Exception e) {
		}

		///////////////////////////////////////////////////////////////////////////////////////////////////
		// -----------------------------------------------------------------------------------------------//
		//////// FIND BY
		///////////////////////////////////////////////////////////////////////////////////////////////////
		// XPATH @id
		/////////////////////////////////////////////////////////////////////////////////////////////////// //////////////////////////////////////////////////////////////////////////////
		try {
			element = driver.findElement(By.xpath("//*[@id='" + name + "']"));
			System.out.println("Elemento idenficado pelo xpath @id " + name);
			borderStyle(element);
			return element;
		} catch (Exception e) {
		}

		///////////////////////////////////////////////////////////////////////////////////////////////////
		// -----------------------------------------------------------------------------------------------//
		//////// FIND BY
		///////////////////////////////////////////////////////////////////////////////////////////////////
		// XPATH @name
		/////////////////////////////////////////////////////////////////////////////////////////////////// //////////////////////////////////////////////////////////////////////////////
		try {
			element = driver.findElement(By.xpath("//*[@name='" + name + "']"));
			System.out.println("Elemento idenficado pelo xpath @name " + name);
			borderStyle(element);
			return element;
		} catch (Exception e) {
		}

		///////////////////////////////////////////////////////////////////////////////////////////////////
		// -----------------------------------------------------------------------------------------------//
		//////// FIND BY
		///////////////////////////////////////////////////////////////////////////////////////////////////
		// XPATH @class
		/////////////////////////////////////////////////////////////////////////////////////////////////// //////////////////////////////////////////////////////////////////////////////
		try {
			element = driver.findElement(By.xpath("//*[@class='" + name + "']"));
			System.out.println("Elemento idenficado pelo xpath @class " + name);
			borderStyle(element);
			return element;
		} catch (Exception e) {
		}

		///////////////////////////////////////////////////////////////////////////////////////////////////
		// -----------------------------------------------------------------------------------------------//
		//////// FIND BY
		///////////////////////////////////////////////////////////////////////////////////////////////////
		// ID/////////////////////////////////////////////////////////////////////////////////
		try {
			element = driver.findElement(By.id(name));
			borderStyle(element);
			return element;
		} catch (Exception e) {
		}

		///////////////////////////////////////////////////////////////////////////////////////////////////
		// -----------------------------------------------------------------------------------------------//
		//////// FIND BY
		///////////////////////////////////////////////////////////////////////////////////////////////////
		// NAME///////////////////////////////////////////////////////////////////////////////
		try {
			element = driver.findElement(By.name(name));
			borderStyle(element);
			return element;
		} catch (Exception e) {
		}

		///////////////////////////////////////////////////////////////////////////////////////////////////
		// -----------------------------------------------------------------------------------------------//
		//////// FIND BY
		///////////////////////////////////////////////////////////////////////////////////////////////////
		// LINK
		/////////////////////////////////////////////////////////////////////////////////////////////////// TEXT///////////////////////////////////////////////////////////////////////////////
		try {
			element = driver.findElement(By.linkText(name));
			borderStyle(element);
			return element;
		} catch (Exception e) {
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////
		// -----------------------------------------------------------------------------------------------//
		//////// FIND BY
		///////////////////////////////////////////////////////////////////////////////////////////////////
		// CLASS
		/////////////////////////////////////////////////////////////////////////////////////////////////// NAME///////////////////////////////////////////////////////////////////////////////
		try {
			element = driver.findElement(By.className(name));
			borderStyle(element);
			return element;
		} catch (Exception e) {
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////
		// ////// FIND BY
		///////////////////////////////////////////////////////////////////////////////////////////////////
		// MOBILITY ACESS
		/////////////////////////////////////////////////////////////////////////////////////////////////// ///////////////////////////////////////////////////////////////////////////////
		try {
			element = driver.findElement(MobileBy.AccessibilityId(name));
			borderStyle(element);
			return element;
		} catch (Exception e) {
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////
		return element;
		// *[@text=
	}

	public static void borderStyle(WebElement element) {
		if (element != null) {
			executor.executeScript("arguments[0].style.border = 'medium solid red';", element);
			// executor.executeScript("arguments[0].style.backgroundColor = 'yellow';",
			// element);
		}
	}

	public static void validaElemento(String obj, Boolean[] assertObjReceved) {

		try {
			if (assertObjReceved[0] == true) {
				if (located == true)
					System.out.println("Ação com o elemento " + obj + " efetuada com sucesso");
				else
					System.out.println("Ocorreu um problema com o elemento " + obj);
				Assert.assertTrue(located);
			}
		} catch (Exception e1) {
			if (located != true)
				System.out.println("Elemento " + obj + " não econtrado");
			// e1.printStackTrace();
		}

	}

	public static void waitExistClick(String obj, Integer timeout, Boolean... assertObj) {
		WebElement element = null;
		assertObjReceved = assertObj;
		located = false;
		for (int i = 0; i <= timeout; i++) {
			element = findBy(obj);
			if (element != null) {
				located = true;
				System.out.println("Elemento " + obj + " encontrado");
				try {
					executor.executeScript("arguments[0].style.border = 'medium solid blue';", element);
				} catch (Exception e) {
					;
				}
				element.click();
				break;
			} else
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					;
				}
		}
		validaElemento(obj, assertObjReceved);
	}

	public static void waitExistClickAndPerform(String menuDropDown, String link, Integer timeout,
			Boolean... assertObj) {
		Actions actions = new Actions(driver);
		WebElement element1 = null;
		WebElement element2 = null;
		assertObjReceved = assertObj;
		located = false;
		for (int i = 0; i <= timeout; i++) {
			element1 = findBy(menuDropDown);
			if (element1 != null) {
				located = true;
				actions.moveToElement(element1);
				// actions.click();
				actions.perform();
				try {
					executor.executeScript("arguments[0].style.border = 'medium solid blue';", element1);
				} catch (Exception e) {
					;
				}
				element2 = findBy(link);
				waitExistClick(link, 2);
				System.out.println("Elemento " + link + " encontrado");
				break;
			} else
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					;
				}
		}
		validaElemento(link, assertObjReceved);
	}

	public static void waitExistClickNewWindow(String obj, Integer numberWindow, Integer timeout,
			Boolean... assertObj) {
		WebElement element = null;
		assertObjReceved = assertObj;
		located = false;

		ArrayList<String> janela = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(janela);
		System.out.println(janela.get(1));
		driver.switchTo().window((String) janela.get(0)).close();

		for (int i = 0; i <= timeout; i++) {
			driver.switchTo().window((String) janela.get(numberWindow));
			element = findBy(obj);
			if (element != null) {
				located = true;
				System.out.println("Elemento " + obj + " encontrado");
				try {
					executor.executeScript("arguments[0].style.border = 'medium solid blue';", element);
				} catch (Exception e) {
					;
				}
				element.click();
				break;
			} else
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					;
				}
		}
		validaElemento(obj, assertObjReceved);
	}

	public static void waitExistSet(String obj, String conteudo, Integer timeout, Boolean... assertObj) {
		WebElement element = null;
		assertObjReceved = assertObj;
		located = false;
		for (int i = 0; i <= timeout; i++) {
			element = findBy(obj);
			if (element != null) {
				located = true;
				System.out.println("Elemento " + obj + " encontrado");
				element.sendKeys(conteudo);
				break;
			} else
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					;
				}
		}
		validaElemento(obj, assertObjReceved);
	}
	
	public static void waitExistSetNewWindow(String obj, String conteudo, Integer numberWindow, Integer timeout, Boolean... assertObj) {
		WebElement element = null;
		assertObjReceved = assertObj;
		located = false;
		
		ArrayList<String> janela = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(janela);
		System.out.println(janela.get(1));
		driver.switchTo().window((String) janela.get(0)).close();
		
		for (int i = 0; i <= timeout; i++) {
			driver.switchTo().window((String) janela.get(numberWindow));
			element = findBy(obj);
			if (element != null) {
				located = true;
				System.out.println("Elemento " + obj + " encontrado");
				element.sendKeys(conteudo);
				break;
			} else
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					;
				}
		}
		validaElemento(obj, assertObjReceved);
	}

	public static boolean waitExist(String obj, Integer timeout, Boolean... assertObj) {
		WebElement element = null;
		assertObjReceved = assertObj;
		located = false;
		for (int i = 0; i <= timeout; i++) {
			element = findBy(obj);
			if (element != null) {
				located = true;
				System.out.println("Elemento " + obj + " encontrado");
				break;
			} else
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					;
				}
		}
		validaElemento(obj, assertObjReceved);
		return located;
	}

	public static String waitExistGetText(String obj, Integer timeout, Boolean... assertObj) {
		WebElement element = null;
		String textoObtido = "";
		assertObjReceved = assertObj;
		located = false;
		for (int i = 0; i <= timeout; i++) {
			element = findBy(obj);
			if (element != null) {
				textoObtido = element.getText().toString();
				if (textoObtido.length() > 3) {
					located = true;
					System.out.println("Elemento " + obj + " encontrado");
					try {
						executor.executeScript("arguments[0].style.backgroundColor = 'yellow';", element);
					} catch (Exception e) {
						;
					}
					System.out.println("Texto obtido [ " + textoObtido + " ]");
					break;
				}
			} else
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					;
				}
		}
		validaElemento(obj, assertObjReceved);
		return textoObtido;
	}

	public static void waitExistSelectComboBox(String obj, String value, Integer timeout, Boolean... assertObj) {
		WebElement element = null;
		assertObjReceved = assertObj;
		located = false;
		for (int i = 1; i <= timeout; i++) {
			element = findBy(obj);
			if (element != null) {
				located = true;
				System.out.println("Elemento " + obj + " encontrado");
				element.click();
				new Select(element).selectByVisibleText(value);
				;
				break;
			} else
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					;
				}
		}
		validaElemento(obj, assertObjReceved);
	}

}