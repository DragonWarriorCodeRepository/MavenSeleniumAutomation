/**
 * 
 */
package com.dragon.framework;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author M1030482
 *
 */
public class ControlMap {
	// Class level Variables
	public HashMap<String, String> searchProperties;
	private ReadPropertyFile propertyFileReader;
	private String locatorKey;
	private String locatorValue;

	// Constructor
	public ControlMap(String invokerClassName) {

		propertyFileReader = new ReadPropertyFile(invokerClassName);
		searchProperties = new HashMap<String, String>();
		Enumeration<Object> propertyKeyValues = propertyFileReader.properties.keys();

		while (propertyKeyValues.hasMoreElements()) {
			locatorKey = (String) propertyKeyValues.nextElement();
			locatorValue = propertyFileReader.properties.getProperty(locatorKey);
			searchProperties.put(locatorKey, locatorValue);
		}

	}

	public ControlMap() {

		propertyFileReader = new ReadPropertyFile();
		searchProperties = new HashMap<String, String>();
		Enumeration<Object> propertyKeyValues = propertyFileReader.properties.keys();

		while (propertyKeyValues.hasMoreElements()) {
			locatorKey = (String) propertyKeyValues.nextElement();
			locatorValue = propertyFileReader.properties.getProperty(locatorKey);
			searchProperties.put(locatorKey, locatorValue);
		}

	}

	public Map.Entry<String, String> getControlMap(String webElementName) {

		Map.Entry<String, String> locatorControlProperty = null;
		try {

			for (Map.Entry<String, String> entry : searchProperties.entrySet()) {
				String entryWebElementName = entry.getKey();
				if (entryWebElementName.contains(webElementName)) {
					locatorControlProperty = entry;
					break;
				}
			}
		} catch (Exception e) {
			Log.error("Locator Properties Could not be assigned to ControlMap");
		}
		return locatorControlProperty;
	}
	
}
