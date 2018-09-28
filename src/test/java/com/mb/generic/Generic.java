package com.mb.generic;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Generic 
{
/**
 * @description read data from config.properties file based on key	
 * @param key
 * @return
 */
public static String getValue(String key) {
	String value = null;
	Properties prop = new Properties();
	try {
		prop.load(new FileInputStream(new File("./config.properties")));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	value = prop.getProperty(key);
	return value;
}
}

