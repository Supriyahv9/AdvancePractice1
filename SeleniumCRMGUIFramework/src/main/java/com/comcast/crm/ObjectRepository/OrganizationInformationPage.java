package com.comcast.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {
	
	WebDriver driver;

	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headerinfo;

	public WebElement getHeaderinfo() {
		return headerinfo;
	}
	
	public OrganizationInformationPage(WebDriver driver) {
		//initilaize the driver
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
}
