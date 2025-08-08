package Listeners;

import java.io.File;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.print.attribute.DateTimeSyntax;
import javax.swing.text.Utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.devtools.v135.page.model.Screenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.github.dockerjava.core.MediaType;

import base.TestBase;
import bsh.util.Util;

public class MyListeners extends TestBase implements ITestListener{
	
	public static ExtentSparkReporter sparkReporter; // UI of the report
	public static ExtentReports extent; // populate common information of the report like config, environment, tester name, etc..
	public static ExtentTest test;//Create test case entery in report 

	TakesScreenshot ts;
    String base64Screenshot;
	
	@Override
	public void onStart(ITestContext context) {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss"));
		 
		
		//specifiy where you want to store report
		sparkReporter = new  ExtentSparkReporter(System.getProperty("user.dir") + "/reports/ExtentReport_" + timestamp + ".html");
		
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		//create extent report object and pass into Spark reporter
		// SparkRepoter attached with extent report
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Computer Name", "Local Host");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester Name", "Bhaumik Gohel");
		extent.setSystemInfo("Operating System", "10");
		extent.setSystemInfo("Browser", "Chrome");
		
		Reporter.log("On Start");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ts = (TakesScreenshot) driver;
		base64Screenshot = ts.getScreenshotAs(OutputType.BASE64);
		
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test Case PASSED is:"+ result.getName());
		 test.pass("Screenshot of success",MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		try {
	        // Take screenshot as Base64
	        
			ts = (TakesScreenshot) driver;
			base64Screenshot = ts.getScreenshotAs(OutputType.BASE64);
	      
			test.log(Status.FAIL, "Test Case FAILED is Due to "+ result.getThrowable());
			test = extent.createTest(result.getName()).addScreenCaptureFromBase64String(base64Screenshot);

	    } catch (Exception e) {
	        test.log(Status.FAIL, "Exception while taking screenshot: " + e.getMessage());
	    }
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Case SKIPED is:"+ result.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
