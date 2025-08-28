package com.qa.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.qa.factory.ConfigReader;
import com.qa.factory.DriverFactory;
import com.qa.util.LoggerHelper;

public class BaseTest {

	protected String browser;
	protected Logger log;
	protected Properties prop;
	protected String base_url;

	
	public void setUpClass() throws FileNotFoundException, IOException {
		log = LoggerHelper.getLogger(this.getClass());
		prop = ConfigReader.readConfigProperties();
		browser = System.getenv("BROWSER");
		if (browser == "" || browser == null)
			browser = "chrome"; // assign default
		log.info("Browser selected is - " + browser);
	}

	protected void setUpMethod() throws FileNotFoundException, IOException {
		DriverFactory.getInstance().initiateBrowser(browser);
		DriverFactory.getDriver().get(base_url);
		
	}

	protected void WaitforPageLoad() {
		new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(20))
				.until(ExpectedConditions.titleContains("Sign"));

	}

	@AfterMethod
	public void tearDownDriver() {
		DriverFactory.quitBrowser();
	}
}
