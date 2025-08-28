package com.qa.util;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

	private static ThreadLocal<ExtentTest> mTest = new ThreadLocal<>();

	public static ExtentTest getTest() {
		return mTest.get();
	}

	public static void setTest(ExtentTest test) {
		mTest.set(test);
	}

	public static ExtentTest createTest(String testName) {
		ExtentTest test = ExtentManager.getExtentReports().createTest(testName);
		setTest(test);
		return test;
	}
}
