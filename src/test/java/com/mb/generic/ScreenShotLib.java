package com.mb.generic;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ScreenShotLib 
{
	/**
	 * @description take screenshots(.png) into screenshot folder
	 * @param driver
	 * @param fileName
	 */
public void takeScreenShot(WebDriver driver,String fileName)
{
	EventFiringWebDriver efw = new EventFiringWebDriver(driver);
	File srcFile = efw.getScreenshotAs(OutputType.FILE);
	File destFile = new File("./screenshot/"+fileName+".png");
	try {
		FileUtils.copyFile(srcFile,destFile);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}