package com.comcast.BaseTest;

import java.io.IOException;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.ObjectRepository.HomePage;
import com.comcast.crm.ObjectRepository.LoginPage;
import com.comcast.crm.generic.utility.ExcelUtility;
import com.comcast.crm.generic.utility.FileUtility;
import com.comcast.crm.generic.utility.JavaUtility;

public class BaseClass {
	
	public WebDriver driver=null;
	public static WebDriver sdriver=null;
	public FileUtility fu = new FileUtility();
	public ExcelUtility eu = new ExcelUtility();
	public JavaUtility ju = new JavaUtility();
	
	
	@BeforeSuite(groups= {"smokeTest","regressionTest"})
	public void BS() {
		
		
		
		
	}
	
	
	
	//@Parameters("browser")
	@BeforeClass(groups= {"smokeTest","regressionTest"})
	//public void BC(String browser) throws IOException {
		//String BROWSER=browser;
	public void BC() throws IOException {
		String BROWSER=fu.getDataFromPropertyFile("browser");
		String URL=fu.getDataFromPropertyFile("url");
		
		if(BROWSER.equals("chrome")) {
			 driver = new ChromeDriver(); 
		}else if(BROWSER.equals("edge")) {
			 driver = new EdgeDriver();
		}else if(BROWSER.equals("firefox")) {
			 driver = new FirefoxDriver(); 
		}else {
			driver=new ChromeDriver();
		}
		
		sdriver=driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
	}
	
	@BeforeMethod(groups= {"smokeTest","regressionTest"})
	public void BM() throws IOException {
		String URL=fu.getDataFromPropertyFile("url");
		String USERNAME=fu.getDataFromPropertyFile("username");
		String PASSWORD=fu.getDataFromPropertyFile("password");
		
		LoginPage lp = new LoginPage(driver);
		lp.LoginToApp(URL, USERNAME, PASSWORD);
		
	}
	
	@AfterMethod(groups= {"smokeTest","regressionTest"})
	public void AM() {
		//Step5:Logout from application
		HomePage hp = new HomePage(driver);
		hp.Logout();
	}
	
	@AfterClass(groups= {"smokeTest","regressionTest"})
	public void AC() {
		driver.quit();
	}
	
	@AfterSuite(groups= {"smokeTest","regressionTest"})
	public void AS() {
		
	}
	
	

}
