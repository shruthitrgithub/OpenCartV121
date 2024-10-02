package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass  {
	
	@Test(groups= {"Regression","Master"})

	public void verify_account_Registration() throws InterruptedException 
	{
		logger.info("*************Test Started*************");
		try {
		
		HomePage hp=new HomePage(driver);

		hp.clickMyAccount();
		logger.info("*******  Clicked on My Account  *****");
		hp.ClickRegister();
		logger.info("*******  Registration Completed  *****");
		
		AccountRegistrationPage AccPage = new AccountRegistrationPage(driver);
		logger.info("******* Providing Customer Details  *****");
		
		AccPage.setFirstName(randomeString().toUpperCase());
		AccPage.setLastname(randomeString().toUpperCase());
		AccPage.setEmail(randomeString()+"@gmail.com");//randomly generated email
		AccPage.setPhoneNo(randomeNumber());
     Thread.sleep(5000);
     
    String Password= randomeAlphaNumeric();
     
		AccPage.setPassword(Password);
		AccPage.setConfirm_Password(Password);

		AccPage.setPrivacy_PolicyString();
		AccPage.clickContinue();
		String confMsg=	AccPage.getConfirmationMsg();
		
		logger.info("Validating Expected Message");		
		Assert.assertEquals(confMsg, "Your Account Has Been Created!");

	}
catch (Exception e)
{
	logger.error("Test Failed");
	logger.debug("Debug Logs");
	Assert.fail();
}
		
	logger.info("***Test Completed  *******");	
	}
}





