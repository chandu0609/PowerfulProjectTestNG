package com.qa.Tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.factory.DriverFactory;
import com.qa.pages.LoginPage;
import com.qa.util.RetryAnalyzer;
import com.qa.util.TestListener;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

	private LoginPage loginPage;

	@BeforeClass
	public void setUp() throws FileNotFoundException, IOException {
		super.setUpClass();
		base_url = System.getenv("BASE_URL");
		if (base_url == null || base_url.isEmpty()) {
			base_url = prop.getProperty("mainurl");
		}
	}

	@BeforeMethod
	public void setUpMethod() throws FileNotFoundException, IOException {
		super.setUpMethod();
		loginPage = new LoginPage(DriverFactory.getDriver());
		WaitforPageLoad();
	}

	@Test(priority = 0, alwaysRun = true, groups = "smoke", description = "Login test are executed - Assert Page Title", retryAnalyzer = RetryAnalyzer.class)
	@Description("The Title Page Text Test")
	public void onionToolWebTitle() {
		String title = loginPage.getTitle();
		Assert.assertTrue(title.contains("Sign in to Onion"));
	}

	@Test(priority = 1, alwaysRun = true, groups = "regression", description = "Forgot password link exist", retryAnalyzer = RetryAnalyzer.class)
	@Epic("Home Page Epic")
	@Feature("Home Page features")
	@Story("Home Page Story")
	@Severity(SeverityLevel.CRITICAL)
	public void forgotPasswordLinkExists() {
		loginPage.clickForgotPasswordLink();
		boolean forgotText = loginPage.forgotPassWordPageShown();
		Assert.assertTrue(forgotText);
	}

	@Test(priority = 2, alwaysRun = true, description = "User Login Test", retryAnalyzer = RetryAnalyzer.class)
	@Epic("User Login Epic")
	@Feature("User Login features")
	@Story("User Login Story")
	@Severity(SeverityLevel.CRITICAL)
	public void userLoginTest() {
		loginPage.enterUserName("onion@gnapi.tech");
		loginPage.enterPassword("Gnapi@1234");
		loginPage.clickSignIn();
		String landingPageText = loginPage.extractTitleOnHomePage();
		Assert.assertEquals(landingPageText, "Onion: Complete Test Management Platform");
	}

}
