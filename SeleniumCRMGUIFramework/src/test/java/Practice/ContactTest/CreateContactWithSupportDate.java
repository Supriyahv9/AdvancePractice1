package Practice.ContactTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.utility.ExcelUtility;
import com.comcast.crm.generic.utility.FileUtility;
import com.comcast.crm.generic.utility.JavaUtility;

public class CreateContactWithSupportDate {

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
		String LASTNAME = eu.getDataFromExcelFile("Contact", 4, 2);
		
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
	
	//current date
	Date objdate = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String startdate = sdf.format(objdate);
	
	//date after 30 days
	Calendar cal = sdf.getCalendar();
	cal.add(Calendar.DAY_OF_MONTH, 30);
	String enddate = sdf.format(cal.getTime());
	
	//Step5:Enter support start date
	driver.findElement(By.name("support_start_date")).clear();
	driver.findElement(By.name("support_start_date")).sendKeys(startdate);
	
	//Step5:Enter support end date
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(enddate);
	
	driver.findElement(By.xpath("(//input[@name='button'])[1]")).click();
	
	//Step5:Verify Header msg Expected result
			String actualStartdate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
			
			
			if(actualStartdate.contains(startdate)) {
				System.out.println(startdate+"is created===Pass");
			}else {
				System.out.println(startdate+"is not created===Fail");
			}
			
			
			
		//	Verify Header orgname info Expected result
			String actenddate = driver.findElement(By.id("dtlview_Support End Date")).getText();
			if(actenddate.equals(enddate)) {
				System.out.println(enddate+"is created===Pass");
			}else {
				System.out.println(enddate+"is not created===Fail");
			}

	
	}

}
