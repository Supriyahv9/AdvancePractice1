package com.comcast.crm.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	

	public WebElement getOrgLink() {
		return OrgLink;
	}


	public WebElement getContactLink() {
		return ContactLink;
	}


	@FindBy(linkText="Contacts")
	private WebElement ContactLink;
	

	@FindBy(linkText="Organizations")
	private WebElement OrgLink;
	
	@FindBy(linkText="More")
	private WebElement MoreLink;
	
	@FindBy(linkText="Campaigns")
	private WebElement CampaignLink;
	
	@FindBy(linkText="Product")
	private WebElement ProductLink;
	
	public WebElement getProductLink() {
		return ProductLink;
	}


	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement Adminimg;
	
	public WebElement getAdminimg() {
		return Adminimg;
	}


	public WebElement getSignoutbtn() {
		return Signoutbtn;
	}


	@FindBy(linkText="Sign Out")
	private WebElement Signoutbtn;

	
	public WebElement getCampaignLink() {
		return CampaignLink;
	}


		public WebDriver getDriver() {
		return driver;
	}


	public WebElement getMoreLink() {
		return MoreLink;
	}


		//Rule3:Object initialization
		public HomePage(WebDriver driver) {
			//initilaize the driver
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
		public void navigateToCampaignPage() {
			Actions action = new Actions(driver);
			action.moveToElement(MoreLink).perform();
			CampaignLink.click();
			
		}
		
		public void Logout() {
			Actions action = new Actions(driver);
			action.moveToElement(Adminimg).perform();
			Signoutbtn.click();
			
		}
		
		
		
		
}
