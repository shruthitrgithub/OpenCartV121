package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}

	
@FindBy (xpath="//input[@id='input-firstname']")
WebElement txtfName;
@FindBy(xpath= "//input[@id='input-lastname']")
WebElement txtLName;

@FindBy(xpath="//input[@id='input-email']")
WebElement txtemail;

@FindBy(xpath="//input[@id='input-telephone']")
WebElement txtPhone;

@FindBy(xpath="//input[@id='input-password']")
WebElement txtPassWord;

@FindBy(xpath="//input[@id='input-confirm']")
WebElement txtConfirm_Password;


@FindBy(xpath="//input[@name='agree']")
WebElement Chk_box;

@FindBy(xpath="//input[@value='Continue']")
WebElement btnContinue;	

@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
WebElement msgConfirmation;	


public void setFirstName(String fname)
{
	txtfName.sendKeys(fname);
}
public void setLastname(String lname)
{
	txtLName.sendKeys(lname);
}
public void setEmail(String email)
{
	txtemail.sendKeys(email);
}
public void setPhoneNo(String num)
{
	txtPhone.sendKeys(num);
}

public void setPassword(String pwd)
{
	txtPassWord.sendKeys(pwd);
}
public void setConfirm_Password(String Cpwd)
{
	txtConfirm_Password.sendKeys(Cpwd);
}
public void setPrivacy_PolicyString()
{
	Chk_box.click();
}
public void clickContinue()
{
	btnContinue.click();
}


public String getConfirmationMsg() {
	
try{
	return(msgConfirmation.getText());
} catch(Exception e) {
	return (e.getMessage());
}
}
}






