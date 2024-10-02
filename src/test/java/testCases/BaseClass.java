package testCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

public static WebDriver driver;
public Logger logger;
public Properties prop;

	@BeforeClass(groups= {"Sanity,Regression","Master"})
	@Parameters({"os","browser"})
	
	
	public void setUp(String os,String br) throws IOException
	{
//Loading config.properties File
	FileReader file =new FileReader("./src//test//resources//config.properties");
	prop=new Properties();
	prop.load(file);
	
	
	
logger =LogManager.getLogger(this.getClass());

switch (br.toLowerCase())
{
case "chrome" :driver =new ChromeDriver(); break;
case "edge" : driver = new EdgeDriver();break;
case "firefox" :driver = new FirefoxDriver();break;
default : System.out.println("Invalid Browser Name"); return;
}	
		
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();	
		driver.get(prop.getProperty("appUrl"));//reading URL from property File	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.close();
	}
	public String randomeString()
	{
	String generatedString=RandomStringUtils.randomAlphabetic(5);
	return generatedString;
	}
	public String randomeNumber()
	{
	String generatedNumber=RandomStringUtils.randomNumeric(10);
	return generatedNumber;
	}
	public String randomeAlphaNumeric()
	{
	String generatedString=RandomStringUtils.randomAlphabetic(3);
	String generatedNumber=RandomStringUtils.randomNumeric(3);
	return(generatedString+"#"+generatedNumber);

	
	}
public String captureScreen(String tname) {
	String timeStamp = new SimpleDateFormat("yyyyMMddhhss").format(new Date());
	
	
	TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
	File SourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
	
	String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tname + "_"+ timeStamp+ ".png";
	File targetFile = new File(targetFilePath);
	
	SourceFile.renameTo(targetFile);
	return targetFilePath;
}

}
