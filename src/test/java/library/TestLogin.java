package library;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.courselist;
import pages.homescreen;
import resources.base;
import resources.Utility;



public class TestLogin extends base {
	
	SoftAssert sa;
	courselist cs;

	@BeforeTest(alwaysRun=true)
	public void setupnow() throws IOException {
		driver=setup();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		sa = new SoftAssert();
	}
	
	@Test(groups= {"smoke"})
	public void testlogin() throws IOException, InterruptedException {
		
		homescreen hs = new homescreen(driver);
		hs.clicklogin();
		explicitwait(20,hs.email);
		//sa.assertTrue(hs.pwddisplayed());
		hs.sendemail();
		Thread.sleep(5000);
		hs.sendpwd();	
		Thread.sleep(5000);
		cs=hs.signin();
		sa.assertTrue(!hs.faildisplayed());
		sa.assertAll();
		
		
	}
	
	@Test(groups= {"regression"})    
	public void testcourseclick() throws InterruptedException {
		Thread.sleep(1000);
		cs.clickcourse("JavaScript for beginners");
	}
	
	@AfterTest(alwaysRun=true)
	public void closeDriver() throws InterruptedException {
		Thread.sleep(10000);
		driver.quit();
	}
	
	@AfterMethod(alwaysRun=true)
	public void getSS(ITestResult result) throws IOException {
		if (ITestResult.FAILURE== result.getStatus()) {
			Utility.takess(result.getName());
		}
	}
}
