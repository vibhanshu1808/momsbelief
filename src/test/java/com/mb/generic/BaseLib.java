package com.mb.generic;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import 
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseLib 
{
public WebDriver driver; //final Driver
@BeforeMethod
@Parameters("browser")
public void setUp(String browserName)
{
	if(browserName.equalsIgnoreCase("firefox"))
	{
		driver = new FirefoxDriver();
		Reporter.log("Firefox launched",true);
	}
	else if(browserName.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver","./exefiles/chromedriver.exe");
				driver = new ChromeDriver();
				Reporter.log("Chrome launched",true);
			}
	driver.manage().window().maximize();
	WaitStatementLib.iWaitForSecs(driver, 20);
	driver.get(GenericLib.getValue("testurl"));
	Reporter.log("Navigate to url", true);
}
@AfterMethod
public void tearDown(ITestResult result)
{
	if(result.isSuccess())
	{
		System.out.println("Result Paas");
	}
	else
	{
		String fileName = result.getMethod().getMethodName();
		ScreenShotLib slib= new ScreenShotLib();
		slib.takeScreenShot(driver, fileName);
		Reporter.log("Screenshot has been taken",true);
	}
	driver.close();
	Reporter.log("Browser closed",true);
}
}

