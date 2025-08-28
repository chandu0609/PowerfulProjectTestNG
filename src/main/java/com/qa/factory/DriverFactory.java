package com.qa.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static ThreadLocal<WebDriver> m_TLDriver = new ThreadLocal<WebDriver>(); //Threadlocal //Chandra_Dev
	private static DriverFactory instance = null;
	
	private DriverFactory() {}
	
	public static DriverFactory getInstance() {
		if(instance == null) {
			instance = new DriverFactory();
		}
		return instance;
	}

	public WebDriver initiateBrowser(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			// options.addArguments("--headless");
			//options.addArguments("--disable-gpu");
			options.addArguments("--window-size=1920,1080");
			//options.addArguments("--incognito");
			//options.addArguments("--disable-extensions");
			m_TLDriver.set(new ChromeDriver(options));

		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			// options.addArguments("--headless");
			options.addArguments("--width=1920");
			options.addArguments("--height=1080");
			options.addArguments("--private");
			options.addArguments("--ignore-certificate-errors");
			m_TLDriver.set(new FirefoxDriver(options));
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();
			// options.addArguments("--headless");
			options.addArguments("--disable-gpu");
			options.addArguments("--window-size=1920,1080");
			options.addArguments("--inprivate");
			options.addArguments("--disable-extensions");
			options.addArguments("--ignore-certificate-errors");
			m_TLDriver.set(new EdgeDriver(options));
		} else {
			throw new IllegalArgumentException("Browser is not supported - " + browser);
		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		return getDriver();
	}

	public synchronized static WebDriver getDriver() {
		return m_TLDriver.get();
	}
	
	public static void quitBrowser() {
		if(getDriver() != null) {
			getDriver().quit();
			m_TLDriver.remove();
		}
	}

}
