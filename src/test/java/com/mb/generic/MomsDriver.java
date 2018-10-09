package com.mb.generic;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MomsDriver implements WebDriver
{

	public  WebDriver driver;
	// static log4jClass log = new log4jClass();

/*	static {
		try {
			MomsDriver.invokeBrowser("firefox");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/

	public void  invokeBrowser(String browser) throws IOException {

		switch (browser) {

		case "firefox":
			System.out.println("User Directory is: "+System.getProperty("user.dir"));
			//File pathBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox");
			//FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);   
			//DesiredCapabilities desired = DesiredCapabilities.firefox();
			DesiredCapabilities cap = new DesiredCapabilities();
			FirefoxOptions options = new FirefoxOptions();
			//options.setHeadless(true);
			//options.setLogLevel(FirefoxDriverLogLevel.TRACE);

			//cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
			cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			// Provide generic comments not the Static paths @Vibhanshu
			String path=System.getProperty("user.dir")+"\\exefiles\\geckodriver.exe";
			System.out.println(path);
			System.setProperty("webdriver.gecko.driver",path);

			driver = new FirefoxDriver(options);
			driver.get(ReadConfig.getInstance().getApplicationUrl());
			// log.info("Browser Invoked");
			break;

		case "htmlunit":
			DesiredCapabilities capHtml = DesiredCapabilities.htmlUnit();
			capHtml.acceptInsecureCerts();
			capHtml.setJavascriptEnabled(true);
			/*
			 * System.setProperty("webdriver.gecko.driver",
			 * ReadConfig.getInstance().getDriverPath().toString() + "geckodriver.exe");
			 */
			driver = new HtmlUnitDriver(capHtml);
			driver.get(ReadConfig.getInstance().getApplicationUrl());
			// log.info("Browser Invoked");
			break;

		case "chrome":
			DesiredCapabilities capChrome = DesiredCapabilities.chrome();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("headless");
			capChrome.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			capChrome.setJavascriptEnabled(true);
			capChrome.acceptInsecureCerts();
			//System.setProperty("webdriver.chrome.driver",
			//		ReadConfig.getInstance().getDriverPath().toString() + "chromedriver");
			System.setProperty("webdriver.chrome.driver","./exefiles/chromedriver.exe");
			driver = new ChromeDriver();
			//driver = new ChromeDriver(capChrome);
			//driver.get(ReadConfig.getInstance().getApplicationUrl());
			driver.manage().window().setSize(new Dimension(1440, 900));
			break;

		case "ie":
			DesiredCapabilities capIE = new DesiredCapabilities();
			capIE.setPlatform(Platform.WINDOWS);
			capIE.acceptInsecureCerts();
			capIE.setJavascriptEnabled(true);
			capIE.setCapability("ignoreZoomSetting", true);
			capIE.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

			capIE.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
			System.setProperty("webdriver.ie.driver",
					ReadConfig.getInstance().getDriverPath().toString() + "IEDriverServer.exe");
			driver = new InternetExplorerDriver(capIE);
			driver.get(ReadConfig.getInstance().getApplicationUrl());

			/*
			 * case "edge": DesiredCapabilities capEdge = DesiredCapabilities.edge();
			 * capEdge.setPlatform(Platform.WIN10); capEdge.acceptInsecureCerts();
			 * capEdge.setJavascriptEnabled(true); EdgeOptions options = new EdgeOptions();
			 * options.setPageLoadStrategy("eager");
			 * System.setProperty("webdriver.edge.driver",
			 * ReadConfig.getInstance().getDriverPath().toString() +
			 * "MicrosoftWebDriver.exe"); driver = new EdgeDriver(options);
			 */

			
			  case "phantomjs": 
				 DesiredCapabilities capPhantom =new DesiredCapabilities(); 
				 ArrayList<String> cliArgsCap = new ArrayList<String>(); 
				 cliArgsCap.add("--web-security=false");
				// cliArgsCap.add("--proxy=10.5.1.175:1024");



			  cliArgsCap.add("--ssl-protocol=any");
			  cliArgsCap.add("--ignore-ssl-errors=true");
			  cliArgsCap.add("--webdriver-loglevel=NONE");
			  cliArgsCap.add("--load-images=true"); //
			  capPhantom.setBrowserName("PhantomJs");
			  capPhantom.setCapability("screen-resolution", "1280x1024");
			  capPhantom.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			 // capPhantom.setCapability("trustAllSSLCertificates", true);

			//  capPhantom.setCapability("proxy", "127.0.0.1:1024");
			  capPhantom.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,cliArgsCap);
			 capPhantom.setPlatform(Platform.LINUX);
			  capPhantom.setJavascriptEnabled(true);
			  capPhantom.setCapability("takesScreenshot", true);
			  capPhantom.setCapability("phantomjs.binary.path",ReadConfig.getInstance().getDriverPath().toString() + "phantomjs.exe");
			  driver = new PhantomJSDriver(capPhantom);

			  System.out.println("Driver value is : "+driver);
			  driver.manage().window().setSize(new Dimension(1920, 1080)); //Size is type in System.Drawing"

			  //driver.manage().window().maximize();
			  driver.get(ReadConfig.getInstance().getApplicationUrl());
			 
		}

		
	}

	public void close() {
		driver.close();

	}

	public WebDriver  getDriver() {
		return driver;
	}

	public  void acceptAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}

	public  void dismissAlert() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().dismiss();
	}

	public  void waitForPageLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}

	public  void waitForElementToBeClickable(WebElement ele, Long... i) {

		if (i.length >= 1) {
			WebDriverWait wait = new WebDriverWait(driver, i[0]);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
		} else {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
		}
	}


	public  void visibilityOfListLocated(List<WebElement> ele) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfAllElements(ele));
	}

	public  void WaitTillElementIsPresent(final WebElement ele) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return ele;
			}
		});
	}

	public  void selectFromDropDownByIndex(WebElement ele, int index) {

		new Select(ele).selectByIndex(index);
	}

	public  void selectFromDropDownByVisibleText(WebElement ele, String value) {
		waitForElementToBeClickable(ele);
		new Select(ele).selectByVisibleText(value);
	}

	public  void uploadFile(String str) throws AWTException {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		StringSelection selection = new StringSelection(str);
		java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, null);

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robot.keyPress(KeyEvent.VK_ENTER);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public  void waitForElementToDisappear(By by) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	public  void hoverOnElement(WebElement ele) {
		Actions action = new Actions(driver);
		action.moveToElement(ele);
		action.build().perform();
	}

	public  void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	public  void switchToFrame(WebElement frame) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
	}

	public  void switchToFrameBasedOnFrameName(String frameName) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
	}

	public  void waitForElementToBeEnable(By by) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public  void executeScript(WebElement ele) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
	}

	public WebElement findElement(By arg0) {
		return null;
	}

	public  void switchToWindow(String strWindowName) {

		driver.switchTo().window(strWindowName);
	}

	public  void switchToDefaultWindow() {
		driver.switchTo().defaultContent();
	}

	public List<WebElement> findElements(By arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void get(String arg0) {
		// TODO Auto-generated method stub

	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getPageSource() {

		return driver.getPageSource();
	}

	public String getTitle() {

		return driver.getTitle();
	}

	public String getWindowHandle() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<String> getWindowHandles() {

		return driver.getWindowHandles();
	}

	public Options manage() {
		// TODO Auto-generated method stub
		return null;
	}

	public Navigation navigate() {

		return null;
	}

	public void quit() {
		driver.quit();
	}

	public TargetLocator switchTo() {

		return null;
	}

	

}
