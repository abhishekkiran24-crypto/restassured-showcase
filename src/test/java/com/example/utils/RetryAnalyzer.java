package com.example.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	private int retryCount=0;
	private static int maxRetryCount=2;//retries a test 2 times if it fails
	
	
	public boolean retry(ITestResult result) {
		if(retryCount<maxRetryCount)
		{
			retryCount++;
			System.out.println("Retrying test:"+result.getName()+" |Attempt: " +(retryCount + 1));
			return true;//re-run test
		}
		return false;
		
	}

}
