package com.comcast.crm.generic.utility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	public void waitPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public void waitForElementTOpresent(WebDriver driver,WebElement Element) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(Element));
	}
	
	public void switchToFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver driver,String name) {
		driver.switchTo().frame(name);
		
	}
	
	public void switchToFrame(WebDriver driver,WebElement Element) {
		driver.switchTo().frame(Element);
		
	}
	
	public void switchToaccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void switchTodismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public void selectdropdown(WebElement Element,String text) {
		Select s = new Select(Element);
		s.selectByValue(text);
		
	}
	
	public void selectdropdown(WebElement Element,int index) {
		Select s = new Select(Element);
		s.selectByIndex(index);
		
	}
	
	public void mouseMoveElement(WebDriver driver,WebElement Element) {
		Actions a = new Actions(driver);
		a.moveToElement(Element).perform();
		
	}
	
	public void rightclick(WebDriver driver,WebElement Element) {
		Actions a = new Actions(driver);
		a.contextClick(Element).perform();
		
	}
	
	public void doubleclick(WebDriver driver,WebElement element) {
		Actions a = new Actions(driver);
		a.doubleClick(element).perform();
			
	}
	
	
	

}
