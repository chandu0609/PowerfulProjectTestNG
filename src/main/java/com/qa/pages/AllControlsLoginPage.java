package com.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllControlsLoginPage {
	
	private WebDriver driver;
	
	public AllControlsLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@placeholder='Enter username']")
	private WebElement usernameTxtName;
	
	@FindBy(xpath = "//input[@placeholder='Enter password']")
	private WebElement passwordTxt;
	
	@FindBy(xpath = "//button[@type='submit' and text() = 'Login']")
	private WebElement submitBtn;

	public void loginUser(String username, String password) {
		usernameTxtName.sendKeys(username);
		passwordTxt.sendKeys(password);
		submitBtn.click();
	}
	
	public AllControlsDashboardPage loginUserToDashboard(String username, String password) {
		usernameTxtName.sendKeys(username);
		passwordTxt.sendKeys(password);
		submitBtn.click();
		return new AllControlsDashboardPage(driver);
	}

}
