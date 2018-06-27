package com.dragon.framework;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dragon.framework.Enums.CheckedStatus;
import com.dragon.framework.Enums.EndAction;
import com.dragon.framework.Enums.MouseClick;
import com.dragon.framework.Enums.WaitTime;
import com.dragon.pages.BasePage;

/**
 * @author M1030482
 *
 */
public class SeleniumWebUIDriver extends GenericSeleniumWebUIDriver {

	// Method to Launches the URL
	@Override
	public void LaunchURL(String URL) {
		// Launches the URL provided
		Webdriver.driver.navigate().to(URL);

	}

	// Method to put the wait in between the WebActions
	@Override
	public void Wait(WaitTime waitTime) {

		WebDriverWait wait = new WebDriverWait(Webdriver.driver, 40);

		switch (waitTime) {
		case VeryShortWait:
			// wait.until(ExpectedConditions.elementToBeClickable(element));
			break;
		case ShortWait:

			break;
		case MediumWait:

			break;
		case LongWait:

			break;
		default:

		}

	}

	// Method to Set the Text box
	@Override
	public void setTextBox(WebElement textBoxWebElement, String text, MouseClick mouseClick, EndAction endAction) {
		String message = String.format("Set %s Web Element to %s", textBoxWebElement, text);
		try {
			textBoxWebElement.clear();
			textBoxWebElement.sendKeys(text);
		} catch (Exception e) {
			// throw new FrameworkException(message);
		}
	}

	// Overriding method to Set the Text box
	@Override
	public void setTextBox(WebElement textBoxWebElement, String text, EndAction endAction) {
		textBoxWebElement.clear();
		textBoxWebElement.sendKeys(text);

	}

	// Method to make different Mouse Click Actions
	@Override
	public void mouseClick(WebElement elementToBeClicked, MouseClick mouseClick) {
		Actions action = new Actions(Webdriver.driver);

		switch (mouseClick) {
		case SINGLE:
			action.click(elementToBeClicked).perform();
			break;
		case DOUBLE:
			action.doubleClick(elementToBeClicked).perform();
			break;
		case RIGHT:
			action.contextClick(elementToBeClicked).build().perform();
			break;
		default:

		}
	}

	// Method to set the Combo box value
	@Override
	public void setCombobox(WebElement comboBoxwebElement, String text, MouseClick mouseClick, EndAction endAction) {
		// TODO Auto-generated method stub

	}

	//
	@Override
	public <T> BasePage clickHyperlink(WebElement hyperlinkWebElement, MouseClick mouseClick) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void endAction(WebElement element, EndAction endAction) {

		switch (endAction) {

		// For Pressing Enter Key from Keyboard
		case ENTER:
			element.sendKeys(Keys.ENTER);
			break;
		// For Pressing Tab Key from Keyboard
		case TAB:
			element.sendKeys(Keys.TAB);
			break;
		// For Pressing Shift Key from Keyboard
		case SHIFT:
			element.sendKeys(Keys.SHIFT);
			break;
		// For Pressing Ctrl Key from Keyboard
		case CTRL:
			element.sendKeys(Keys.CONTROL);
			break;
		// For Pressing Arrow Left Key from Keyboard
		case LEFT:
			element.sendKeys(Keys.ARROW_LEFT);
			break;
		// For Pressing Arrow Right Key from Keyboard
		case RIGHT:
			element.sendKeys(Keys.ARROW_RIGHT);
			break;
		// For Pressing Arrow Up Key from Keyboard
		case UP:
			element.sendKeys(Keys.ARROW_UP);
			break;
		// For Pressing Arrow Down Key from Keyboard
		case DOWN:
			element.sendKeys(Keys.ARROW_DOWN);
			break;
		default:

		}

	}

	@Override
	public void clickButton(WebElement buttonWebElement, MouseClick mouseClick) {

		if (buttonWebElement.isEnabled()) {
			mouseClick(buttonWebElement, mouseClick);
		}

	}

	// Method to set the Combo box value
	@Override
	public void setCheckbox(WebElement checkBoxWebElement, CheckedStatus status) {
		// if(Boolean.parseBoolean(status)!=checkBoxWebElement.isSelected()) {
		// checkBoxWebElement.click();
		// }

	}
}
