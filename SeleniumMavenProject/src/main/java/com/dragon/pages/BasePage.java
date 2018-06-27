/**
 * 
 */
package com.dragon.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.dragon.framework.ControlMap;
import com.dragon.framework.Enums.WaitTime;
import com.dragon.framework.GenericSeleniumWebUIDriver;
import com.dragon.framework.Enums.LocatorName;
import com.dragon.framework.Log;
import com.dragon.framework.SeleniumWebUIDriver;
import com.dragon.framework.Webdriver;

/**
 * @author M1030482 : Base Page contains the necessary Page Methods helpful for
 *         loading the page properties and consequently searching any control on
 *         the page and locating any web element
 *
 */
public class BasePage {
	// GenericSeleniumWebUIDriver instance to help perform various actions on Web
	// Controls
	protected GenericSeleniumWebUIDriver WebUIDriver = new SeleniumWebUIDriver();
	// List of control-maps associated with a page.
	HashMap<String, String> searchProperties;

	// Constructor

	public BasePage() {

		String className = this.getClass().getSimpleName();
		searchProperties = new ControlMap(className).searchProperties;
	}

	// Returns InvokerClass name
	protected String getInvokerClassName() {
		return this.getClass().getSimpleName();
	}

	/// <summary>
	/// Searches for the WebElement from the list of controls.
	/// </summary>
	/// <param name="name">Name of the ControlMap.</param>
	/// <returns></returns>
	protected WebElement searchControl(String webElementName) {
		try {
			ControlMap controlMap = new ControlMap(getInvokerClassName());
			LocatorName elementLocator = LocatorName.ID;
			String value = controlMap.getControlMap(webElementName).getValue();
			
			String[] locator = value.split(Pattern.quote("||"));
			String locatorName = locator[1];

			String locatorValue = locator[0];
			if (locatorName.contains( "ID"))
				elementLocator = LocatorName.ID;
			else if (locatorName == "XPATH")
				elementLocator = LocatorName.XPATH;
			else if (locatorName == "CSSLOCATOR")
				elementLocator = LocatorName.CSSLOCATOR;
			else if (locatorName == "LINKTEXT")
				elementLocator = LocatorName.LINKTEXT;
			else if (locatorName == "PARTIALLINKTEXT")
				elementLocator = LocatorName.PARTIALLINKTEXT;
			else if (locatorName == "NAME")
				elementLocator = LocatorName.NAME;
			else if (locatorName == "CLASSNAME")
				elementLocator = LocatorName.CLASSNAME;
			else if (locatorName == "TAGNAME")
				elementLocator = LocatorName.TAGNAME;

			WebElement element = null;

			for (int i = 0; i < 10; i++) {
				element = FindElement(elementLocator, locatorValue);
				if (element != null)
					break;
				else
					WebUIDriver.Wait(WaitTime.VeryShortWait);
			}

			return element;
		}

		catch (Exception e) {
			// throw new FrameworkException(String.format("{0},{1}",e.getMessage(), name));
		}
		return null;

	}

	// To Find WebElement
	private WebElement FindElement(LocatorName attributeName, String attributeValue) {
		WebElement element = null;
		Webdriver.driver.switchTo().defaultContent();
		try {

			if (attributeValue != null || !attributeValue.isEmpty()) {

				switch (attributeName) {

				case ID:
					element = Webdriver.driver.findElement(By.id(attributeValue));

					break;
				case XPATH:
					element = Webdriver.driver.findElement(By.xpath(attributeValue));
					break;
				case NAME:
					element = Webdriver.driver.findElement(By.name(attributeValue));
					break;
				case CLASSNAME:
					element = Webdriver.driver.findElement(By.className(attributeValue));
					break;
				case CSSLOCATOR:
					element = Webdriver.driver.findElement(By.cssSelector(attributeValue));
					break;
				case LINKTEXT:
					element = Webdriver.driver.findElement(By.linkText(attributeValue));
					break;
				case PARTIALLINKTEXT:
					element = Webdriver.driver.findElement(By.partialLinkText(attributeValue));
					break;
				case TAGNAME:
					element = Webdriver.driver.findElement(By.tagName(attributeValue));
					break;
				default:
					Log.info("No attribute is assigned to the control in the Property File with name:" + attributeName);
					break;
				}
				return element;
			} else {
				return element;
			}
		} catch (Exception e) {
			element = NestedIFrame(attributeName, attributeValue);
			return element;
		}
	}

