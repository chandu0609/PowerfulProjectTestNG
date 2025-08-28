package com.qa.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private static Properties prop;
	
	private ConfigReader() {}

	public static Properties readConfigProperties() throws FileNotFoundException, IOException {

		if (prop == null) { //Don't create object if it already exists
			prop = new Properties();
			String configFilePath = System.getProperty("user.dir") + "/src/main/resources/config.properties";

			try (FileInputStream fis = new FileInputStream(configFilePath)) {
				prop.load(fis);
			}
		}
		return prop;
	}
}
