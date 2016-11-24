package com.mbc.server.ppt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import com.mbc.server.util.StringUtil;

public class LineupMaker {
	
	private static final String[] songList = {"IT'S CHRISTMAS", "LAY IT DOWN", "JESUS AT THE CENTER", "TO YOU BE THE GLORY"};

	public static void main(String[] args) {
		String folderInput = "C:/Users/IBM_ADMIN/Desktop/OneDrive-2016-11-24";
		String folderOutput = "C:/Users/IBM_ADMIN/Desktop/OneDrive-2016-11-24/Merged/Merged.pptx";
		
		createLineupPPT(Arrays.asList(songList), folderInput, folderOutput);
	}
	
	public static void createLineupPPT(List<String> songList, String folderInput, String folderOutput) {
		try {
			File folder = new File(folderInput);
			XMLSlideShow ppt = new XMLSlideShow();
			
			if (folder.isDirectory()) {
				File[] fileList = folder.listFiles();
				
				List<Integer> songPptExistingIndexList = new ArrayList<Integer>();
				
				for (int i = 0; i < fileList.length; i++) {
					File file = fileList[i];
					
					System.out.println("File: " + file.getAbsolutePath());
					
					String pptFileName = getFileNameOnly(file.getAbsolutePath(), ".pptx");
					
					if (StringUtil.isNotEmptyOrNull(pptFileName) && songList.contains(pptFileName)) {
						songPptExistingIndexList.add(songList.indexOf(pptFileName));
						
						FileInputStream is = new FileInputStream(file);

						XMLSlideShow src = new XMLSlideShow(is);
						is.close();
						
						for (XSLFSlide srcSlide : src.getSlides()) {
							ppt.createSlide().importContent(srcSlide);
						}
						
						src.close();
					}
				}
				
				for (int i = 0; i < songList.size(); i++) {
					if (!songPptExistingIndexList.contains(i)) {
						System.out.println("Song \"" + songList.get(i) + "\"  has no Powerpoint Presentation yet.");
					}
				}
				
				if (songPptExistingIndexList.size() == songList.size()) {
					FileOutputStream out = new FileOutputStream(folderOutput);
					
					ppt.write(out);
					out.close();
				}
			}
			
			ppt.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String getFileNameWithExtension(String fullPath) {
		String fileName = null;
		
		if (StringUtil.isNotEmptyOrNull(fullPath) && new File(fullPath).isFile()) {
			int fileSeparatorindex = fullPath.lastIndexOf(File.separator);
			fileName = fullPath.substring(fileSeparatorindex + 1);
		}
		
		return fileName;
	}
	
	private static String getFileNameWithExtension(String fullPath, String extension) {
		String fileName = null;
		
		if (StringUtil.isNotEmptyOrNull(fullPath) && new File(fullPath).isFile() && fullPath.endsWith(extension)) {
			int fileSeparatorindex = fullPath.lastIndexOf(File.separator);
			fileName = fullPath.substring(fileSeparatorindex + 1);
		}
		
		return fileName;
	}
	
	private static String getFileNameOnly(String fullPath) {
		String fileName = null;
		
		if (StringUtil.isNotEmptyOrNull(fullPath) && new File(fullPath).isFile()) {
			int fileSeparatorindex = fullPath.lastIndexOf(File.separator);
			fileName = fullPath.substring(fileSeparatorindex + 1);
			
			int dotIndex = fileName.lastIndexOf(".");
			fileName = fileName.substring(0, dotIndex);
		}
		
		return fileName;
	}
	
	private static String getFileNameOnly(String fullPath, String extension) {
		String fileName = null;
		
		if (StringUtil.isNotEmptyOrNull(fullPath) && new File(fullPath).isFile() && fullPath.endsWith(extension)) {
			int fileSeparatorindex = fullPath.lastIndexOf(File.separator);
			fileName = fullPath.substring(fileSeparatorindex + 1);
			
			int dotIndex = fileName.lastIndexOf(".");
			fileName = fileName.substring(0, dotIndex);
			
			System.out.println("fileName: " + fileName);
		}
		
		return fileName;
	}

}
