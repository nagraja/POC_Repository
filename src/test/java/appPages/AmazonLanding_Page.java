package appPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import utilities.UI_Actions;
import utilities.baseRunner;


public class AmazonLanding_Page extends baseRunner
{
	private static final String Exists_Value = null;

	//Declare WebDriver at the Class level so that you can use it in all the methods in this class
	WebDriver DriverOBJ = null;
	
	UI_Actions UIactionsOBJ = null;
	
	By SignInObj = By.xpath("//*[@id='nav-link-accountList']");
	
	//ExtentTest AmazonLandingPageReport = ReportOBJ.createTest("AmazonLandingPage", "Amazon Landing Page Validation");
	
	//This is a Constructor is special method which name is same as class name and it mainly used for initialization purpose
	public AmazonLanding_Page(WebDriver DriverOBJ)
	{
		this.DriverOBJ = DriverOBJ;
		this.UIactionsOBJ = new UI_Actions(DriverOBJ);
	}	
	
	public void SiginButtonClick()
	{
		UIactionsOBJ.click(SignInObj);
	}
	
	
	// This Function is To Check Web Element Existence Starts
		public String WebElementExists_Fun(String WebElementName)
		{
			String Exists_Value = UIactionsOBJ.WebElementExistsCheck(SignInObj);
//			if ("Exists".equals(Exists_Value))
//				{ AmazonLandingPageReport.pass("Expected "+WebElementName+" Exists and Displayed"); }
//			else
//				{ AmazonLandingPageReport.fail("Expected "+WebElementName+" Not Exists or Not Displayed"); }
			return Exists_Value;
		}
	// This Function is To Check Web Element Existence Ends
	
	
/*	
	
	public String ObjectExistsVerification_Fun()
	{
		String ExistanceCheckValue;
		//DriverOBJ.findElement(SignInObj).isDisplayed();
		if ((DriverOBJ.findElement(SignInObj) != null) && (DriverOBJ.findElement(SignInObj).isDisplayed()))
			{
				AmazonLandingPageReport.pass("Expected SignIn Button Exists and Displayed");
				System.out.println("Signin Button Exists");
				ExistanceCheckValue = "Exists";
				
			}
		else
			{
				System.out.println("Signin Button Not Exists");
				ExistanceCheckValue = "NotExists";
			}
		return ExistanceCheckValue;
	}
	
*/	
}
