package Practice.OrgTest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.utility.ExcelUtility;
import com.comcast.crm.generic.utility.FileUtility;
import com.comcast.crm.generic.utility.JavaUtility;

public class CreateOrganizationWithIndustry {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
WebDriver driver=null;
		

		FileUtility fu = new FileUtility();
		ExcelUtility eu = new ExcelUtility();
		JavaUtility ju = new JavaUtility();
		
		//read data from property file
		String BROWSER=fu.getDataFromPropertyFile("browser");
		String URL=fu.getDataFromPropertyFile("url");
		String USERNAME=fu.getDataFromPropertyFile("username");
		String PASSWORD=fu.getDataFromPropertyFile("password");
		
		//read data from excel
		String ORGNAME = eu.getDataFromExcelFile("Organization", 1, 2);
		String INDUSTRY = eu.getDataFromExcelFile("Organization", 4, 3);
		String TYPE = eu.getDataFromExcelFile("Organization", 4, 4);
		
		if(BROWSER.equals("chrome")) {
			 driver = new ChromeDriver(); 
		}else if(BROWSER.equals("edge")) {
			 driver = new EdgeDriver();
		}else if(BROWSER.equals("firefox")) {
			 driver = new FirefoxDriver(); 
		}else {
			driver=new ChromeDriver();
		}
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		
		//Step1:Login to application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step2:navigate to Organization
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step3:Click on Create Organization Module
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		//Step4:Enter all detail & create new Organization
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME+ju.getRandomNumber());
		
		//handle industry dropdown
		WebElement industrydropdown = driver.findElement(By.name("industry"));
		Select si = new Select(industrydropdown);
		si.selectByVisibleText(INDUSTRY);
		
		//handle type dropdown
		WebElement typedropdown = driver.findElement(By.name("accounttype"));
		Select st = new Select(typedropdown);
		st.selectByVisibleText(TYPE);
		
		//Click on save button
		driver.findElement(By.xpath("(//input[@name='button'])[1]")).click();

		
		//verify industry & type info
		String actindustry = driver.findElement(By.id("dtlview_Industry")).getText();
		if(actindustry.equals(INDUSTRY)) {
			System.out.println(INDUSTRY +"information is verified");
		}else
		System.out.println(INDUSTRY +"information is not verified");
		
		
		String acttype = driver.findElement(By.id("dtlview_Type")).getText();
		if(acttype.equals(TYPE)) {
			System.out.println(TYPE +"information is verified");
		}else
		System.out.println(TYPE +"information is not verified");
		
		  
	/*	  //Step5:Logout from application WebElement img 
		  WebElement img = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		 Actions action = new Actions(driver); 
		 action.moveToElement(img);
		 
		 driver.findElement(By.linkText("Sign Out")).click();*/
		 
		  
		  driver.quit();
		 

	}

}
