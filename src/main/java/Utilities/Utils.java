package Utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class Utils extends TestBase{

	public static void Waitfor(WebElement e, int i) {
		
		WebDriverWait wb = new WebDriverWait (driver,Duration.ofSeconds(i));
		wb.until(ExpectedConditions.visibilityOf(e));
		
	}
	
	public static void ScrollUntil(WebElement e) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", e);
	}
	
	
	public static String captureScreenshotBase64() {
		TakesScreenshot ts = (TakesScreenshot) driver;
		String src = ts.getScreenshotAs(OutputType.BASE64);
		return src;
	}
	
	public static String captureScreenshot(String Filelane) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./Screenshot/"+Filelane);
		
		try {
			FileUtils.copyFile(src, destFile);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return destFile.getAbsolutePath();
	}
}
