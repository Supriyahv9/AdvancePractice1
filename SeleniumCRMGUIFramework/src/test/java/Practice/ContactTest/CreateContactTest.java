package Practice.ContactTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.BaseTest.BaseClass;
import com.comcast.crm.generic.utility.ExcelUtility;
import com.comcast.crm.generic.utility.JavaUtility;

public class CreateContactTest extends BaseClass {
	
	
	
	@Test
	public void ContactTest() throws EncryptedDocumentException, IOException {
		
		//read data from excel
		String LASTNAME = eu.getDataFromExcelFile("Contact", 1, 2);
				
		//Step2:navigate to Organization
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step3:Click on Create Organization Module
	driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
	
	//Step4:Enter Last name
	driver.findElement(By.name("lastname")).sendKeys(LASTNAME+ju.getRandomNumber());
	driver.findElement(By.xpath("(//input[@name='button'])[1]")).click();

	
	
	//Step5:Verify Header msg Expected result
	String HeaderInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	
	
	/*if(HeaderInfo.contains(LASTNAME)) {
		System.out.println(LASTNAME+"is created===Pass");
	}else {
		System.out.println(LASTNAME+"is not created===Fail");
	}*/
		boolean status = HeaderInfo.contains(LASTNAME);
	Assert.assertEquals(status, true);
	
	
//	Verify Header orgname info Expected result
	String actorgname = driver.findElement(By.id("dtlview_Last Name")).getText();
	/*if(actorgname.equals(LASTNAME)) {
		System.out.println(LASTNAME+"is created===Pass");
	}else {
		System.out.println(LASTNAME+"is not created===Fail");
	}*/
	boolean name = actorgname.equals(LASTNAME);
	SoftAssert s = new SoftAssert();
	s.assertEquals(name,LASTNAME);
	s.assertAll();
	
	
	
	
	
	}

}
