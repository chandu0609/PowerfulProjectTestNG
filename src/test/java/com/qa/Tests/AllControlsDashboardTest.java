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
import com.qa.pages.AllControlsDashboardPage;
import com.qa.pages.AllControlsLoginPage;
import com.qa.util.CSVUtil;
import com.qa.util.RetryAnalyzer;
import com.qa.util.TestListener;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Features;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(TestListener.class)
public class AllControlsDashboardTest extends BaseTest {

	private AllControlsDashboardPage allcontrolsDashboardPage;
	String credCSV = System.getProperty("user.dir") + "/src/main/resources/testDataUser.csv";
	String credCSVInputs = System.getProperty("user.dir") + "/src/main/resources/testDataControlInputs.csv";

	@BeforeClass
	public void setUpControlsPage() throws FileNotFoundException, IOException {
		super.setUpClass();
		base_url = System.getenv("CONTROLS_URL");
		if (base_url == null || base_url.isEmpty()) {
			base_url = prop.getProperty("controlsurl");
		}
	}

	@BeforeMethod
	public void setupDriver() throws FileNotFoundException, IOException {
		super.setUpMethod();

	}

	@DataProvider(name = "loginData")
	private Iterator<Object[]> ReadCSVCreds() throws CsvValidationException, IOException {
		return CSVUtil.readCSV(credCSV, true);
	}

	@DataProvider(name = "InputData")
	private Iterator<Object[]> ReadCSVInputData() throws CsvValidationException, IOException {
		return CSVUtil.readCSV(credCSVInputs, true);
	}

	@Test(dataProvider = "loginData", groups = "smoke",  retryAnalyzer = RetryAnalyzer.class)
	@Epic("Login Selenium Page")
	@Feature("Login Selenium Feature")
	@Story("Login Selenium Story")
	@Severity(SeverityLevel.NORMAL)
	private void Login(String username, String password) {
		allcontrolsDashboardPage = new AllControlsLoginPage(DriverFactory.getDriver()).loginUserToDashboard(username,
				password);
	}

	@Test(dataProvider = "InputData", groups = "regression", retryAnalyzer = RetryAnalyzer.class)
	@Epic("Input Selenium Page")
	@Feature("Input Selenium Feature")
	@Story("Input Selenium Story")
	@Severity(SeverityLevel.NORMAL)
	private void inputDiffControls(String fullname, String password, String	comments, String checkbox, 
			         String	radiobutton, String	dropdown, String date, String counter, String dragdrop) {
		allcontrolsDashboardPage = new AllControlsLoginPage(DriverFactory.getDriver()).loginUserToDashboard(fullname,
				password);
		allcontrolsDashboardPage.inputControlsBasicInputs(fullname, password, comments);
		allcontrolsDashboardPage.inputControlsCheckandRadio(checkbox, radiobutton );
		allcontrolsDashboardPage.inpDropDownCalCountChooseFile(dropdown,date, counter );
		allcontrolsDashboardPage.inputdragDrop(dragdrop);
		allcontrolsDashboardPage.inputIframe();
		allcontrolsDashboardPage.inputAlert();
	}

}
