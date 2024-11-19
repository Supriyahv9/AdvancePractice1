package PracticeTest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.BaseTest.BaseClass;

@Listeners(com.comcast.crm.listenerutility.ListenerImplimentationclass.class)
public class InvoiceTest extends BaseClass {
	
	@Test(retryAnalyzer=com.comcast.crm.listenerutility.RetryListenerImp.class)
	public void CreateOrg() {
		
	String	actualtitle=driver.getTitle();
	Assert.assertEquals(actualtitle, "Login");
	System.out.println("fetching the title");
	Reporter.log("fetching the title",true);
		
	}
	
	

}
