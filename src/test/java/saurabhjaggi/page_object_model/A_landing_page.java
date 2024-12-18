package saurabhjaggi.page_object_model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class A_landing_page extends AbstractComponents {
			
	
	WebDriver driver;
	
	//constructor 
	public A_landing_page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//pagefactory
		PageFactory.initElements( driver, this);
		
		
	}
	
	// Locating elements using @FindBy annotations
	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	@FindBy(id = "userPassword")
	WebElement password;
	
	@FindBy(id = "login")
	WebElement submit;
	
	
	
	//actions
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public B_productCatalog logInApplication(String username, String pass) {
		userEmail.sendKeys(username);
		password.sendKeys(pass);
		submit.click();
		B_productCatalog productCatalog = new B_productCatalog(driver);
		return productCatalog;
	}
}
