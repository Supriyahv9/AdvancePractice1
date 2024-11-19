package com.comcast.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationPage {
	
	WebDriver driver;

	@FindBy(name="accountname")
	private WebElement OrgNametf;
	
	@FindBy(xpath="(//input[@name='button'])[1]")
	private WebElement Savebtn;
	
	@FindBy(name="industry")
	private WebElement Industrydropdown;
	
	@FindBy(xpath="accounttype")
	private WebElement Typedropdown;

	public WebElement getOrgNametf() {
		return OrgNametf;
	}

	public WebElement getSavebtn() {
		return Savebtn;
	}
	
	public CreateNewOrganizationPage(WebDriver driver) {
		//initilaize the driver
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void CreateOrg(String name) {
		OrgNametf.sendKeys(name);
		Savebtn.click();
	}
	
	public void CreateOrg(String name, String industry) {
		OrgNametf.sendKeys(name);
		Select si = new Select(Industrydropdown);
		si.selectByVisibleText(industry);
		Savebtn.click();
	}
}
