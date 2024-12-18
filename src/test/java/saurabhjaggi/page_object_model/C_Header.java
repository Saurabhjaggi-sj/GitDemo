package saurabhjaggi.page_object_model;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class C_Header extends AbstractComponents {

	WebDriver driver;

	public C_Header(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[contains(text(),'Cart ')]")
	WebElement cart;

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartItems;

	@FindBy(xpath = "//div/ul/li/button")
	WebElement checkOut;

	
	
	By checkout = By.xpath("//div/ul/li/button");

	public void cart() {
		cart.click();
	}

	public Boolean cartValid(List<String> productNeeded) {
		Boolean cart = cartItems.stream().allMatch(a -> productNeeded.contains(a.getText()));
		return cart;
	}

	public D_checkout checkout() {
		WaitforElements(checkout);
		checkOut.click();
		D_checkout checkout = new D_checkout(driver);
		return checkout;
	}

	
}
