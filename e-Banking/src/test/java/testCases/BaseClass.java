package testCases;



import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
// common variables
//	public String baseURL = "https://demo.guru99.com/V1/index.php";
//	public String username = "mngr602743";
//	public String password = "terubYb";
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	
	@BeforeClass
	public void setup(String br) throws IOException
	{
		// Run test case on desired browser
	    switch(br.toLowerCase())
	 	{
	 	case "chrome": driver = new ChromeDriver(); break;
	 	case "edge": driver = new EdgeDriver(); break;
		case "firefox": driver = new FirefoxDriver(); break;
	 	default: System.out.println("Invalid browser name"); return;
       	}
		
	
		driver.get(p.getProperty("baseURL")); // we obtain appURL key from config.properties file which holds URL of Web Application
		driver.manage().window().maximize();
		
		
		logger = LogManager.getLogger(this.getClass()); // logger object
		
		// Loading config.properties file
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		p = new Properties(); // properties object
		p.load(file);
		
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	
	
	// user defined method captures a screenshot
     public String captureScreen(String tname, WebDriver driver) 
     {
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // Time stamp
			
			TakesScreenshot ts = (TakesScreenshot) driver;
			File sourceFile = ts.getScreenshotAs(OutputType.FILE);
			
			String targetFilePath = System.getProperty("user.dir")+ "\\screenshots\\" + tname +"_" + timeStamp;
			File targetFile = new File(targetFilePath);
			
			sourceFile.renameTo(targetFile);
			return targetFilePath;
			
     }
    
    
	// user defined method for generating a random string
	public static String randomestring() 
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(8); // it will create a string which contains 8 characters
		return (generatedstring);
	}
	
	
	// User defined method for generating a random number
	public static String randomenumber() 
	{
	    String generatednumber = RandomStringUtils.randomNumeric(8); // it will create a string which contains 8 characters
	    return (generatednumber);
	}
	

}