	// To Find Web Element inside IFrames and Frames
	private WebElement NestedIFrame(LocatorName attributeName, String attributeValue) {
		WebElement element = null;
		try {
			List<WebElement> iframes = Webdriver.driver.findElements(By.tagName("iframe"));

			if (iframes.size() != 0) {
				for (WebElement frame : iframes) {
					if (frame.isDisplayed()) {
						Webdriver.driver.switchTo().frame(frame);
						element = SelectLocater(attributeName, attributeValue);
						if (element != null) {
							break;
						} else {
							element = NestedIFrame(attributeName, attributeValue);
							if (element != null) {
								break;
							} else {
								element = FramesOnly(attributeName, attributeValue);
								if (element != null) {
									break;
								}
							}
						}
						Webdriver.driver.switchTo().parentFrame();
					}
				}
			}
			if (element == null) {
				element = FramesOnly(attributeName, attributeValue);
			}
		} catch (Exception e) {
		}
		return element;

	}

	/// To Find Web Element inside Frames only
	private WebElement FramesOnly(LocatorName attributeName, String attributeValue) {
		WebElement element = null;
		try {
			List<WebElement> frames = Webdriver.driver.findElements(By.tagName("frame"));

			if (frames.size() != 0) {
				for (WebElement frame : frames) {
					if (frame.isDisplayed()) {
						Webdriver.driver.switchTo().frame(frame);
						element = SelectLocater(attributeName, attributeValue);
						if (element != null) {
							break;
						} else {
							element = FramesOnly(attributeName, attributeValue);
							if (element != null) {
								break;
							} else {
								element = NestedIFrame(attributeName, attributeValue);
							}
						}
						Webdriver.driver.switchTo().parentFrame();
					}
				}
			}

		} catch (Exception e) {
		}

		return element;
	}

	// To Find Web Element inside Frames only
	private static void SwitchTOWindow(String windowTitle) {

		for (String defwindow : Webdriver.driver.getWindowHandles()) {
			try {
				Webdriver.driver.switchTo().window(defwindow);
				if (Webdriver.driver.getTitle().equals(windowTitle)
						|| Webdriver.driver.getTitle().contains(windowTitle)) {

					Webdriver.driver.manage().window().maximize();
					Log.info("Page with window title " + windowTitle
							+ "present on the screen and current driver Attached to window");
					break;
				}
			} catch (Exception e) {

				// throw new FrameworkException(String.format("{0}", e.getMessage()));
			}

		}
		// if (Webdriver.driver.Title != WindowTitle)
		// {
		// Thread.Sleep(5000);
		// foreach (string defwindow in Webdriver.driver.WindowHandles)
		// {
		// try
		// {
		// Webdriver.driver.SwitchTo().Window(defwindow);
		// if (Webdriver.driver.Title == WindowTitle)
		// {

		// // Webdriver.driver.SwitchTo().Window(defwindow);
		// Logging.MessageLogger.Success("Page with window title " + WindowTitle +
		// "present on the screen and current driver Attached to window");
		// break;
		// }
		// }
		// catch (Exception e)
		// {

		// throw new FrameworkException(string.Format("{0}", e.Message));
		// }

		// }
		// if (Webdriver.driver.Title != WindowTitle)
		// {
		// throw new FrameworkException(string.Format("Could not find window with given
		// window title '{0}' in properties.", WindowTitle));
		// }

		// }

		// Logging.MessageLogger.Failure("Page with window title " + WindowTitle + "not
		// present on the screen ");
	}

