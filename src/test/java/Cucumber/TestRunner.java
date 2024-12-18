package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features ="src\\test\\java\\Cucumber", glue = "saurabhjaggi.stepdefination", 
monochrome = true,tags = "@Regression", plugin = {"pretty" , "html:target/cucumber.html"} )
public class TestRunner extends AbstractTestNGCucumberTests{

}
