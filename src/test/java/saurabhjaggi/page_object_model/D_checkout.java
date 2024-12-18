package saurabhjaggi.page_object_model;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class D_checkout extends AbstractComponents {

	
	WebDriver driver;

	public D_checkout(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "(//div/input[@class = 'input txt text-validated'])[2]")
	WebElement CountryName;
	
	@FindBy(css = "button .ng-star-inserted")
	List<WebElement>  countryList;
	
	@FindBy(css =".btnn")
	WebElement checkOut ;
	
	@FindBy(xpath = "//tbody//tr[4]//h1")
	WebElement confirm;
	
	By countrySearch = By.cssSelector("button .ng-star-inserted");
	
	
	public void countryName(String selectCountry) {
		WebElement countryName = CountryName;
		Actions a = new Actions(driver);
		a.moveToElement(countryName).click().sendKeys(selectCountry).build().perform();
		WaitforElements(countrySearch);
	}
	
	
	public void selectCountry(String Country) {
		
	
		List<WebElement> countries = countryList;
		WebElement country = countries.stream().filter(con -> con.getText().equalsIgnoreCase(Country)).findFirst()
				.orElse(null);
		country.click();
		checkOut.click();
		
	
	}
	public String confirmation() {
		String confirmText = confirm.getText();
		return confirmText;
	}
	
	
	
}
