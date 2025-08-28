package com.qa.Tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;
import com.qa.base.BaseTest;
import com.qa.factory.DriverFactory;
import com.qa.pages.AllControlsLoginPage;
import com.qa.util.CSVUtil;
import com.qa.util.RetryAnalyzer;
import com.qa.util.TestListener;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(TestListener.class)
public class AllControlsLogin extends BaseTest {

	AllControlsLoginPage allctrlLoginPage;
	String credCSV = System.getProperty("user.dir") + "/src/main/resources/testDataUser.csv";

	@BeforeClass
	public void setUpControlsPage() throws FileNotFoundException, IOException {
		super.setUpClass();
		base_url = System.getenv("CONTROLS_URL");
		if (base_url == null || base_url.isEmpty()) {
			base_url = prop.getProperty("controlsurl");
		}
		
	}

	@BeforeMethod
	public void setUpMethod() throws FileNotFoundException, IOException {
		super.setUpMethod();
		allctrlLoginPage = new AllControlsLoginPage(DriverFactory.getDriver());
	}

	@DataProvider (name = "loginData")
	private Iterator<Object[]> ReadCSVCreds() throws CsvValidationException, IOException {
		return CSVUtil.readCSV(credCSV, true);
	}

	@Test (dataProvider = "loginData", groups = "smoke", retryAnalyzer = RetryAnalyzer.class)
	@Epic("Login Selenium Page")
	@Feature("Login Selenium Feature")
	@Story("Login Selenium Story")
	@Severity(SeverityLevel.NORMAL)
	public void inputSimpleControls(String username, String password) {
		allctrlLoginPage.loginUser(username, password);
	}

}
