package com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.BaseTest.BaseClass;

import org.openqa.selenium.support.events.*;

public class ListenerImplimentationclass implements ISuiteListener, ITestListener {

	public static ExtentReports report;
	public static ExtentTest test ;
	
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("report configuration");
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		ExtentSparkReporter spark = new ExtentSparkReporter("./advancereport/report_"+time+".html");
		spark.config().setDocumentTitle("CRM test suite results");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("CRM report");
	
		//add envirnoment information
				 report = new ExtentReports();
				report.attachReporter(spark);
				report.setSystemInfo("OS", "Windows");
				report.setSystemInfo("Browser", "Chrome");
	}

	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		System.out.println("Report backup");
		
		//To take backup
		report.flush();
	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("========="+result.getMethod().getMethodName()+"===Test Start=====");
		 test = report.createTest(result.getMethod().getMethodName());
		 test.log(Status.INFO, result.getMethod().getMethodName()+"====Started=====");
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("========="+result.getMethod().getMethodName()+"===Test end=====");
		 test.log(Status.PASS, result.getMethod().getMethodName()+"====Completed=====");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String testname = result.getMethod().getMethodName();
		TakesScreenshot ts = (TakesScreenshot)BaseClass.sdriver;
		String filepath = ts.getScreenshotAs(OutputType.BASE64);
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		File destfile = new File("./screenshot/"+testname+""+time+".png");
		/*try {
			FileUtils.copyFile(destfile, filepath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		test.addScreenCaptureFromBase64String(filepath,testname+"_"+time);
		 test.log(Status.FAIL, result.getMethod().getMethodName()+"====Failed=====");
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	

	}



