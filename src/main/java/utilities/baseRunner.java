package utilities;

import static utilities.baseRunner.brOBJ;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import utilities.excelUtilities;

import javax.swing.JOptionPane;

import org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.markuputils.util.*;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class baseRunner 
{
	public static String BrowserType_Value;
	public static WebDriver DriverOBJ;
	static Properties pfOBJ = new Properties();
	static String ProjectPath = System.getProperty("user.dir");
	
	public static String BT_Name = null;
	public static String TestEnv_Value = null;
	public static String TestEnv1 = "Test1";
	public static String Selected_BT;
	public static String ProjectRelativePath_Value;
	public static excelUtilities excelOBJ;
	public static ExtentTest SignFunctionalValidationReportOBJ;
	public static ExtentTest SignValidationReportOBJ;
	public static ExtentTest ExtentReportOBJ;
	
	public static ExtentSparkReporter ReporterOBJ;
	public static ExtentReports ReportOBJ;
	
	//Global Functions Object Creation
		static baseRunner brOBJ = new baseRunner();
	
	@Parameters({"BTN","TE_Value"})
	//@BeforeSuite
	@BeforeTest
	public static void setup_Fun(String BTN, String TE_Value)
	{
		
		// start reporters
        ReporterOBJ = new ExtentSparkReporter("TestExecutionReport.html");
        // create ExtentReports and attach reporter(s)
        ReportOBJ = new ExtentReports();
        ReportOBJ.attachReporter(ReporterOBJ);
        
		//Test Data File Excel Setup Starts
			excelOBJ = brOBJ.TestDataFile_Fun("Login_FunctionalTest_DF.xlsx", "Sheet1");
		//Test Data File Excel Setup Starts
		
		//Calling This function to Get BrowserType Value from Properties File
			
			System.out.println("Browser Name From Properties File Is: "+baseRunner.getProperties("BrowserType"));
			System.out.println("Test Environment Value From Properties File Is: "+baseRunner.getProperties("TestEnv"));
			
			System.out.println();
			
			System.out.println("Browser Name From testng.xml File Is: "+BTN);
			System.out.println("Test Environment Value From testng.xml File Is: "+TE_Value);
									
			baseRunner.setProperties("BrowserType", BTN);
			baseRunner.setProperties("TestEnv", TE_Value);
			
			System.out.println();
			System.out.println("Browser Name From Properties File After Set Is: "+baseRunner.getProperties("BrowserType"));
			System.out.println("Test Environment Value From Properties File After Set Is: "+baseRunner.getProperties("TestEnv"));
			
			String BT_Name = baseRunner.getProperties("BrowserType");
			String TestEnv_Value = baseRunner.getProperties("TestEnv");
			if (BT_Name.isEmpty()) 
				{
					//Calling and Assigning Returned Value from BrowserTypeRequest
						Selected_BT = brOBJ.BrowserTypeRequest_Fun();
				}
			else
				{
					Selected_BT = BT_Name;
				}
		
		//Calling Browser Launch Function and Assigning Driver value returned from Function
			DriverOBJ = brOBJ.LaunchBrowser_Fun(Selected_BT);
			
			brOBJ.Open_And_LaunchApp();
		}

	public void Open_And_LaunchApp()
	{
		//Launch Application Starts
			brOBJ.LaunchApplication_Fun();
			String URL_Value = DriverOBJ.getCurrentUrl();
			System.out.println("Browser Launched: "+URL_Value);
		//Launch Application Ends
	}
	
	public static ExtentTest ExtentReportForTest_Fun(String TestNameForExtentReport)
	{
		//Creating Reporter Object to report statuses in this class.
			ExtentReportOBJ = ReportOBJ.createTest(TestNameForExtentReport, "Test: "+TestNameForExtentReport+" Executed In***"+baseRunner.getProperties("TestEnv")+"***Environment");
		//Creating Reporter Object to report statuses in this class.
		
		return ExtentReportOBJ;
	}
	
	
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
	
	@AfterTest
	public void TearDown() throws Exception
	{
		ReportOBJ.flush();
	}
	
	public excelUtilities TestDataFile_Fun(String FileName, String SheetName)
	{
		//Excel Setup Starts
			ProjectRelativePath_Value = System.getProperty("user.dir");
			excelOBJ = new excelUtilities(ProjectRelativePath_Value+"/excel/"+FileName, SheetName);
		//Excel Setup Ends
		return excelOBJ;
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
		
		// start reporters
				
		return DriverOBJ;
	}
	
	public void LaunchApplication_Fun()
	{
		DriverOBJ.navigate().to("http://www.amazon.com");
	}
	
	public void CloseBrowserAndApplication()
	{
		DriverOBJ.quit();
	}
	
	public static String getProperties(String ParameterName)
	{
		String Parameter_Value = null;
		try 
			{
				InputStream inputOBJ = new FileInputStream(ProjectPath+"/src/main/java/configPackage/config.properties");
				try 
				{
					pfOBJ.load(inputOBJ);
					Parameter_Value = pfOBJ.getProperty(ParameterName);
					//System.out.println(Parameter_Value);
					
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return Parameter_Value;
	}
	
	public static void setProperties(String PN, String PV)
	{
		try 
			{
				
				OutputStream outputOBJ = new  FileOutputStream(ProjectPath+"/src/main/java/configPackage/config.properties");
				pfOBJ.setProperty(PN, PV);
				//pfOBJ.setProperty("BrowserType", "Firefox");
				//pfOBJ.setProperty("Status", "Passed");
				try 
					{
						pfOBJ.store(outputOBJ, "This is Writing BrowserType Value In The Properties File");
					} 
				catch (IOException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} 
		catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	public static String getScreenShot_Fun(WebDriver DriverOBJ, String screenshotName) throws Exception 
	{
		TakesScreenshot takeScreenshotOBJ = (TakesScreenshot) DriverOBJ;
		
		File screenshotsSource = takeScreenshotOBJ.getScreenshotAs(OutputType.FILE);
		
		String screenshotsDestination = System.getProperty("user.dir")+"\\src\\test\\resources\\screenShots"+screenshotName+System.currentTimeMillis()+".png";
		
		File fileDestination = new File(screenshotsDestination);
		
		FileUtils.copyFile(screenshotsSource, fileDestination);
						
		return screenshotsDestination;
	}
	
	
}
