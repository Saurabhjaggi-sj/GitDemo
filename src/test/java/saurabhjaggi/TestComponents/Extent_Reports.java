package saurabhjaggi.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Extent_Reports {

	
	public static ExtentReports intialTest() {
		
		
		String path  = System.getProperty("user.dir") + "//report//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation");
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setDocumentTitle("Report");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Saurabh");
		return extent;
		
	}
	
}
