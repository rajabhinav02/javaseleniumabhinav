package library;

import static org.testng.Assert.fail;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Utility;

public class Listener implements ITestListener{
	
	ExtentReports ext = Utility.getextreport();
	ExtentTest tst;

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		tst=ext.createTest(result.getName());
		System.out.println("ha");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		tst.log(Status.PASS, "test passed");
		System.out.println("na");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		tst.fail(result.getThrowable());
		String path = System.getProperty("user.dir")+"\\reports\\"+result.getName()+".png";
		tst.addScreenCaptureFromPath(path);
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ext.flush();
		System.out.println("ka");
		
	}

}
