package Practice.OrgTest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.ObjectRepository.CreateNewOrganizationPage;
import com.comcast.crm.ObjectRepository.HomePage;
import com.comcast.crm.ObjectRepository.LoginPage;
import com.comcast.crm.ObjectRepository.OrganizationInformationPage;
import com.comcast.crm.ObjectRepository.OrganizationPage;
import com.comcast.crm.generic.utility.ExcelUtility;
import com.comcast.crm.generic.utility.FileUtility;
import com.comcast.crm.generic.utility.JavaUtility;


public class CreateOrgPomTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		WebDriver driver=null;
		JavaUtility ju = new JavaUtility();
		

		FileUtility fu = new FileUtility();
		ExcelUtility eu = new ExcelUtility();
		
		//read data from property file
	String BROWSER=fu.getDataFromPropertyFile("browser");
	String URL=fu.getDataFromPropertyFile("url");
	String USERNAME=fu.getDataFromPropertyFile("username");
	String PASSWORD=fu.getDataFromPropertyFile("password");
	
	//read data from excel
			String ORGNAME = eu.getDataFromExcelFile("Organization", 1, 2);
			
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
			
			
			
			//Step1:Login to application
			LoginPage lp = new LoginPage(driver);
			lp.LoginToApp(URL,USERNAME, PASSWORD);
			
			//Step2:navigate to organization module
			HomePage hp= new HomePage(driver);
			hp.getOrgLink().click();
			
			//Step3:Click on Create Organization Button
			OrganizationPage op = new OrganizationPage(driver);
			op.getCreateOrgLink().click();
			
			//Step4:Enter details & create new Organization
			CreateNewOrganizationPage cop = new CreateNewOrganizationPage(driver);
			cop.CreateOrg(ORGNAME+ju.getRandomNumber());
			
			
			//Step5:Verify Header msg Expected result
			OrganizationInformationPage oip = new OrganizationInformationPage(driver);
			String actorgname=oip.getHeaderinfo().getText();
			if(actorgname.contains(ORGNAME)) {
				System.out.println(ORGNAME+"is verified---pass");
			}else {
				System.out.println(ORGNAME+"is not verified---fail");
			}
			
			//Step6:Signout
		hp.Logout();
			

	}

}
