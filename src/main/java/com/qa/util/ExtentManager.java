package com.qa.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	public static ExtentReports extent;

	public synchronized static ExtentReports getExtentReports() {
		if (extent == null) {
			ExtentSparkReporter spark = new ExtentSparkReporter(
					System.getProperty("user.dir") + "/reports/ExtentReport.html");
			spark.config().setDocumentTitle("Automation Report");
			spark.config().setReportName("UI Test Report");

			extent = new ExtentReports();
			extent.attachReporter(spark);

			// Optional system info
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("Browser", System.getProperty("browser", "chrome"));
		}
		return extent;
	}
}
