package com.mbc.server.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class ConfigUtil {
	
	private static final String projectRootPath = System.getProperty("user.dir");
	private static final String configFolder = "config";
	
	public static HashMap<String, String> getKeyValueHashMap(String configFilename) {
		HashMap<String, String> keyValueHashMap = new HashMap<String, String>();
		
		String configPath = projectRootPath + File.separator + configFolder + File.separator + configFilename;
		
		try {
			Properties prop = new Properties();
			InputStream input;
			input = new FileInputStream(configPath);
			
			prop.load(input);
			
			Set<Object> keys = prop.keySet();
			
	        for (Object k:keys) {
	            String key = (String) k;
	            keyValueHashMap.put(key, prop.getProperty(key));
	        }
			
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return keyValueHashMap;
	}
	
	public static String getValue(String configFilename, String key) {
		String value = null;
		
		String configPath = projectRootPath + File.separator + configFolder + File.separator + configFilename;
		
		try {
			Properties prop = new Properties();
			InputStream input;
			input = new FileInputStream(configPath);
			
			prop.load(input);
			
			value = prop.getProperty(key);
			
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return value;
	}
	
	public static void changeProperty(String filename, String key, String value) {
		try {
			final File tmpFile = new File(filename + ".tmp");
		    final File file = new File(filename);
		    
		    PrintWriter pw = new PrintWriter(tmpFile);
		    BufferedReader br = new BufferedReader(new FileReader(file));
		    
		    boolean found = false;
		    final String toAdd = key + '=' + value;
		    
		    for (String line; (line = br.readLine()) != null; ) {
		        if (line.startsWith(key + '=')) {
		            line = toAdd;
		            found = true;
		        }
		        pw.println(line);
		    }
		    
		    if (!found) {
		    	pw.println(toAdd);
		    }
		    
		    br.close();
		    pw.close();
		    tmpFile.renameTo(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
