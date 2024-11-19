package Practice.ContactTest;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.utility.ExcelUtility;
import com.comcast.crm.generic.utility.FileUtility;
import com.comcast.crm.generic.utility.JavaUtility;

public class CreateContactWithOrg {

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
		String organame = eu.getDataFromExcelFile("Contact", 7, 2);
		String contactlastnam = eu.getDataFromExcelFile("Contact", 7, 3);
		
		
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
		driver.findElement(By.xpath("(//input[@name='button'])[1]")).click();

		
		
		//Step5:Verify Header msg Expected result
		String HeaderInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		System.out.println(HeaderInfo);
		
		if(HeaderInfo.contains(ORGNAME)) {
			System.out.println(ORGNAME+"is created===Pass");
		}else {
			System.out.println(ORGNAME+"is not created===Fail");
		}
		
		
		
	//	Verify Header orgname info Expected result
		String actorgname = driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(actorgname.equals(ORGNAME)) {
			System.out.println(ORGNAME+"is created===Pass");
		}else {
			System.out.println(ORGNAME+"is not created===Fail");
		}
		
		
		//Step6:
		
		
		
		
		      //Step2:navigate to Organization
				driver.findElement(By.linkText("Contacts")).click();
				
				//Step3:Click on Create Organization Module
			driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
			
			//Step4:Enter Last name
			driver.findElement(By.name("lastname")).sendKeys(contactlastnam);
			
			//click on + icon
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		
		//Switch from parent window to child window
		Set<String> allwindow = driver.getWindowHandles();
		
		for(String a : allwindow) {
			driver.switchTo().window(a).getTitle();
		}
		
		
		
		driver.findElement(By.id("search_txt")).sendKeys(organame);
		
		driver.findElement(By.name("search")).click();
		
		driver.findElement(By.xpath("//a[text()='"+organame+"']")).click();
		
		
		//switch from child window to parent window
		
		for(String a : allwindow) {
			driver.switchTo().window(a).getTitle();
			
			
		}
		
		
		
			//driver.findElement(By.xpath("(//input[@name='button'])[1]")).click();

			
			
			//Step5:Verify Header msg Expected result
			
			
		
	}
	
	

}
