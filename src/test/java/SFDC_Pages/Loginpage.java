package SFDC_Pages;

import java.io.FilterInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusable.BaseTest;
import reusable.dataUtils;

public class Loginpage extends BaseTest {
	
	public static WebDriver driver;
	  public Loginpage(WebDriver driver)
	  {
		  PageFactory.initElements(driver, this);
	  }
	 	
@FindBy(id= "username")
public WebElement user_name;

@FindBy (name = "pw")
public WebElement Password;

@FindBy (name = "Login")
public WebElement login_button;

@FindBy (name= "rememberUn")
public WebElement rememberme;

@FindBy (linkText = "Forgot Your Password?")
public WebElement ForgotYourPassword;

@FindBy (id= "error")
public WebElement error_msg;


@FindBy(id="idcard")
public WebElement remember_me_uname;

@FindBy(id="idcard-identity")
public WebElement remember_me_uname2;

public void enter_username(String text)
{
	user_name.sendKeys(text);
}

public void password(String text)
{
	Password.sendKeys(text);
}

public void loginclick()
{
	login_button.click();
}

public String errorText()
{
	return error_msg.getText();
}
public void remembermeClick()
{
	rememberme.click();
}

public String getTitle(WebDriver driver)
{
	 String title = driver.getTitle();
	 return title;
}

public String remember_name()
{
	String Actual_name = remember_me_uname.getAttribute("aria-label");
	return Actual_name;
}
}




