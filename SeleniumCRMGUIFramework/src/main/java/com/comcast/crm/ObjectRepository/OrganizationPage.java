package com.comcast.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement CreateOrgbtn;

	public WebElement getCreateOrgLink() {
		return CreateOrgbtn;
	}
	
	public OrganizationPage(WebDriver driver) {
		//initilaize the driver
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

}
