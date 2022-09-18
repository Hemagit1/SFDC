package SFDC.com;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import SFDC_Pages.Loginpage;
import SFDC_Pages.UserMenu;
import SFDC_Pages.profilepage;
import reusable.BaseTest;
import reusable.dataUtils;

public class profileTest extends BaseTest {

	
	
	@BeforeClass
	public void driverInt()
	{
		driver = getBrowser("Chrome");
	}
	
	@Test(priority =1)
	public void getURL() throws IOException
	{
		dataUtils d1 = new dataUtils();
		Loginpage l1 = new Loginpage(driver);
		driver.get(d1.getProperties("login", "URL"));	
		driver.manage().window().maximize();
		l1.enter_username(l1.user_name, d1.getProperties("login", "username"));
		l1.enter_username(l1.Password, d1.getProperties("login", "password"));
		l1.click(l1.login_button);
	}
	
	@Test (priority =2)
	public void Usermenu_edit()
	{
		UserMenu um = new UserMenu(driver);
		profilepage p = new profilepage(driver);
		um.UserMenuSelection("My Profile", um.usermenu, um.usermenuItemsList);		
	}
	
	@Test (priority =3)
	public void editclick()
	{
		UserMenu um = new UserMenu(driver);
		profilepage p = new profilepage(driver);
		
		try {
			waitForElement(driver, p.editbtn);
			p.editbtn.click(); 
		}
		catch (Exception e)
		{
			e.getMessage();
			
		}	
	}
	
	@Test (priority =4)
	public void edit_popup_tabs()
	{
		profilepage p = new profilepage(driver);
		//p.edit_tabs_name(p.editpopuptabs);
		p.isTabDisplay(p.editpopuptabs, p.iframe, driver);	
	}
	
	@Test(priority =5)
	public void AboutTabLastName()
	{
		profilepage p = new profilepage(driver);
		BaseTest b1 = new BaseTest(); 
		waitForElement(driver, p.iframe);
		p.changeLastNameInAboutTab("Manav Patel", driver);
		p.profileName.getText().contains("Hemangini Manav");
		p.post(driver, "Post is created");
	
	}
	
	
	
	
}
