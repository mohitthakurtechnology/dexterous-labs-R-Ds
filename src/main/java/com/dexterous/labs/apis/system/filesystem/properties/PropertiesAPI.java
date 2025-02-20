package com.dexterous.labs.apis.system.filesystem.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * 
 */
public class PropertiesAPI 
{
	Properties prop;
	
	public Properties loadProperties(String fileName)
	{
		String filePath = System.getProperty("user.dir") + File.separator + fileName + ".properties";
		try {
				FileInputStream fis = new FileInputStream(filePath);
				prop = new Properties();
				prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	public String getProperty(String key)
	{
		return prop.getProperty(key);
	}
}
