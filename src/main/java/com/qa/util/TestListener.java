package com.qa.util;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.ByteArrayInputStream;

import com.qa.factory.DriverFactory;

import io.qameta.allure.Allure;

public class TestListener implements ITestListener {

	protected Logger log = LoggerHelper.getLogger(TestListener.class);

	public void onTestStart(ITestResult result) {
		ExtentTestManager.createTest(result.getName());
		log.info("Test Method Started " + result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		ExtentTestManager.getTest().pass(result.getName());
		log.info("Test Method Success " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		// ExtentTestManager.getTest().fail(result.getTestName());
		log.error("Test Method Failure " + result.getName());
		File src = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
		byte[] srcBytes = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
		String desSrc = System.getProperty("user.dir") + "/Screenshot/" + result.getName() + System.currentTimeMillis()
				+ ".png";

		try {
			FileUtils.copyFile(src, new File(desSrc));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ExtentTestManager.getTest() != null) {
			ExtentTestManager.getTest().fail(result.getName());
			ExtentTestManager.getTest().fail("Screenshot of Failure",
					com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromPath(desSrc).build());
		} else {
			log.error("ExtentTest not found for " + result.getName());
		}

		Allure.addAttachment("Failure Screenshot", "image/png",  new ByteArrayInputStream(srcBytes), "png");

	}

	public void onTestSkipped(ITestResult result) {
		ExtentTestManager.getTest().skip(result.getName());
		log.warn("Test Method Skipped " + result.getName());
	}

	public void onFinish(ITestContext context) {
		log.info("Test Class Finish " + context.getName());
		ExtentManager.getExtentReports().flush();
	}

}
