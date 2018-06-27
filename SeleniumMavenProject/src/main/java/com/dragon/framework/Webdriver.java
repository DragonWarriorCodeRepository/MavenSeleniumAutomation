/**
 * 
 */
package com.dragon.framework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.dragon.pages.FacebookLoginPage;

/**
 * @author TheDragonWarrior
 *
 */
public class Webdriver {

	public static WebDriver driver;
	static ReadPropertyFile readPropertyFile = new ReadPropertyFile();

	public static void configureBrowser(String browserName) {

		if (browserName.equalsIgnoreCase("Chrome") || browserName.equalsIgnoreCase("GoogleChrome")
				|| browserName.equalsIgnoreCase("Google")) {
			System.setProperty("webdriver.chrome.driver", readPropertyFile.getChromeDriverLocation());
			Webdriver.driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("iexplore") || browserName.equalsIgnoreCase("InternetExplorer")
				|| browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", readPropertyFile.getIEDriverLocation());
			Webdriver.driver = new InternetExplorerDriver();
		} else if (browserName.equalsIgnoreCase("Firefox") || browserName.equalsIgnoreCase("MozillaFirefox")
				|| browserName.equalsIgnoreCase("Mozilla")) {
			System.setProperty("webdriver.gecko.driver", readPropertyFile.getGeckoDriverLocation());
			Webdriver.driver = new FirefoxDriver();
		}
		Webdriver.driver.manage().deleteAllCookies();
		Webdriver.driver.manage().window().maximize();
		Webdriver.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Webdriver.driver.manage().timeouts().pageLoadTimeout(70, TimeUnit.SECONDS);

	}

	public static FacebookLoginPage Login() {
		
		String browser = readPropertyFile.getBrowserName();

		configureBrowser(browser);

		driver.navigate().to("https://www.facebook.com");
		
		return new FacebookLoginPage();
	}

}
