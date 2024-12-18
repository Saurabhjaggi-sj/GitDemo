package framework;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import saurabhjaggi.TestComponents.BaseTest;

public class A_login extends BaseTest{

	
	@Test(groups = {"Unorganised"})
	public void cartTest() throws InterruptedException, IOException {
		
		LandingPage.goTo();
		
		
		driver.findElement(By.id("userEmail")).sendKeys("asdf@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("qwer");
		driver.findElement(By.id("login")).click();

		String[] prod = new String[] { "ADIDAS ORIGINAL" }; // ,"IPHONE 13 PRO"
		List<String> productNeeded = Arrays.asList(prod);

		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		List<WebElement> pro = products.stream()
				.filter(product -> productNeeded.stream()
						.anyMatch(name -> product.findElement(By.cssSelector("b")).getText().contains(name)))
				.collect(Collectors.toList());

		pro.stream().forEach(a -> {
			a.findElement(By.cssSelector(".mb-3 button:last-child")).click();
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		});

//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
//		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		Thread.sleep(2000);

		driver.findElement(By.xpath("//button[contains(text(),'Cart ')]")).click();

		List<WebElement> cartItem = driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean cart = cartItem.stream().allMatch(a -> productNeeded.contains(a.getText()));

		Assert.assertTrue(cart);
		System.out.println(cart);

//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("window.scrollTo(0,500)");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/ul/li/button")));

		driver.findElement(By.xpath("//div/ul/li/button")).click();

		Actions a = new Actions(driver);

		WebElement countryName = driver.findElement(By.xpath("(//div/input[@class = 'input txt text-validated'])[2]"));

		a.moveToElement(countryName).click().sendKeys("Ind").build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button .ng-star-inserted")));

		List<WebElement> countries = driver.findElements(By.cssSelector("button .ng-star-inserted"));

		WebElement country = countries.stream().filter(con -> con.getText().equalsIgnoreCase("india")).findFirst()
				.orElse(null);

		country.click();

		driver.findElement(By.cssSelector(".btnn")).click();
		driver.close();
		
	}
}
