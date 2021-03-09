package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UI_Actions extends baseRunner
{
	WebDriver DriverOBJ = null;
		
	//This is a Constructor is special method which name is same as class name and it mainly used for initialization purpose
	public UI_Actions(WebDriver DriverOBJ)
		{
			this.DriverOBJ = DriverOBJ;
		}	
	
	public void click(By locator)
		{
			DriverOBJ.findElement(locator).click();
		}
	
	// This Function is For Object/WebElement Exists Check Starts
			public String WebElementExistsCheck(By locator)
			{
				String ExistanceCheckValue;
				if ((DriverOBJ.findElement(locator) != null) && (DriverOBJ.findElement(locator).isDisplayed()))
					{ ExistanceCheckValue = "Exists"; }
				else
					{ ExistanceCheckValue = "NotExists"; }
				return ExistanceCheckValue;
			}
	// This Function is For Object/WebElement Exists Check Ends
			
	// This Functions is To Get Alert Messages Starts		
		public String GetAlertText(By locator)
		{
			
			String AlertMessage_AV = DriverOBJ.findElement(locator).getText().toString();
	        return AlertMessage_AV;
		}
	// This Functions is To Get Alert Messages Starts
			
	
}
