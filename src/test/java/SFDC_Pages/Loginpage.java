package SFDC_Pages;

import java.io.FilterInputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SFDC.com.LoginTest;
import reusable.BaseTest;
import reusable.dataUtils;

public class Loginpage {

	public static WebDriver driver;
	public static Logger logger = LogManager.getLogger(Loginpage.class.getName());


	public Loginpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "username")
	public WebElement user_name;

	@FindBy(name = "pw")
	public WebElement Password;

	@FindBy(name = "Login")
	public WebElement login_button;

	@FindBy(name = "rememberUn")
	public WebElement rememberme;

	@FindBy(linkText = "Forgot Your Password?")
	public WebElement ForgotYourPassword;

	@FindBy(id = "error")
	public WebElement error_msg;

	@FindBy(id = "idcard")
	public WebElement remember_me_uname;

	@FindBy(id = "idcard-identity")
	public WebElement remember_me_uname2;

	@FindBy(id = "un")
	public WebElement Forgotpsw_username;

	@FindBy(xpath = "//div[@id='forgotPassForm']/div/p[1]")
	public WebElement password_Reset;

	@FindBy(id = "continue")
	public WebElement forget_continue;

	public void enter_username(WebElement element, String text) {
		element.sendKeys(text);
		logger.info(text + "element entered properly");
	}

	public void password(String text) {
		Password.sendKeys(text);
	}

	public void loginclick() {
		login_button.click();
	}

	public void click(WebElement element) {
		element.click();
	}

	public String getText(WebElement element) {
		return element.getText();
	}

	public void remembermeClick() {
		rememberme.click();
	}

	public static String getTitle(WebDriver driver) {
		String title = driver.getTitle();
		return title;
	}

	public String remember_name() {
		String Actual_name = remember_me_uname.getAttribute("aria-label");
		return Actual_name;
	}

}
