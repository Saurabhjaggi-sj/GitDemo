package saurabhjaggi.stepdefination;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import saurabhjaggi.TestComponents.BaseTest;
import saurabhjaggi.page_object_model.A_landing_page;
import saurabhjaggi.page_object_model.B_productCatalog;
import saurabhjaggi.page_object_model.C_Header;
import saurabhjaggi.page_object_model.D_checkout;

public class Stepdefination extends BaseTest {

	
	public	 A_landing_page LandingPage;
	public  B_productCatalog productCatalog ;
	public C_Header header;
	public D_checkout checkout;
	
	@Given("I landed on loging page")
	public void I_landed_on_loging_page() throws IOException {
		
		LandingPage = launchTest();
		LandingPage.goTo();
	}
	
	@Given ("^I logged in with username (.+) and password (.+)$")
	public void I_logged_in_with_username_and_password(String username, String password) {
		productCatalog	= LandingPage.logInApplication(username,password);
	}
	
	@When("I added following product to cart")
	public void I_added_product_to_cart(List<String> productName) throws InterruptedException {
		productCatalog.getProductList();
		productCatalog.selectProduct(productName);
		header = productCatalog.addProductToCart(productName);
		Thread.sleep(2000);
	}
	
	@And("checkout the product and submit the order")
	public void checkout_the_product_and_submit_the_order(List<String> productName) {
		header.cart();
		Boolean cart = header.cartValid(productName);
		Assert.assertTrue(cart);
		checkout = header.checkout();
		checkout.countryName("Ind");
		checkout.selectCountry("India");
		
	}
	@Then("{string} is displayed on confirmation page")
	public void confirmation_msg_is_displayed(String string) {
		
		String confirmText = checkout.confirmation(); 
		System.out.println(confirmText);
		Assert.assertTrue(confirmText.equalsIgnoreCase(string));
		driver.close();
	}

}
