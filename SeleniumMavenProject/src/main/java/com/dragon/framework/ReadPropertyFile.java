/**
 * 
 */
package com.dragon.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.dragon.pages.BasePage;

/**
 * @author M1030482
 *
 */
public class ReadPropertyFile {

	// Class Level Variables
	public static Properties properties;
	private File file;
	private FileInputStream fileInputStream;
	private String currentDirectory = System.getProperty("user.dir");
	String locatorFilePath = Constants.Page_LocatorFilePath + BasePage.class.getSimpleName() + ".properties";

	// Constructor to initialize and Load the config file
	public ReadPropertyFile(String filePath) {

		try {
			filePath = Constants.Page_LocatorFilePath + filePath + ".properties";
			properties = new Properties();
			file = new File(filePath);
			fileInputStream = new FileInputStream(file);
			properties.load(fileInputStream);
		} catch (IOException e) {
			System.out.println("Not able to read configuration File" + e.getMessage());
		}
	}

	// Default Constructor to initialize and Load the configuration files
	public ReadPropertyFile() {

		try {
			//String filePath = currentDirectory + "\\src\\main\\test\\com\\dragon\\test\\config.properties";
			String filePath = currentDirectory + "\\src\\test\\java\\com\\dragon\\test\\config.properties";
			// filePath = Constants.Page_LocatorFilePath + filePath + ".properties";
			properties = new Properties();
			file = new File(filePath);
			fileInputStream = new FileInputStream(file);
			properties.load(fileInputStream);
			Log.info("");
		} catch (IOException e) {
			System.out.println("Not able to read configuration File" + e.getMessage());
		}
	}

	// Method to get the URL From config File
	public final static String getURL() {
		return properties.getProperty("URL");
	}

	// Method to get the Browser From config File
	public final String getBrowserName() {
		String BrowserName = properties.getProperty("Browser");
		return BrowserName;

	}

	// Method to get the Application Name From config File
	public final static String getApplicationName() {
		return properties.getProperty("Application");
	}

	// Method to get the toolsQA Test Data File Path From config File
	public final static String getTestDataFilePath() {
		return properties.getProperty("toolsQA.testDataFile");
	}

	// Method to get the chromeDriver Location From config File
	public final String getChromeDriverLocation() {
		String driverLocation = currentDirectory + properties.getProperty("chromeDriverLocation");
		return driverLocation;
	}

	// Method to get the IE Driver Location From config File
	public final String getIEDriverLocation() {
		String driverLocation = currentDirectory + properties.getProperty("ieDriverLocation");
		return driverLocation;
	}

	// Method to get the FireFox Driver Location From config File
	public final String getGeckoDriverLocation() {
		String driverLocation = currentDirectory + properties.getProperty("geckoDriverLocation");
		return driverLocation;
	}

}
