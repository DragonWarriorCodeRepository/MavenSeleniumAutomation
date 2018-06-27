/**
 * 
 */
package com.dragon.utilities;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.dragon.framework.Constants;
import com.dragon.framework.Webdriver;
import org.apache.commons.io.FileUtils;

/**
 * @author TheDragonWarrior
 *
 */
public class Utilities {

	public static void TakeScreenShot(String screenShotName) {

		try {
			String imageFilePath = Constants.Path_ScreenShot;

			TakesScreenshot scrShot = ((TakesScreenshot) Webdriver.driver);

			File sourceFile = scrShot.getScreenshotAs(OutputType.FILE);

			File destinationFile = new File(imageFilePath);

			// Copy file at destination

			FileUtils.copyFile(sourceFile, destinationFile);
		} catch (IOException e) {
			System.out.println("File Error : " + e.getMessage());
		}

	}
	
	
	
	
	

}
