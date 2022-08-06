package com.UserMgtSystem.UserMgtSystem.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;


public class CsvHelper {

public String getFileName() {

	SimpleDateFormat datetimeformatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	String datetime = datetimeformatter.format(new Date());

	return datetime;
}

public File multipartToFile(String fileName, MultipartFile file) throws Exception {
	File convFile = new File(fileName);
	if (!convFile.getParentFile().exists()) {
		// LOGGER.info("directory does not exist. It will be created");
		convFile.getParentFile().mkdirs();
	}

	FileUtils.writeByteArrayToFile(convFile, file.getBytes());
	return convFile;

}
	}



