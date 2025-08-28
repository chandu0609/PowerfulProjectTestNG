package com.qa.pages;

import java.nio.channels.SelectableChannel;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AllControlsDashboardPage {

	private WebDriver driver;
	private WebDriverWait eWait;

	public AllControlsDashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		eWait = new WebDriverWait(this.driver, Duration.ofSeconds(20));

	}

	@FindBy(xpath = "//input[@type = 'text' and @placeholder='Enter full name']")
	private WebElement fullNameTxt;

	@FindBy(xpath = "//input[@type = 'password' and @placeholder='Enter password']")
	private WebElement passwordTxt;

	@FindBy(xpath = "//textarea[text() ='Some sample text' and @rows = '3']")
	private WebElement commentsTextArea;

	@FindBy(xpath = "//input[@type = 'checkbox']")
	private WebElement checkBoxOption;
	
	@FindBy(xpath = "//input[@type ='date']")
	private WebElement calendarInput;
	
	@FindBy(xpath = "//input[@type ='number' and @max = '10']")
	private WebElement counterInput;
	
	@FindBy(xpath = "//input[@type ='file']")
	private WebElement fileInput;
	
	@FindBy(xpath = "//div[@class ='dropzone']")
	private WebElement dropZone;
	
	@FindBy(id = "btnOpenModal")
	private WebElement modalBtn;
	
	@FindBy(id = "btnCloseModal")
	private WebElement closemodalBtn;
	
	@FindBy(id = "btnAlert")
	private WebElement alertShowBtn;
	
	@FindBy(id = "btnConfirm")
	private WebElement btnConfirm;
	
	@FindBy(id = "btnPrompt")
	private WebElement btnPrompt;

	public void inputControlsBasicInputs(String fullname, String password, String comments) {
		fullNameTxt.sendKeys(fullname);
		passwordTxt.sendKeys(password);
		Actions act = new Actions(driver);
		act.click(commentsTextArea).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE)
				.perform();

		commentsTextArea.sendKeys(comments);
	}

	public void inputControlsCheckandRadio(String checkbox, String radiobutton) {
		// TODO Auto-generated method stub
		WebElement checkWE = eWait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//label[contains(text(), '" + checkbox + "')]/input")));
		checkWE.click();

		WebElement radioWE = eWait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//label[contains(text(), '" + radiobutton + "' )]/input")));
		radioWE.click();
	}

	public void inpDropDownCalCountChooseFile(String dropdown, String date, String counter) {
		WebElement dropDownCountry = driver
				.findElement(By.xpath("//option[contains(text(), 'Select country')]/parent::Select"));
		Select dropDown = new Select(dropDownCountry);
		dropDown.selectByVisibleText(dropdown);
		calendarInput.clear();
		calendarInput.sendKeys(date);
		counterInput.clear();
		counterInput.sendKeys(counter);
		
		//fileInput.sendKeys("C:/Users/Chandraalahari/Downloads/QA_Automation_Java_Collections_Cheat_Sheet.pdf");
	
	}

	public void inputdragDrop(String dragdrop) {
		// TODO Auto-generated method stub
		JavascriptExecutor js  = (JavascriptExecutor)driver;
		WebElement dragDropElement = driver.findElement(By.xpath("//div[@class='draggable' and contains(text(), '"+ dragdrop +  "')]"));
		js.executeScript("arguments[0].scrollIntoView(true)", dragDropElement);
		Actions action = new Actions(driver);
		action.dragAndDrop(dragDropElement,dropZone).build().perform();
	}

	public void inputIframe() {
		WebElement iframe = driver.findElement(By.xpath("//iframe"));
		driver.switchTo().frame(iframe);
		WebElement frameBody = driver.findElement(By.xpath("//body"));
		
		frameBody.sendKeys("Let's see whether this works");
		driver.switchTo().defaultContent();
		
		modalBtn.click();
		
		eWait.until(ExpectedConditions.elementToBeClickable(closemodalBtn));
		closemodalBtn.click();
	}

	public void inputAlert() {
		// TODO Auto-generated method stub
		alertShowBtn.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		btnConfirm.click();
	    alert = driver.switchTo().alert();
		alert.accept();
		alert.accept();
		
		btnConfirm.click();
	    alert = driver.switchTo().alert();
		alert.dismiss();
		alert.accept();
		
		btnPrompt.click();
	    alert = driver.switchTo().alert();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.sendKeys("Chandra");
		alert.accept();
		alert.accept();
	}

}
