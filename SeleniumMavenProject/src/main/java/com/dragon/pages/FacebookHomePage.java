/**
 * 
 */
package com.dragon.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.dragon.framework.Webdriver;

/**
 * @author TheDragonWarrior
 *
 */
public class FacebookHomePage extends BasePage {
	// WebElements
	private WebElement PostTextArea() {
		return searchControl("PostTextArea");
	}

	private WebElement PostButton() {
		return searchControl("PostButton");
	}

	private WebElement FriendRequestsIcon() {
		return searchControl("FriendRequestsIcon");
	}

	private WebElement ViewAllFriendRequestLinkText() {
		return searchControl("ViewAllFriendRequestLinkText");
	}

	private WebElement PendinFriendRequestCountText() {
		return searchControl("PendinFriendRequestCountText");
	}

	private WebElement SearchBox() {
		return searchControl("SearchBox");
	}
	
	private WebElement FriendConfirmationButton() {
		return searchControl("FriendConfirmationButton");
	}
	
	public String GetFriendConfirmationButtonText() {
		return FriendConfirmationButton().getText();

	}
	
	public FacebookHomePage SetSearchBox(String text) {
		SearchBox().sendKeys(text);
		Actions action = new Actions(Webdriver.driver);
		action.sendKeys(Keys.ENTER).build().perform();
		return this;
	}

	public String GetPendinFriendRequestCountText() {
		return PendinFriendRequestCountText().getText();

	}

	public FacebookHomePage ClickViewAllFriendRequestsLinkText() {
		ViewAllFriendRequestLinkText().click();
		return this;
	}

	public FacebookHomePage ClickFriendRequestsIcon() {
		FriendRequestsIcon().click();
		return this;
	}

	public FacebookHomePage ClickPostButton() {
		PostButton().click();
		return this;
	}

	// Page Methods
	public FacebookHomePage SetPostTextArea(String text) {
		PostTextArea().sendKeys(text);
		return this;
	}

}
