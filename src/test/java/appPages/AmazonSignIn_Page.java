package appPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

import utilities.UI_Actions;
import utilities.baseRunner;

public class AmazonSignIn_Page extends baseRunner
{

	private static final String Exists_Value = null;
	WebDriver DriverOBJ = null;
	UI_Actions UIactionsOBJ = null;
	
	//Page Objects Identification Starts
	private By unOBJ = By.xpath("//*[@id='ap_email']");
	private By pwOBJ = By.xpath("//*[@id='ap_password']");
	private By cbOBJ = By.xpath("//*[@id='continue']");
	private By cabOBJ = By.xpath("//*[@id='createAccountSubmit']");
	private By SignInPageHeadingOBJ = By.xpath("//*[@id='authportal-main-section']/div[2]/div/div[1]/form/div/div/div/h1]");
	private By UN_Empty_amOBJ = By.xpath("//*[@id='auth-email-missing-alert']/div[@class='a-box-inner a-alert-container']/div[@class='a-alert-content']");
	//private By UN_Empty_amOBJ1 = By.xpath("//*[@id='auth-error-message-box']/div[@class='a-box-inner a-alert-container']/div[@class='a-alert-content']/ul[@class='a-unordered-list a-nostyle a-vertical a-spacing-none']/li/span[@class='a-list-item']");
	private By UN_Invalid_amOBJ = By.xpath("//*[@id=\"auth-error-message-box\"]/div/div/ul/li/span");
	private By amazon_Logo = By.xpath("//*[@id=\"a-page\"]/div[1]/div[1]/div/a/i");
		
	//ExtentTest AmazonsigninPageReport = ReportOBJ.createTest("AmazonSigInPage", "Amazon SignIn Page Validation");
	
	
	//*[@id="a-page"]/div[1]/div[1]/div/a/i										
	
	//This is a Constructor is special method which name is same as class name and it mainly used for initialization purpose Starts
		public AmazonSignIn_Page(WebDriver DriverOBJ)
		{
			this.DriverOBJ = DriverOBJ;
			this.UIactionsOBJ = new UI_Actions(DriverOBJ);
		}	
	//This is a Constructor is special method which name is same as class name and it mainly used for initialization purpose Ends
		
	//This Functions is to Enter User Name in The User Name Text Box Starts
		public void EnterUserName(String UN_Value)
		{
			DriverOBJ.findElement(unOBJ).clear();
			DriverOBJ.findElement(unOBJ).sendKeys(UN_Value);
		}
	//This Functions is to Enter User Name in The User Name Text Box Ends
	
	// This Function is to Press Continue Button Start	
		public void PressContinueButton_Fun()
		{
			UIactionsOBJ.click(cbOBJ);
		}
	// This Function is to Press Continue Button Ends
	
	//This Function is to click on Amazon Logo Starts	
		public void AmazonLogoClick()
		{
			UIactionsOBJ.click(amazon_Logo);
		}	
	//This Function is to click on Amazon Logo Ends		
		
		
	// This Function is To Check Web Element Existence Starts
		public String WebElementExists_Fun(String WebElementName)
		{
					
			String Exists_Value = UIactionsOBJ.WebElementExistsCheck(unOBJ);
//			if ("Exists".equals(Exists_Value))
//				{ AmazonsigninPageReport.pass("Expected "+WebElementName+" Exists and Displayed"); }
//			else
//				{ AmazonsigninPageReport.fail("Expected "+WebElementName+" Not Exists or Not Displayed"); }
			return Exists_Value;
		}
	// This Function is To Check Web Element Existence Ends
	
	// This Function is to Actual Alert Message Starts
		public String GetAlertText_Fun(String AlertMessage_ExpectedValue, String TCsummary)
		{
			String AlertMessage_ActualValue = null;
			//System.out.println("User Name Entry Value: "+UNvalue);
			if (TCsummary.equals("Empty"))
			{
				AlertMessage_ActualValue = UIactionsOBJ.GetAlertText(UN_Empty_amOBJ);
		    }
			
			if (TCsummary.equals("Invalid"))
			{
				AlertMessage_ActualValue = UIactionsOBJ.GetAlertText(UN_Invalid_amOBJ);
		    }
			
			//Returning Alert Message back to, from where it is called
			return 	AlertMessage_ActualValue;		
		}
	// This Function is to Actual Alert Message Ends	
	
		
/*		
	// This Function is For Object/WebElement Exists Check Starts
		public String ObjectExistsVerification_Fun()
		{
			String ExistanceCheckValue;
			if ((DriverOBJ.findElement(unOBJ) != null) && (DriverOBJ.findElement(unOBJ).isDisplayed()))
				{
					AmazonsigninPageReport.pass("Expected UserName TextBox Exists and Displayed");
					ExistanceCheckValue = "Exists";
				}
			else
				{
					AmazonsigninPageReport.fail("Expected UserName TextBox Not Exists or Not Displayed");
					ExistanceCheckValue = "NotExists";
				}
			return ExistanceCheckValue;
		}
	// This Function is For Object/WebElement Exists Check Ends
*/
}
