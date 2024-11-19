package Practice.ContactTest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.utility.ExcelUtility;
import com.comcast.crm.generic.utility.FileUtility;
import com.comcast.crm.generic.utility.JavaUtility;

public class CreateContact {
	
	public static void main(String[] args) throws IOException {
		
		
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
		String LASTNAME = eu.getDataFromExcelFile("Contact", 1, 2);
		
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
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step3:Click on Create Organization Module
	driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
	
	//Step4:Enter Last name
	driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
	driver.findElement(By.xpath("(//input[@name='button'])[1]")).click();

	
	
	//Step5:Verify Header msg Expected result
	String HeaderInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	
	
	if(HeaderInfo.contains(LASTNAME)) {
		System.out.println(LASTNAME+"is created===Pass");
	}else {
		System.out.println(LASTNAME+"is not created===Fail");
	}
	
	
	
//	Verify Header orgname info Expected result
	String actorgname = driver.findElement(By.id("dtlview_Last Name")).getText();
	if(actorgname.equals(LASTNAME)) {
		System.out.println(LASTNAME+"is created===Pass");
	}else {
		System.out.println(LASTNAME+"is not created===Fail");
	}
	
	
	
	
	
	
	}

}
