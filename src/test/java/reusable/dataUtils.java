package reusable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class dataUtils {
	
	static WebDriver driver;
	public static Logger logger = LogManager.getLogger(dataUtils.class.getName());
	
	public static String getProperties(String filename , String Key) throws IOException
	{
		Properties p = new Properties();
		FileReader file = new FileReader("C:\\Users\\ADMIN\\eclipse-workspace\\SFDC\\src\\test\\java\\resources\\login.properties");
		p.load(file);
		String value = p.getProperty(Key);
		return value;	
	}
	
	/*public static boolean waitForElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}*/

}
