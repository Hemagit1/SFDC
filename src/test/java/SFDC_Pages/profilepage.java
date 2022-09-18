package SFDC_Pages;

import static org.testng.Assert.assertEquals;

import java.lang.System.Logger;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SFDC.com.LoginTest;

public class profilepage {

	WebDriver driver;

	public profilepage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='editPen']/a")
	public WebElement editbtn;

	@FindBy(xpath = "//div[@class='zen-header']/ul/li")
	public List<WebElement> editpopuptabs;

	@FindBy(id = "contactInfoContentId")
	public WebElement iframe;

	@FindBy(id = "aboutTab")
	public WebElement aboutTab;

	@FindBy(id = "//div[@class= 'zen-header']/ul/li[@id='contactTab']")
	public WebElement contactTab;

	@FindBy(id = "lastName")
	public WebElement aboutTablastName;

	@FindBy(xpath = "//input[@value='Save All']")
	public WebElement aboutTabSaveAll;

	@FindBy(id = "publisherAttachTextPost")
	public WebElement postLink;

	@FindBy(id = "publishersharebutton")
	public WebElement shareButton;

	@FindBy(id = "userNavLabel")
	public WebElement profileName;

	@FindBy(xpath = "//div[@class='feeditembody']/div/div/span")
	public WebElement sharedPost;

	@FindBy(xpath = "//iframe[@class='cke_wysiwyg_frame cke_reset']")
	public WebElement iFram2;

	@FindBy(xpath = "/html/body")
	public WebElement postTextInput;

	public boolean isTabDisplay(List<WebElement> tablocation, WebElement iframe, WebDriver driver) {

		boolean tabdisplay = true;
		driver.switchTo().frame(iframe);

		for (WebElement tabname : tablocation) {
			tabname.isDisplayed();
		}

		driver.switchTo().defaultContent();
		return tabdisplay;
	}

	public boolean changeLastNameInAboutTab(String lastname, WebDriver driver) {

		boolean isLastNamechange = false;
		driver.switchTo().frame(iframe);

		if (aboutTab.isDisplayed()) {
			aboutTab.click();
			aboutTablastName.clear();
			aboutTablastName.sendKeys(lastname);
			aboutTabSaveAll.click();
			isLastNamechange = true;
		}

		driver.switchTo().defaultContent();
		return isLastNamechange;

	}

	public boolean post(WebDriver driver, String postMessage) {
		boolean isPostCreated = false;

		if (postLink.isDisplayed()) {
			postLink.click();
			driver.switchTo().frame(iFram2);
			postTextInput.sendKeys(postMessage);
			driver.switchTo().defaultContent();
			
			if (shareButton.isDisplayed()) {
				shareButton.click();
				isPostCreated = true;
			}
		}
		return isPostCreated;
	}
}
