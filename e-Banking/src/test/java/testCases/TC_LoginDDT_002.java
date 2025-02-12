package testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import utilities.DataProviders;
import utilities.ExcelUtility;

public class TC_LoginDDT_002 extends BaseClass {
	
	
	@Test(dataProvider= "LoginData", dataProviderClass=DataProviders.class)
	public void loginTDD(String username, String password)
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username); // user name comes from excel file 
		logger.info("user name is provided");
		lp.setPassword(password); // password comes from excel file 
		logger.info("password is provided");
		lp.clickSubmit();
		
		if(isAlertPersent() == true)
		{
			driver.switchTo().alert().accept(); // will close alert
	        driver.switchTo().defaultContent(); // will focus on login page 
	        Assert.assertTrue(false); // will fail the test case
	        logger.warn("Login failed");
			
		} else 
		{
			Assert.assertTrue(true); // will pass the test case
		    logger.warn("Login ");
			lp.clickLogout(); // logout 
			driver.switchTo().alert().accept(); // close logout alert
			driver.switchTo().defaultContent(); // focus on login page 
		}

	}
	
	// validation
	public boolean isAlertPersent()
	{
		try 
		{
		driver.switchTo().alert();
		return true; // if the method returns true that indicates alert is present
		}
		catch (NoAlertPresentException e)
		{
			return false; // if the method returns false that indicates alert is present
		}
		
		
	}
	
	

	

}
