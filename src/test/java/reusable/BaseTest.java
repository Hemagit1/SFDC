package reusable;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;
	public static Logger logger = LogManager.getLogger(BaseTest.class.getName());
	public static ExtentReports extent;
	public static ExtentTest test;

	// Invoke Browser using below method
	public static WebDriver getBrowser(String Browser_name) {
		String Browser = Browser_name.toLowerCase();

		switch (Browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "safari":
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			break;

		default:
			driver = null;
		}
		return driver;
	}

	// Create extent report
	@BeforeSuite
	public void extentReport() {
		String dateFormat = new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(new Date());
		String reportPath = System.getProperty("user.dir") + "\\src\\test\\resources\\" + dateFormat + "_sfdc.html";

		logger.info("************* Report extent configuration ********************");
		extent = new ExtentReports();
		// logger.info(result.toString() + ": Report intitilized");
		ExtentHtmlReporter htmlReport = new ExtentHtmlReporter(reportPath);
		extent.attachReporter(htmlReport);
		logger.info("************* Report extent configuration: Done ********************");

	}

	@AfterSuite
	public void extentEnd() {
		extent.flush();
	}

	public static boolean waitForElement(WebDriver driver, WebElement element) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		}

		catch (Exception e) {
			e.getMessage();
			return false;
		}

	}
	
	public static String captureScreenshot(WebDriver driver) throws IOException {
		
		String dateFormat = new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(new Date());
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		String destinationpath = System.getProperty("user.dir")+"\\src\\test\\resources\\"+dateFormat+"_sfdc.PNG";
		
		//get screenshot
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
		
		//create path for file 
		File destFile = new File(destinationpath);
		
		//copy file 
		FileUtils.copyFile(sourceFile, destFile);
		
		logger.info("Screenshot captured");
		return destinationpath;
	}
	
	
	
	
	
}
