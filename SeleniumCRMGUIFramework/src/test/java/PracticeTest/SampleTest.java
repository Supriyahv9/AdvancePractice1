package PracticeTest;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.BaseTest.BaseClass;

public class SampleTest {
	public ExtentReports report;
	@BeforeSuite
	public void BS() {
		ExtentSparkReporter spark = new ExtentSparkReporter("./advancereport/report1.html");
		spark.config().setDocumentTitle("CRM test suite results");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("CRM report");
	
		//add envirnoment information
				 report = new ExtentReports();
				report.attachReporter(spark);
				report.setSystemInfo("OS", "Windows");
				report.setSystemInfo("Browser", "Chrome");
	}
	
	@AfterSuite
	public void AS() {
		//To take backup
		report.flush();
	}
	
	@Test
	public void CreateContactTest() {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.irctc.com/about.html");
		TakesScreenshot ts = (TakesScreenshot)driver;
		String filepath = ts.getScreenshotAs(OutputType.BASE64);
		
		
		
		
		ExtentTest test = report.createTest("sampleTest");
		test.log(Status.INFO,"Log in to application");
		test.log(Status.INFO,"navigate to contact page");
		test.log(Status.INFO,"create contact");
		if("HDFCadd".equals("HDFCS")) {
			test.log(Status.PASS,"Contact is created");
		}else {
			test.addScreenCaptureFromBase64String(filepath,"errorfile");
		}
		
		
		test.log(Status.INFO,"Logout to application");	
	}

}
