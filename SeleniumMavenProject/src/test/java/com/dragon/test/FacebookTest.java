/**
 * 
 */
package com.dragon.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.dragon.framework.Log;
import com.dragon.framework.Webdriver;
import com.dragon.pages.FacebookHomePage;
import com.dragon.pages.FacebookLoginPage;
import com.dragon.utilities.Utilities;

/**
 * @author TheDragonWarrior
 *
 */
public class FacebookTest extends BaseTest {

	@Test
	public void TC00001_FacebookLoginTest()throws Exception {
		try {
			FacebookLoginPage loginPage = new FacebookLoginPage();
			loginPage.SetLoginTextBox("amit.tripathi9696@gmail.com");
			loginPage.SetPasswordTextBox("Tejas@113920");
			loginPage.ClickLoginButton();
			Thread.sleep(2000);
			Webdriver.driver.switchTo().alert().dismiss();
			
			Assert.assertTrue(Webdriver.driver.getTitle().contains("Facebook"));

		} catch (Exception e) {
			Log.info("Exception Occured : " + e.getMessage());
			Utilities.TakeScreenShot(e.getMessage().substring(0, 10));
		}
	}

	// Delete a particular Friend Request
	@Test
	public void TC00002_SearchFriendAndVerifyProfileTest() {

		try {
			String friendName = "Saurabh Dubey";
			FacebookLoginPage loginPage = new FacebookLoginPage();
			loginPage.SetLoginTextBox("amit.tripathi9696@gmail.com");
			loginPage.SetPasswordTextBox("Tejas@113920");
			FacebookHomePage homePage = loginPage.ClickLoginButton();
			Assert.assertTrue(Webdriver.driver.getTitle().contains("Facebook"));
			homePage.SetSearchBox(friendName);

		} catch (Exception e) {
			Log.info("Exception Occured : " + e.getMessage());
		}

	}

}
