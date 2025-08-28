package com.qa.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerHelper {
	 public static Logger getLogger(Class<?> clazz) {
	        return LogManager.getLogger(clazz);
	    }

}
