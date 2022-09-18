package SFDC.com;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


import SFDC_Pages.Loginpage;
import SFDC_Pages.UserMenu;
import io.github.bonigarcia.wdm.WebDriverManager;
import reusable.BaseTest;
import reusable.dataUtils;


public class LoginTest extends BaseTest {
	
	//private static Logger logger = LogManager.getLogger(LoginTest.class.getName());
	public static Logger logger = LogManager.getLogger(LoginTest.class.getName());
	public static WebDriver driver;
	protected static String browsername;
	
	/*
	 * public LoginTest(WebDriver driver, String B_name) { super(driver);
	 * this.browsername= B_name; // TODO Auto-generated constructor stub }
	 */

	@BeforeClass
	public void driverIni() {
		//driver = getBrowser("chrome");
		// logger.info("driver loaded successfully");
		// implicitwait
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//LoginTest test = new LoginTest(driver, "chrome");
		driver = getBrowser("chrome");
	}

	@BeforeMethod
	public void Call_extent() {
		extentReport();
	}

	@AfterMethod
	public void end_extent() {
		extentEnd();
	}

	@AfterClass
	public void close() {
		driver.quit();
	}

	@Test(priority = 1)
	public void getUrl() throws IOException {
		// login is a file name and URL is a key in property file.
		test = extent.createTest("tc1");
		driver.get(dataUtils.getProperties("login", "URL"));
		logger.info("Website loaded successfully");
		logger.debug("It is loaded");
		test.info("Test case passed");

	}

	@Test(priority = 2)
	public void login() throws IOException {
		Loginpage lp = new Loginpage(driver);
		test = extent.createTest("tc2");
		// BaseTest.waitForElement(driver,lp.username);
		lp.enter_username(lp.user_name, dataUtils.getProperties("login", "username"));
		lp.user_name.isDisplayed();
		lp.Password.clear();
		lp.loginclick();
		lp.getText(lp.error_msg);
		assertEquals(lp.getText(lp.error_msg), dataUtils.getProperties("login", "login.emptypasssword"));
		test.info("Empty password error message verified");
		// capturescreenshot
		// b.captureScreenshot(driver);

	}

	@Test(priority = 3)
	public void title() throws IOException {
		test = extent.createTest("tc3");
		Loginpage lp = new Loginpage(driver);
		UserMenu Up = new UserMenu(driver);
		lp.enter_username(lp.user_name, dataUtils.getProperties("login", "username"));
		lp.password(dataUtils.getProperties("login", "password"));
		lp.loginclick();
		String t1 = lp.getTitle(driver);
		assertEquals(t1, dataUtils.getProperties("login", "title1"));
		Up.UserMenuSelection("Logout", Up.usermenu, Up.usermenuItemsList);
		logger.info("User LogOut successfully ");
		test.info("SalesForce dashboard page is displayed properly");

	}

	@Test(priority = 4)
	public void checkRememberme() throws IOException, InterruptedException {
		test = extent.createTest("tc4");
		Loginpage lp = new Loginpage(driver);
		UserMenu Up = new UserMenu(driver);
		BaseTest.waitForElement(driver,lp.user_name);
		lp.enter_username(lp.user_name, dataUtils.getProperties("login", "username"));
		lp.password(dataUtils.getProperties("login", "password"));
		lp.remembermeClick();
		lp.loginclick();
		Up.UserMenuSelection("Logout", Up.usermenu, Up.usermenuItemsList);
		Thread.sleep(2000);
		String t2 = lp.getTitle(driver);
		assertEquals(t2,dataUtils.getProperties("login", "title2"));
		assertEquals(lp.remember_name(),dataUtils.getProperties("login", "username"));
		lp.remember_me_uname2.isDisplayed();
		test.info("remember functionality working properly");

	}
	
	@Test(priority = 5)
	public void forgot_password() throws IOException
	{
		test = extent.createTest("tc5");
		BaseTest  bp = new BaseTest();
		Loginpage lp = new Loginpage(driver);
		lp.click(lp.ForgotYourPassword);
		lp.getTitle(driver);
		assertEquals(lp.getTitle(driver), dataUtils.getProperties("login", "forgot_password_titile"));
		lp.enter_username(lp.Forgotpsw_username,dataUtils.getProperties("login", "username"));
		lp.click(lp.forget_continue);
		bp.waitForElement(driver, lp.password_Reset);
		String text = lp.getText(lp.password_Reset);
	//	assertEquals(text, dataUtils.getProperties("login", "password_reset_text"));
		test.info("reset password working properly");
		logger.info("Reset works properly");

		
	}
	
}
