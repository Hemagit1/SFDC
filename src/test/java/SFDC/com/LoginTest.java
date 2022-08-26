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

	private static Logger logger = LogManager.getLogger(LoginTest.class.getName());

	@BeforeClass
	public void driverIni() {
		driver = getBrowser("chrome");
		// logger.info("driver loaded successfully");

		// implicitwait
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
		test.info("Test case passed");

	}

	@Test(priority = 2)
	public void login() throws IOException {
		Loginpage lp = new Loginpage(driver);
		test = extent.createTest("tc2");
		// BaseTest.waitForElement(driver,lp.username);
		lp.enter_username(dataUtils.getProperties("login", "username"));
		lp.user_name.isDisplayed();
		lp.Password.clear();
		lp.loginclick();
		lp.errorText();
		assertEquals(lp.errorText(), dataUtils.getProperties("login", "login.emptypasssword"));
		test.info("Empty password error message verified");
		// capturescreenshot
		// b.captureScreenshot(driver);

	}

	@Test(priority = 3)
	public void title() throws IOException {
		Loginpage lp = new Loginpage(driver);
		UserMenu Up = new UserMenu(driver);
		lp.enter_username(dataUtils.getProperties("login", "username"));
		lp.password(dataUtils.getProperties("login", "password"));
		lp.loginclick();
		lp.getTitle(driver);
		assertEquals(lp.getTitle(driver), dataUtils.getProperties("login", "title1"));
		Up.UserMenuSelection("Logout");

	}

	@Test(priority = 4)
	public void checkRememberme() throws IOException, InterruptedException {
		Loginpage lp = new Loginpage(driver);
		UserMenu Up = new UserMenu(driver);
		BaseTest.waitForElement(driver,lp.user_name);
		lp.enter_username(dataUtils.getProperties("login", "username"));
		lp.password(dataUtils.getProperties("login", "password"));
		lp.remembermeClick();
		lp.loginclick();
		Up.UserMenuSelection("Logout");
		Thread.sleep(2000);
		assertEquals(lp.getTitle(driver),dataUtils.getProperties("login", "title2"));
		assertEquals(lp.remember_name(),dataUtils.getProperties("login", "username"));
		lp.remember_me_uname2.isDisplayed();
	}
	

}
