/**
 * 
 */
package com.dragon.test;

import org.testng.annotations.BeforeTest;
import com.dragon.framework.Webdriver;
import org.testng.annotations.AfterTest;

/**
 * @author TheDragonWarrior
 *
 */
public class BaseTest {

	@BeforeTest
	public void initialiseBrowser() {
		// Initializes and configures the Browser to run the Test
		Webdriver.Login();
	}

	@AfterTest
	public void closeBrowser() {
		// Closes all the driver instances opened
		Webdriver.driver.quit();
	}

}
