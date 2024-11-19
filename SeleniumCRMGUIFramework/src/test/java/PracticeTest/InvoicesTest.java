package PracticeTest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class InvoicesTest {
	
	@Test
	public void CreateOrg() {
		
	String	actualtitle=driver.getTitle();
	Assert.assertEquals(actualtitle, "Login");
	System.out.println("fetching the title");
	Reporter.log("fetching the title",true);
		
	}

}
