package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage (WebDriver driver) 
	{
		super(driver);
		
	}

@FindBy(xpath="//span[normalize-space()='My Account']")
WebElement LnkMyaccount;

@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")	
WebElement LnkRegister;

@FindBy(linkText="Login")	
WebElement linkLogin;


public void clickMyAccount()
{
	LnkMyaccount.click();
}

public void ClickRegister()
{
	LnkRegister.click();
}
public void ClickLogin()
{
	linkLogin.click();
}


}