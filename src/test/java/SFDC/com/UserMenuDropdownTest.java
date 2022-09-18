package SFDC.com;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.lang.System.Logger;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import SFDC_Pages.Loginpage;
import SFDC_Pages.UserMenu;
import reusable.BaseTest;
import reusable.dataUtils;

public class UserMenuDropdownTest extends BaseTest {

	public static WebDriver driver;
	private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(UserMenuDropdownTest.class.getName());
	@BeforeClass
	public void Call_extent() {
		extentReport();
	}

	@BeforeMethod
	public void DriveInit() throws IOException {
		BaseTest b1 = new BaseTest();
		driver = getBrowser("chrome");
		logger.info("Browser loaded ");
	}

	@Test(priority = 1)
	public void UserMenuVerification() throws IOException {
		dataUtils a1 = new dataUtils();
		Loginpage lp = new Loginpage(driver);
		UserMenu um = new UserMenu(driver);
		driver.get(a1.getProperties("login", "URL"));
		lp.enter_username(lp.user_name, dataUtils.getProperties("login", "username"));
		lp.enter_username(lp.Password, dataUtils.getProperties("login", "password"));
		lp.loginclick();
		um.usermenubtn.isDisplayed();
		um.usermenubtn.click();
		um.UserMenuOptions(driver);
	}

	@AfterMethod
	public void end_extent() {
		extentEnd();

	}

	@AfterClass
	public void close() {
		driver.quit();
	}

}
