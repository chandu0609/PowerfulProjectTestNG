package com.qa.util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	private static int retryDone = 0;
	private static int maxRetryCount = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		if(retryDone < maxRetryCount) {
			retryDone++;
			return true;
		}
		return false;
	}

}