	private WebElement SelectLocater(LocatorName attributeName, String attributeValue) {
		WebElement element = null;
		try {
			switch (attributeName) {

			case ID:
				element = Webdriver.driver.findElement(By.id(attributeValue));
				break;
			case XPATH:
				element = Webdriver.driver.findElement(By.xpath(attributeValue));
				break;
			case NAME:
				element = Webdriver.driver.findElement(By.name(attributeValue));
				break;
			case CLASSNAME:
				element = Webdriver.driver.findElement(By.className(attributeValue));
				break;
			case CSSLOCATOR:
				element = Webdriver.driver.findElement(By.cssSelector(attributeValue));
				break;
			case LINKTEXT:
				element = Webdriver.driver.findElement(By.linkText(attributeValue));
				break;
			case PARTIALLINKTEXT:
				element = Webdriver.driver.findElement(By.partialLinkText(attributeValue));
				break;
			case TAGNAME:
				element = Webdriver.driver.findElement(By.tagName(attributeValue));
				break;
			default:
				Log.info("No attribute is assigned to the control in the PropertiesFile entry");
				break;
			}
		} catch (Exception e) {

		}

		return element;
	}
	// <summary>
	// Get Search Properties of the ControlMap from the properties file.
	// </summary>
	// <param name="name">Name of the WebElement in properties File.</param>
	// <returns>List of propertyKey and propertyValue. list[0] is PropertyKey and
	// list[1] is PropertyValue</returns>
	// protected List<String> GetSearchPropertiesOfControl(String propertyName)
	// {
	// try
	// {
	// ControlMap controlMap = null;
	// String propertyKey = null;
	// String propertyValue = null;
	// Dictionary<String, String> controlMapSearchProperties = new
	// Dictionary<String, String>();
	// List<String> lstSearchProperties = new List<String>();
	//
	// PropertyInfo property = this.GetType().GetProperty(propertyName,
	// BindingFlags.NonPublic | BindingFlags.Instance |
	// BindingFlags.FlattenHierarchy | BindingFlags.Public);
	//
	// //If property is not found in the current type, search for it in parent
	// classes.
	// if (property == null)
	// {
	// List<Type> baseTypes = new List<Type>();
	//
	// baseTypes.AddRange(this.GetType().GetBaseTypes());
	//
	// if (baseTypes.Count > 0)
	// {
	// for (int i = 0; i < baseTypes.Count; i++)
	// {
	// property = baseTypes[i].GetProperty(propertyName, BindingFlags.NonPublic |
	// BindingFlags.Instance | BindingFlags.FlattenHierarchy | BindingFlags.Public);
	// if (property != null)
	// break;
	// }
	// }
	// }
	//
	// if (property != null &&
	// controlMaps.ContainsKey(property.DeclaringType.FullName))
	// controlMap = controlMaps[property.DeclaringType.FullName]
	// .FirstOrDefault(c => c.Name.Equals(propertyName,
	// StringComparison.InvariantCultureIgnoreCase));
	// else //Property has been declared in base 'Page' class or as an enum.
	// controlMap = controlMaps[this.GetType().FullName]
	// .FirstOrDefault(c => c.Name.Equals(propertyName,
	// StringComparison.InvariantCultureIgnoreCase));
	//
	// if (controlMap.searchProperties.size()==1)
	// {
	// controlMapSearchProperties = controlMap.searchProperties;
	//
	// propertyKey = new List<string>(controlMapSearchProperties.Keys)[0];
	// propertyValue = controlMapSearchProperties[propertyKey];
	// lstSearchProperties.Add(propertyKey);
	// lstSearchProperties.Add(propertyValue);
	// }
	//
	// return lstSearchProperties;
	// }
	//
	// catch (Exception e)
	// {
	// throw new FrameworkException(String.format("{0},{1}", e.getMessage(),
	// propertyName));
	//
	// }
	//
	// }

}
