package SFDC_Pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SFDC.com.LoginTest;

public class UserMenu
{
	
	WebDriver driver;
	private static Logger logger = LogManager.getLogger(UserMenu.class.getName());

	
	public UserMenu(WebDriver driver)
	{
		  PageFactory.initElements(driver, this);

	}

@FindBy(id= "userNavButton")
public WebElement usermenu;

@FindBy(xpath= "//div[@id='userNavMenu']//a")
public WebElement usermenuItems;

@FindBy(xpath= "//div[@id='userNav-menuItems']/a")
public  List<WebElement> usermenuItemsList;

@FindBy (id= "userNavButton")
public WebElement usermenubtn;

@FindBy (xpath = "//img[@title='Edit Profile']")
public WebElement editbtn;

@FindBy (id = "contactInfo")
public WebElement editWindow;

@FindBy (xpath = "//ul[@class='zen-tabControl']//li")
public List<WebElement> editWindowOptions;



public void UserMenuOptions(WebDriver driver)
{
	 String[] names = {"My profile", "My Settings", "Developer Console","Logout", "Switch to Lightning Experience"};
	 List<WebElement> actual_name = driver.findElements(By.xpath("//div[@id= 'userNav-menuItems']//a"));
	
	 for (int i=0; i<=names.length-1; i++)
	 {
		 String getName = actual_name.get(i).getText();
		 if(getName.equals(names[i]))
		 {
			 logger.info("Usermenu items is " +getName);
		 }
	 }	
	 
	 
		/*
		 * for(WebElement i : usermenuItemsList ) { i.getText(); }
		 */
}


public void UserMenuSelection(String UserMenuName, WebElement usermenu, List<WebElement> usermenuItems)
{
	try {
			usermenu.click();
			
			
			for(int i=0; i<usermenuItems.size(); i++)
			{
			 String find_name = usermenuItems.get(i).getText();
			 if(find_name.equals(UserMenuName))
			 {
				 usermenuItems.get(i).click();
				// logger.info("User moved to the page" +find_name );
			 }
		 }
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		//logger.fatal("UserMenu item is not found");

	}	
	
}


}
