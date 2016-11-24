package com.mbc.server.ppt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import com.mbc.server.util.ConfigUtil;
import com.mbc.server.util.FileUtil;
import com.mbc.server.util.StringUtil;

public class LineupMaker {
	
	private static final String lineupConfigName = "lineup.properties";
	private static final String folderInputKey = "pptFolderInput";
	private static final String folderOutputKey = "pptFolderOutput";
	private static final String pptExtensionKey = "pptExtension";
	private static final HashMap<String, String> lineupConfig = ConfigUtil.getKeyValueHashMap(lineupConfigName);
	private static final String[] songList = {"IT'S CHRISTMAS", "LAY IT DOWN", "JESUS AT THE CENTER", "TO YOU BE THE GLORY"};

	public static void main(String[] args) {
		createLineupPPT(Arrays.asList(songList), lineupConfig.get(folderInputKey), lineupConfig.get(folderOutputKey));
	}
	
	public static void createLineupPPT(List<String> songList, String folderInput, String folderOutput) {
		try {
			File folder = new File(folderInput);
			XMLSlideShow ppt = new XMLSlideShow();
			
			if (FileUtil.isDirectory(folderInput)) {
				File[] fileList = folder.listFiles();
				
				List<Integer> songPptExistingIndexList = new ArrayList<Integer>();
				
				for (int i = 0; i < fileList.length; i++) {
					File file = fileList[i];
					
					System.out.println("File: " + file.getAbsolutePath());
					
					String pptFileName = FileUtil.getFileNameOnly(file.getAbsolutePath(), lineupConfig.get(pptExtensionKey));
					
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
	
}
