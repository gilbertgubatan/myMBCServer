package com.mbc.server.util;

import java.io.File;

public class FileUtil {
	
	public static boolean isFile(String fullPath) {
		if (StringUtil.isNotEmptyOrNull(fullPath)) {
			return new File(fullPath).isFile();
		} else {
			return false;
		}
	}
	
	public static boolean isDirectory(String fullPath) {
		if (StringUtil.isNotEmptyOrNull(fullPath)) {
			return new File(fullPath).isDirectory();
		} else {
			return false;
		}
	}
	
	public static String getFileNameWithExtension(String fullPath) {
		String fileName = null;
		
		if (StringUtil.isNotEmptyOrNull(fullPath) && isFile(fullPath)) {
			int fileSeparatorindex = fullPath.lastIndexOf(File.separator);
			fileName = fullPath.substring(fileSeparatorindex + 1);
		}
		
		return fileName;
	}
	
	public static String getFileNameWithExtension(String fullPath, String extension) {
		String fileName = null;
		
		if (StringUtil.isNotEmptyOrNull(fullPath) && isFile(fullPath) && fullPath.endsWith(extension)) {
			int fileSeparatorindex = fullPath.lastIndexOf(File.separator);
			fileName = fullPath.substring(fileSeparatorindex + 1);
		}
		
		return fileName;
	}
	
	public static String getFileNameOnly(String fullPath) {
		String fileName = null;
		
		if (StringUtil.isNotEmptyOrNull(fullPath) && isFile(fullPath)) {
			int fileSeparatorindex = fullPath.lastIndexOf(File.separator);
			fileName = fullPath.substring(fileSeparatorindex + 1);
			
			int dotIndex = fileName.lastIndexOf(".");
			fileName = fileName.substring(0, dotIndex);
		}
		
		return fileName;
	}
	
	public static String getFileNameOnly(String fullPath, String extension) {
		String fileName = null;
		
		if (StringUtil.isNotEmptyOrNull(fullPath) && isFile(fullPath) && fullPath.endsWith(extension)) {
			int fileSeparatorindex = fullPath.lastIndexOf(File.separator);
			fileName = fullPath.substring(fileSeparatorindex + 1);
			
			int dotIndex = fileName.lastIndexOf(".");
			fileName = fileName.substring(0, dotIndex);
			
			System.out.println("fileName: " + fileName);
		}
		
		return fileName;
	}
	
}
