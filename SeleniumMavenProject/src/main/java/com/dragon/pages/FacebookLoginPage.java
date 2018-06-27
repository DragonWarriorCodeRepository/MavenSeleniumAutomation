/**
 * 
 */
package com.dragon.pages;

import org.openqa.selenium.WebElement;

/**
 * @author TheDragonWarrior
 *
 */
public class FacebookLoginPage extends BasePage {

	private WebElement LoginTextBox() {
		return searchControl("LoginTextBox");
	}

	private WebElement PasswordTextBox() {
		return searchControl("PasswordTextBox");
	}

	private WebElement LoginButton() {
		return searchControl("LoginButton");
	}

	public FacebookLoginPage SetLoginTextBox(String text) {
		LoginTextBox().sendKeys(text);
		return this;
	}

	public FacebookLoginPage SetPasswordTextBox(String text) {
		PasswordTextBox().sendKeys(text);
		return this;
	}

	public FacebookHomePage ClickLoginButton() {
		LoginButton().click();
		return new FacebookHomePage();
	}

}
