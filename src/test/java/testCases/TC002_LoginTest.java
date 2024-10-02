package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass {

	
	@Test(groups={"sanity","Master"})
	public void Verify_login()
	{
	//HomePage	
	logger.info("***********  Test TC002 Started  ***********");
	try
	{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.ClickLogin(); 
		
	//Login
LoginPage lp =new LoginPage(driver);
    lp.setEmail(prop.getProperty("email"));
    lp.setPassWord(prop.getProperty("password"));
	lp.clickLogin();
	
	//MyAccount 
	MyAccountPage macc =new MyAccountPage(driver);
	boolean targetPage = macc.isMyAccountPageExists();
	Assert.assertTrue(targetPage);
	
	}
	catch(Exception e)
	{
		Assert.fail();
	}
	logger.info("************ Finished TC_002_Login Test ****************");
}
}
