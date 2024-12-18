package framework;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import saurabhjaggi.TestComponents.BaseTest;
import saurabhjaggi.TestComponents.Retry;
import saurabhjaggi.page_object_model.B_productCatalog;
import saurabhjaggi.page_object_model.C_Header;
import saurabhjaggi.page_object_model.D_checkout;
import saurabhjaggi.page_object_model.OrdersPage;

public class A_login2 extends BaseTest {

	
	String[] prod = new String[] { "ADIDAS ORIGINAL"}; // ,"IPHONE 13 PRO"
	List<String> productNeeded = Arrays.asList(prod);
	
	@Test(dataProvider = "getData", groups = {"purchase"}, retryAnalyzer = Retry.class)
	public void AddToCart(HashMap<String,String> map) throws IOException, InterruptedException
	 {
		
		LandingPage.goTo();
		

		B_productCatalog productCatalog = LandingPage.logInApplication(map.get("email"), map.get("password"));
		productCatalog.getProductList();
		productCatalog.selectProduct(productNeeded);

		C_Header header = productCatalog.addProductToCart(productNeeded);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
//		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		Thread.sleep(2000);

		header.cart();
		Boolean cart = header.cartValid(productNeeded);
		Assert.assertTrue(cart);
		
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("window.scrollTo(0,1000)");
		
		D_checkout checkout = header.checkout();

		checkout.countryName("Ind");
		checkout.selectCountry("India");
		String confirmText = checkout.confirmation(); 
		System.out.println(confirmText);
		Assert.assertTrue("Thankyou for the order.".equalsIgnoreCase(confirmText));

		/*
		 * driver.manage().window().maximize(); WebDriverWait wait = new
		 * WebDriverWait(driver, Duration.ofSeconds(3));
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
		 * ".mb-3"))); List<WebElement> products =
		 * driver.findElements(By.cssSelector(".mb-3")); for (int i = 0; i <
		 * products.size(); i++) { String pro =
		 * products.get(i).findElement(By.cssSelector("b")).getText(); if
		 * (productNeeded.contains(pro)) {
		 * 
		 * products.get(i).findElement(By.cssSelector(".mb-3 button:last-child")).click(
		 * ); wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.
		 * cssSelector("#toast-container")))); } }
		 */
	}
	
	
	

	@Test(dependsOnMethods = {"AddToCart"})
	public void Orders() {
		LandingPage.goTo();
		B_productCatalog productCatalog = LandingPage.logInApplication("qua@gmail.com", "Qa@12345");
		OrdersPage orderpage = productCatalog.gotoOrdersPage();
		List<String>productTexts =  orderpage.checkOrders(productNeeded);
		Assert.assertTrue(productTexts.containsAll(productNeeded));
	}
	
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
//		Object[][] data = new Object[2][3];
		
//		data[0][0] = "qua@gmail.com" ;
//		data[0][1] = "Qa@12345";
//		data[0][2] = productNeeded;
//		
//		data[1][0] = "asdf@gmail.com" ;
//		data[1][1] = "asdf";
//		data[1][2] = productNeeded;
//		
//		return data;
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "qua@gmail.com");
//		map.put("password", "Qa@12345");
////		map.put("product", "ADIDAS ORIGINAL");
//		
//		HashMap<String,String> map2 = new HashMap<String,String>();
//		map2.put("email", "asdf@gmail.com");
//		map2.put("password", "asdf");
		
		List<HashMap<String,String>> data = getJSONdata(System.getProperty("user.dir")+ "//src//test//java//JSON//file.json");
		
		return new Object[][] {{data.get(0)} ,{data.get(1)}};	
		
		
	}
	
	
}
