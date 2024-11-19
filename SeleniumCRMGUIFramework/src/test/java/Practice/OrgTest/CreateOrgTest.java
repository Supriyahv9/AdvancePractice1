package Practice.OrgTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.BaseTest.BaseClass;
import com.comcast.crm.generic.utility.ExcelUtility;
import com.comcast.crm.generic.utility.JavaUtility;
import com.comcast.crm.listenerutility.ListenerImplimentationclass;

@Listeners(com.comcast.crm.listenerutility.ListenerImplimentationclass.class)
public class CreateOrgTest extends BaseClass{
	
	
	
	@Test(groups="smokeTest")
	public void OrgTest() throws EncryptedDocumentException, IOException {
		
		ListenerImplimentationclass.test.log(Status.INFO, "read data from excel");
		
		//read data from excel
				String ORGNAME = eu.getDataFromExcelFile("Organization", 1, 2);
				
				
		//Step2:navigate to Organization
				ListenerImplimentationclass.test.log(Status.INFO, "navigate to Org Page");
				driver.findElement(By.linkText("Organizations")).click();
				
				//Step3:Click on Create Organization Module
				ListenerImplimentationclass.test.log(Status.INFO, "navigate to create Org Page");
				driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
				
				//Step4:Enter all detail & create new Organization
				driver.findElement(By.name("accountname")).sendKeys(ORGNAME+ju.getRandomNumber());
				driver.findElement(By.xpath("(//input[@name='button'])[1]")).click();
	}
	
	@Test(groups="regressionTest")
	public void CreateOrganizationWithIndustry() throws EncryptedDocumentException, IOException {
		
		//read data from excel
		String ORGNAME = eu.getDataFromExcelFile("Organization", 1, 2);
		String INDUSTRY = eu.getDataFromExcelFile("Organization", 4, 3);
		String TYPE = eu.getDataFromExcelFile("Organization", 4, 4);
		
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
				
			
	}
	
	@Test(groups="regressionTest")
	public void CreateOrganizationWithPhoneNumber() throws EncryptedDocumentException, IOException {
		
		//read data from excel
	String ORGNAME = eu.getDataFromExcelFile("Organization", 1, 2);
	String PHONENUM = eu.getDataFromExcelFile("Organization", 7, 3);
		
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
		
		
	
	
	
	
	
	
	
	
	
	}

}
