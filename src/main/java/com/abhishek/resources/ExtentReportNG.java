package com.abhishek.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	
	public static ExtentReports getReportObject() {
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\report\\index.html");
		reporter.config().setDocumentTitle("Test Result");
		reporter.config().setReportName("Web Automation");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Abhishek");
		
		return extent;
		
	}

}
