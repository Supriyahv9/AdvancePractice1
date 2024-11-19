package PracticeTest;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class CreateContactTest {
	
	@Test 
	public void sampleTest() {
		//spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./advancereport/report.html");
		spark.config().setDocumentTitle("CRM test suite results");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("CRM report");
		
		//add envirnoment information
		ExtentReports report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows");
		report.setSystemInfo("Browser", "Chrome");
		ExtentTest test = report.createTest("sampleTest");
		
		test.log(Status.INFO,"Log in to application");
		test.log(Status.INFO,"navigate to contact page");
		test.log(Status.INFO,"create contact");
		if("HDFC".equals("HDFC")) {
			test.log(Status.PASS,"Contact is created");
		}else {
			test.log(Status.FAIL,"Contact is not created");
		}
		//To take backup
		report.flush();
		test.log(Status.INFO,"Logout to application");
	}

}
