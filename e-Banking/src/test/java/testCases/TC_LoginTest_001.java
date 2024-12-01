package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	// Test methods
	@Test
	public void loginTest() 
	{
			
		LoginPage lp = new LoginPage(driver); // we pass the driver to page object class 
		lp.setUserName(p.getProperty("username")); // we get user name value from config.properties file 
		logger.info("Entered username");
		lp.setPassword(p.getProperty("password"));  // we get password value from config.properties file 
		logger.info("Entered password");
		lp.clickSubmit();

		
		
		// validate whether the actual title of home page equals expected title or not
		if(driver.getTitle().equals("GTPL Bank Manager HomePage"))
		{
			Assert.assertTrue(true); // test case passed
			logger.info("Login test passed");
		}
		else
		{
			Assert.assertTrue(false); // test case failed
			logger.info("Login test failed");
		}
		
		
	
	
	}

}
