package SFDC_Pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SFDC.com.LoginTest;

public class UserMenu {
	
	WebDriver driver;
	private static Logger logger = LogManager.getLogger(UserMenu.class.getName());

	
	public UserMenu(WebDriver driver)
	{
		  PageFactory.initElements(driver, this);

	}

@FindBy(id= "userNavButton")
public static WebElement usermenu;

@FindBy(xpath= "//div[@id='userNavMenu']//a")
public WebElement usermenuItems;

@FindBy(xpath= "//div[@id='userNav-menuItems']//a")
public static List<WebElement> usermenuItemsList;


public  void UserMenuSelection(String UserMenuName)
{
	try {
			usermenu.click();
			for(WebElement link : usermenuItemsList)
			{
			 String find_name = link.getText();
			 if(find_name.equals(UserMenuName))
			 {
				 link.click();
			 }
		 }
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		logger.fatal("UserMenu item is not found");

	}	
	
}


}
