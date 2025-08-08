package testcases;

import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.TestBase;
import pages.HomePage;

public class HomePageTest extends TestBase implements ITestListener{
	
	HomePage homepage;
	String ProductName;

	public HomePageTest() {
		super();
	}
	
	SoftAssert sa = new SoftAssert();
	@BeforeClass
	public void Setup() {
		Initilization();
		homepage = new HomePage();
		
	}
	
	@Test(priority = 1)
	public void Validate_HomePage_Title() {
		String title = homepage.Validate_HomePage_title();
		Assert.assertEquals(title, "eKharidi – Your Reliable Wholesale Supplier of Top-Selling Products – Your Reliable Wholesale Supplier of Top-Selling Products");
	}
	
	@Test(priority = 2, dependsOnMethods = {"Validate_HomePage_Title"})
	public void Validate_HomePage_logo() {
		boolean logo = homepage.Validate_HomePage_Logo();
		sa.assertTrue(logo);
	}
	
	@Test(priority = 3)
	public void Validate_Social_Media() {
		boolean socialmedia_icon = homepage.Validate_HomePage_Social_Media();
		sa.assertTrue(socialmedia_icon);
	}
	
	@Test(priority = 4)
	public void Validate_ManuText() {
		homepage.Validate_HomePage_Menu();
		
	}
	
	@Test(priority = 5)
	public void Validate_Get_Product_Text() {
		ProductName = homepage.Get_FirstProduct_Name();
		
	}
	
	@Test(priority = 6)
	public void Validate_Search_Product_Functionality() {
		 homepage.Search_Functionality(ProductName);
		
	}
	
	
	@AfterClass
	public void quit() {
		driver.quit();
	}
	
}
