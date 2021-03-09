package testScripts;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import appPages.AmazonLanding_Page;
import appPages.AmazonSignIn_Page;
import utilities.baseRunner;

public class SignInFunctionalValidation_Script extends baseRunner
{
	
	@Test
	public static void SignInFunctionalValidation() throws Exception 
	{
		ExtentTest TestReportName = ExtentReportForTest_Fun("SignInFunctionalValidation");
		
		Thread.sleep(9000);
		//Creating Object for Applications Pages Starts
			AmazonLanding_Page AmazonLandingPageOBJ = new AmazonLanding_Page(DriverOBJ);
			AmazonSignIn_Page AmazonSignInPageOBJ = new AmazonSignIn_Page(DriverOBJ);
		//Creating Object for Applications Pages Ends
		
		//String LandingPageWebElementCheck_Value = AmazonLandingPageOBJ.ObjectExistsVerification_Fun();
		String SingInButtonExists_Value = AmazonLandingPageOBJ.WebElementExists_Fun("SingIn Button");
		if ("Exists".equals(SingInButtonExists_Value))
			{
				TestReportName.pass("Expected Amazon Landing Page Displayed");
				AmazonLandingPageOBJ.SiginButtonClick();
				//String SignInPageWebElementCheck_Value = AmazonSignInPageOBJ.ObjectExistsVerification_Fun();
				String UserNameExists_Value = AmazonSignInPageOBJ.WebElementExists_Fun("UserName EditBox");
				if (UserNameExists_Value.equals("Exists"))
					{
					TestReportName.pass("Expected Amazon SignIn Page Displayed");
						  	
						
						AmazonSignInPageOBJ.EnterUserName("Test@Test.com");
						String ContinueButtonExists_Value = AmazonSignInPageOBJ.WebElementExists_Fun("Continue Button");
						if ("Exists".equals(ContinueButtonExists_Value))
							{
								AmazonSignInPageOBJ.PressContinueButton_Fun();
							}
					}
				else
					{
						TestReportName.fail("Expected Amazon SignIn Page Not Displayed");
					}
			}
		else
			{
				TestReportName.pass("Expected Amazon Landing Page Not Displayed");
			}
		
		
		//DriverOBJ.close();
	}
		
}
