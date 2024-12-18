package saurabhjaggi.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import saurabhjaggi.page_object_model.A_landing_page;

public class BaseTest {

	public WebDriver driver;
	public A_landing_page LandingPage;

	public WebDriver initializeDriver() throws IOException {

		// properties class
		Properties prop = new Properties();
		// change file into stream by using FileInputStream
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "//src//test//java//resources//Global.properties");
		prop.load(file);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
//		prop.getProperty("browser");

		if (browserName.contains("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			
			if(browserName.contains("headless")) {
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);

		} else if (browserName.equalsIgnoreCase("FireFox")) {
			// firefox
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("Edge")) {
			// edge

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}

	
	
	public List<HashMap<String, String>> getJSONdata(String filePath) throws IOException {
		//read json to string through FileUtils
	String jsonContent = 	FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		//covert String to HashMap using Jackson Databind
	ObjectMapper mapper  = new ObjectMapper();
	
	List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
	
	return data;
	
	}
	
	
	public String takeScreenshot(String testCase, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File output = ts.getScreenshotAs(OutputType.FILE);
		File path = new File(System.getProperty("user.dir") + "//report//" + testCase + ".jpg");
		FileUtils.copyFile(output, path);
		return System.getProperty("user.dir") + "//report//" + testCase + ".jpg" ; 
	}
	
	
	@BeforeMethod(alwaysRun = true)
	public A_landing_page launchTest() throws IOException {
		driver = initializeDriver();
		LandingPage = new A_landing_page(driver);
		return LandingPage;
	}

	@AfterMethod(alwaysRun = true) // alwaysRun = true is used to run these method in every case even when you run
									// a particular groups method from xml
	public void tearDown() {
		if(driver != null) {
			try {
			driver.close();
			}catch(NoSuchSessionException e){
				System.out.println("Session already closed " + e.getMessage());
			}
		}
	}
}
