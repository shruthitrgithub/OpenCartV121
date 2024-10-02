package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BasePage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
@Test(dataProvider = "LoginData",dataProviderClass=DataProviders.class,groups="Datadriven")//Getting Data Providers from Different Class
public void Verify_loginDDT(String email,String pwd,String result)
{
	
	logger.info("******** Test TC003 Started ********");
//HomePage	

try
{
	HomePage hp = new HomePage(driver);
	hp.clickMyAccount();
	hp.ClickLogin(); 
	
//Login
	
LoginPage lp =new LoginPage(driver);
lp.setEmail(email);
lp.setPassWord(pwd);
lp.clickLogin();

//MyAccount 
MyAccountPage macc =new MyAccountPage(driver);
boolean targetPage = macc.isMyAccountPageExists();

/*Data is valid --> Login Success--> testpass--> logout
Data is valid --> Login Failed--> test fail
Data is invalid --> Login Success--> test fail
Data is invalid --> Login Fail--> test pass*/

if(result.equalsIgnoreCase("Valid"))
{
	if (targetPage==true)
	{
		macc.ClickLogOut();
		Assert.assertTrue(true);
		
		
	}
	else {
		Assert.assertTrue(false);
	}
}
if(result.equalsIgnoreCase("InValid"))
{
	if (targetPage==true)
	{
		macc.ClickLogOut();
		Assert.assertTrue(false);
		
		
	}
	else {
		Assert.assertTrue(true);
	}
}
}

catch(Exception e)
{
	Assert.fail();
}
logger.info("******** Test TC003 Finished ********");



}

}




