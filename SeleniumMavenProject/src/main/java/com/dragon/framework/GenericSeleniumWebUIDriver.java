/**
 * 
 */
package com.dragon.framework;

import org.openqa.selenium.WebElement;

import com.dragon.framework.Enums.CheckedStatus;
import com.dragon.framework.Enums.EndAction;
import com.dragon.framework.Enums.MouseClick;
import com.dragon.framework.Enums.WaitTime;
import com.dragon.pages.BasePage;

/**
 * @author M1030482
 *
 */
public abstract class GenericSeleniumWebUIDriver {

	public abstract void LaunchURL(String URL);

	public abstract void setTextBox(WebElement textBoxWebElement, String text, MouseClick mouseClick,
			EndAction endAction);

	public abstract void setTextBox(WebElement textBoxWebElement, String text, EndAction endAction);

	public abstract void mouseClick(WebElement elementToBeClicked, MouseClick mouseClick);

	public abstract void setCombobox(WebElement comboBoxwebElement, String text, MouseClick mouseClick,
			EndAction endAction);

	public abstract void clickButton(WebElement buttonWebElement, MouseClick mouseClick);

	public abstract <T> BasePage clickHyperlink(WebElement hyperlinkWebElement, MouseClick mouseClick);

	public abstract void Wait(WaitTime waitTime);

	public abstract void endAction(WebElement element, EndAction endAction);

	public abstract void setCheckbox(WebElement checkBoxwebElement, CheckedStatus status);

	//public abstract void checkBrokenLinks(WebElement pageWebElement);
}
