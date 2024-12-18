package saurabhjaggi.page_object_model;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage extends AbstractComponents {

	
	
	WebDriver driver;
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	
	@FindBy(xpath = "//tr/td[2]")
	List<WebElement> prod;
	
	
	public List<String> checkOrders(List<String> productNeeded) {
	
		List<WebElement> filteredProducts = prod.stream()
			    .filter(a -> productNeeded.stream().anyMatch(product -> a.getText().contains(product)))
			    .collect(Collectors.toList());
		List<String> productTexts = filteredProducts.stream()
			    .map(WebElement::getText)
			    .collect(Collectors.toList());
		return productTexts;
		}
}
