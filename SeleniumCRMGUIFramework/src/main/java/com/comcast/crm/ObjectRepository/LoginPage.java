package com.comcast.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.utility.WebDriverUtility;

public class LoginPage extends WebDriverUtility {
	
	WebDriver driver;
	//Rule1:seperate java class
	//Rule2:Object creation
	
	
	//Rule4:Object Encapsulation
	public WebElement getUsernametf() {
		return usernametf;
	}

	public WebElement getPasswordtf() {
		return passwordtf;
	}

	public WebElement getLoginbtn() {
		return loginbtn;
	}

	@FindBy(name="user_name")
	private WebElement usernametf;
	
	@FindBy(name="user_password")
	private WebElement passwordtf;
	
	@FindBy(id="submitButton")
	private WebElement loginbtn;
	
	//Rule3:Object initialization
	public LoginPage(WebDriver driver) {
		//initilaize the driver
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Business method
	public void LoginToApp(String url,String usernamedata,String passworddata) {
		waitPageToLoad(driver);
		driver.get(url);
		usernametf.sendKeys(usernamedata);
		passwordtf.sendKeys(passworddata);
		loginbtn.click();
	}

}
