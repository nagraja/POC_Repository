package example;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class NewTest {
	WebDriver driver = null;		
	@Test				
	public void testEasy() {
		//System.setProperty("webdriver.chrome.driver", "C:\\Selenium_WebDriver\\chromedriver_win32\\chromedriver.exe");
		
		//driver.navigate().to("www.amazon.com");
		driver.get("http://www.amazon.com");
		String title = driver.getTitle();	
		System.out.println("testEasy test Page Title Is:"+title);
		Assert.assertTrue(title.contains("Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more")); 		
	}	
	
	@Test
	public void test1()
	{
		driver.get("http://www.google.com");
		String title = driver.getTitle();	
		System.out.println("test1 test Page Title Is:"+title);
		Assert.assertTrue(title.contains("Google"));
	}
	
	
	@BeforeTest
	public void beforeTest() {	
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium_WebDriver\\chromedriver_win32\\chromedriver.exe");
	    driver = new ChromeDriver();  
	}		
	@AfterTest
	public void afterTest() {
		//driver.quit();			
	}

}
