package utilities;

import javax.swing.JOptionPane;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class global_functions 
{
	WebDriver DriverOBJ = null;
	public String BrowserTypeRequest_Fun()
	{
		String[] BT_Choices = {"IE", "Chrome", "Firefox", "Edge"}; //BrowserType_Choices
		String Selected_BT = (String) JOptionPane.showInputDialog(null, "Choose BrowserType", 
		"Select Browser Type Against YOu Want To Open Application", 
		JOptionPane.QUESTION_MESSAGE, null, 
		BT_Choices, BT_Choices[0]);
		System.out.println("Selected Browser Type Is: "+Selected_BT);
		return Selected_BT;
	}
	
	public WebDriver LaunchBrowser_Fun(String Selected_BT)
	{
		
		if (Selected_BT.equals("IE"))
    	{
			System.setProperty("webdriver.ie.driver", "C:\\Selenium_WebDriver\\IEDriverServer_x64_3.14.0\\IEDriverServer.exe");
			DriverOBJ = new InternetExplorerDriver();
			DriverOBJ.manage().window().maximize();
			System.out.println("Expected Browser*** "+Selected_BT+" *** Launched");
    	}
    
		if (Selected_BT.equals("Chrome"))
    	{
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium_WebDriver\\chromedriver_win32\\chromedriver.exe");
			DriverOBJ = new ChromeDriver();
			DriverOBJ.manage().window().maximize();
			System.out.println("Expected Browser*** "+Selected_BT+" *** Launched");
    	}
		
		if (Selected_BT.equals("Firefox"))
    	{
			System.setProperty("webdriver.gecko.driver", "C:\\Selenium_WebDriver\\geckodriver-v0.28.0-win64\\geckodriver.exe");
			DriverOBJ = new FirefoxDriver();
			DriverOBJ.manage().window().maximize();
			System.out.println("Expected Browser*** "+Selected_BT+" *** Launched");
    	}
		
		if (Selected_BT.equals("Edge"))
    	{
			System.setProperty("webdriver.edge.driver", "C:\\Selenium_WebDriver\\edgedriver_win64\\msedgedriver.exe");
			DriverOBJ = new EdgeDriver();
			DriverOBJ.manage().window().maximize();
			System.out.println("Expected Browser*** "+Selected_BT+" *** Launched");
    	}
		
		return DriverOBJ;
	}
	
	public void LaunchApplication_Fun()
	{
		DriverOBJ.navigate().to("http://www.amazon.com");
	}
	
	public void CloaseBrowserAndApplication()
	{
		DriverOBJ.quit();
	}
	
	
	
	
	
	
}
