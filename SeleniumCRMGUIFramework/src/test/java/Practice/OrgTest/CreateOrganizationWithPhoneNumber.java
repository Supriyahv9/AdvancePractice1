package Practice.OrgTest;

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

public class CreateOrganizationWithPhoneNumber {

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
		String PHONENUM = eu.getDataFromExcelFile("Organization", 7, 3);
		
		
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
		
	//Step5:Enter Phone number
		driver.findElement(By.id("phone")).sendKeys(PHONENUM);
	
		//Click on save button
				driver.findElement(By.xpath("(//input[@name='button'])[1]")).click();

				
				//verify phone number
				String actphonenum = driver.findElement(By.id("dtlview_Phone")).getText();
				if(actphonenum.equals(PHONENUM)) {
					System.out.println(PHONENUM +"information is verified");
				}else
				System.out.println(PHONENUM +"information is not verified");
	
	
	driver.quit();
	
	
	
	}

}
