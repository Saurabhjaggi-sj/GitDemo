package saurabhjaggi.page_object_model;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class B_productCatalog extends AbstractComponents {

	WebDriver driver;

	// constructor
	public B_productCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// pagefactory
		PageFactory.initElements(driver, this);

	}

//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	// Locating elements using @FindBy annotations
	@FindBy(css = ".mb-3")
	List<WebElement> products;

	
	@FindBy(css = "#toast-container")
	WebElement toast;
	
	@FindBy(css = "[routerlink *= 'myorders']")
	WebElement orders;
	
	By product = By.cssSelector(".mb-3");
	By toster = By.cssSelector("#toast-container");
	By selectProduct = By.cssSelector(".mb-3 button:last-child");
	
	
	public List<WebElement> getProductList() {

		WaitforElements(product);
		return products;

	}

	public List<WebElement> selectProduct(List<String> productNeeded) {
		
		List<WebElement> pro =  getProductList().stream()
				.filter(product -> productNeeded.stream().anyMatch(name -> product.findElement(By.cssSelector("b"))
						.getText().contains(name))).collect(Collectors.toList());
		return pro;
		
	}
	
	public C_Header addProductToCart(List<String> productNeeded) {
		
		selectProduct(productNeeded).stream().forEach(a->{
			a.findElement(selectProduct).click();
			WaitForElementToDisapper(toast);
		});
		
		C_Header header = new C_Header(driver);
		return header;
	}
	
	public OrdersPage gotoOrdersPage() {
		orders.click();
		OrdersPage orderspage = new OrdersPage(driver);
		return orderspage;
	}
	

	
}
