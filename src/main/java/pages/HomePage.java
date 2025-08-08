package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.Utils;
import base.TestBase;

public class HomePage extends TestBase{
	
	String ProductName;
	ArrayList<String> ab;
	
	@FindBy(xpath = "(//img[@class='default-logo'])[1]")
	WebElement logo;
	
	@FindBy(xpath = "(//div//a//span[@class='ct-icon-container'])[3]")
	WebElement Social_Header_icon;
	
	
	
	@FindBy(xpath = "//nav[@id='header-menu-1']//li[contains(@class,'menu-item menu-item-type-post_type')]")
	WebElement Menu;
	
	@FindBy(xpath = "(//h2[@class='woocommerce-loop-product__title']//a)[1]")
	WebElement First_Product_Name;
	
	@FindBy(xpath = "//button[@data-id='search']")
	WebElement Searchbutton;

	@FindBy(xpath = "//input[@type='search']")
	WebElement SearchTextBox;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	
	public String Validate_HomePage_title() {
		String title = driver.getTitle();
		return title;
	}
	
	public boolean Validate_HomePage_Logo() {
		boolean sitelogo = logo.isDisplayed();
		return sitelogo;
	}
	
	public boolean Validate_HomePage_Social_Media() {
		boolean Social_icon = Social_Header_icon.isDisplayed();
		return Social_icon;
	}
	
	public void Validate_HomePage_Menu(){
		
		int j=1;
		
		while(j <=3) {
			WebElement ele = driver.findElement(By.xpath(("(//nav[@id='header-menu-1']//li[contains(@class,'menu-item menu-item-type-post_type')])["+j+"]")));
			
			String w1 = ele.getText();
			System.out.println(w1);
			j++;
		}
		
	}
	public String Get_FirstProduct_Name() {
		
		Utils.ScrollUntil(First_Product_Name);
		
		return ProductName = First_Product_Name.getText();

		//ArrayList<ProductData> test = new ArrayList<>();
		
		//ProductData.ProductName1 = First_Product_Name.getText();
		//		ProductData.ProductName2 = First_Product_Name.getText();

		//	test.add(ProductData);
		
		// ab = new ArrayList<String>();
		
		
		
	}
	public void Search_Functionality(String ProductName) {
		
		//Utils.ScrollUntil(logo);
		
		Utils.Waitfor(Searchbutton, 10);
		Searchbutton.click();
		
		Utils.Waitfor(SearchTextBox, 10);
		SearchTextBox.sendKeys(ProductName);
		
		
	}
}
