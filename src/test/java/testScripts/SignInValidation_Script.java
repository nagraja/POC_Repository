package testScripts;


import java.util.HashMap;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.reporters.TestHTMLReporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.model.Media;

/*
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
*/

import appPages.AmazonLanding_Page;
import appPages.AmazonSignIn_Page;
import utilities.baseRunner;
import utilities.excelUtilities;




public class SignInValidation_Script extends baseRunner
{
	//Global Functions Object Creation
	//static global_functions gfOBJ = new global_functions();
	
	//Calling and Assigning Returned Value from Browser TypeFunction
	//static String Selected_BT = gfOBJ.BrowserTypeRequest_Fun();
	
	//Calling Browser Launch Function and Assigning Driver value returned from Function
	//static WebDriver DriverOBJ = gfOBJ.LaunchBrowser_Fun(Selected_BT);
	
	@Test
	public static void SiginValidation() throws Exception
	{
		Thread.sleep(5000);
		ExtentTest TestReportName = ExtentReportForTest_Fun("SiginValidation");
		//Creating Object for Applications Pages Starts
			AmazonLanding_Page AmazonLandingPageOBJ = new AmazonLanding_Page(DriverOBJ);
			AmazonSignIn_Page AmazonSignInPageOBJ = new AmazonSignIn_Page(DriverOBJ);
		//Creating Object for Applications Pages Ends
		
		System.out.println("Git & GitHub Branch Demo");

		
		//String LandingPageWebElementCheck_Value = AmazonLandingPageOBJ.ObjectExistsVerification_Fun();
		String SingInButtonExists_Value = AmazonLandingPageOBJ.WebElementExists_Fun("SingIn Button");
		if ("Exists".equals(SingInButtonExists_Value))
			{
				TestReportName.pass("Expected Amazon Landing Page Displayed");
				AmazonLandingPageOBJ.SiginButtonClick();
				//String SignInPageWebElementCheck_Value = AmazonSignInPageOBJ.ObjectExistsVerification_Fun();
				
				//This section is to get the Row Count From DataFile Starts
					int RowCount_Value = excelOBJ.GetRowCount();
			    	System.out.println("Row Count: "+RowCount_Value);
				//This section is to get the Row Count From DataFile Ends
		    	
			    	int ColumnCount_Value = excelOBJ.GetColumnCount();
			    	System.out.println("Column Count: "+ColumnCount_Value);
		    	
		    	//Hash Map Object Creation Starts to get the column name and corresponding values
			    	HashMap<String, String> cn_OBJ = new HashMap<String, String>(); //cnOBJ=columnnameOBJ
			    //Hash Map Object Creation Ends
			    	
			    for (int rli=1; rli<=(RowCount_Value-1); rli++)
			    {
			    	//This Loop is to Get the Column Name and corresponding Value Start
			    	cn_OBJ.clear();
			    	for (int cni=0; cni<ColumnCount_Value; cni++)
				    {
				    	String Column_Name = excelUtilities.GetCellData(0, cni);
			    		String Column_Value = excelUtilities.GetCellData(rli, cni);
			    		//System.out.println("Column Name Is: "+Column_Name+" And Corresponding Value Is: "+Column_Value);
			    		cn_OBJ.put(Column_Name, Column_Value);
			    	}		    	
			    	//This Loop is to Get the Column Name and corresponding Value Ends 
			    	
			    	if (cn_OBJ.get("TC_ExecutionFlg_V").equals("yes"))
				    	{
			    			String UserNameExists_Value = AmazonSignInPageOBJ.WebElementExists_Fun("UserName EditBox");
							if (UserNameExists_Value.equals("Exists"))
								{
									TestReportName.pass("Expected Amazon SignIn Page Displayed");
									
									AmazonSignInPageOBJ.EnterUserName(cn_OBJ.get("UserName_V"));
									AmazonSignInPageOBJ.PressContinueButton_Fun();
									String AlertMessage_ActualValue = AmazonSignInPageOBJ.GetAlertText_Fun(cn_OBJ.get("TC_Exception_EV"), cn_OBJ.get("TCsummary_V"));
									if (cn_OBJ.get("TC_Exception_EV").equals(AlertMessage_ActualValue))
						        		{ 
											TestReportName.pass("Expected Alert Message: "+cn_OBJ.get("TC_Exception_EV")+" :And: Actual Alert Message :"+AlertMessage_ActualValue+" :Are Matched"); 
										}
							        else
							        	{ 
							        		TestReportName.fail("Expected Alert Message: "+cn_OBJ.get("TC_Exception_EV")+" :And: Actual Alert Message :"+AlertMessage_ActualValue+" :Are Not Matched");
							        		String sspath = baseRunner.getScreenShot_Fun(DriverOBJ, "SiginValidation");
							        		TestReportName.log(Status.FAIL, (Markup) TestReportName.addScreenCaptureFromPath(sspath));
						        	}
								}
							else
								{
								TestReportName.fail("Expected Amazon SignIn Page Not Displayed");
								}
						}
			    	else
			    	{
			    		TestReportName.info("Test Case: "+cn_OBJ.get("TC_Number")+"Is Skipped");
			    	}
			    }
			    AmazonSignInPageOBJ.AmazonLogoClick();
			}
		else
			{
			TestReportName.pass("Expected Amazon Landing Page Not Displayed");
			}
		//DriverOBJ.close();
		
		
	}
	
	
	
}
