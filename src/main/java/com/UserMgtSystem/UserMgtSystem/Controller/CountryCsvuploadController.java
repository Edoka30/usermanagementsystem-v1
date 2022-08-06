package com.UserMgtSystem.UserMgtSystem.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.UserMgtSystem.UserMgtSystem.Entities.Country;
import com.UserMgtSystem.UserMgtSystem.Repos.CountryRepository;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParserSettings;

@RestController
@RequestMapping("/api/v1")
public class CountryCsvuploadController {
	@Autowired
	CountryRepository countryRepo;

	@PostMapping("/uploadcsv")
	public String csvfileUpload(@RequestParam("file") MultipartFile file) throws Exception {
		List<Country> Listcountry = new ArrayList<>();
		InputStream inputstream = file.getInputStream();
		CsvParserSettings settings = new CsvParserSettings();
		settings.setHeaderExtractionEnabled(true);
		CsvParser parser = new CsvParser(settings);
		parser.parseAllRecords(inputstream);
		List<Record> parserRecords = parser.parseAllRecords(inputstream);
		parserRecords.forEach(record -> {
			Country country = new Country();
			country.setId(Integer.parseInt("id"));
			country.setCountryCode(record.getString("countryCode"));
			country.setCountryName(record.getString("countryName"));
			country.setShortCode(record.getString("shortCode"));
			Listcountry.add(country);
		});
		countryRepo.saveAll(Listcountry);

		return "upload successful";
	}

	@PostMapping("/uploadingcsv")
	public String uploadCsvfile(@RequestParam("file") MultipartFile file) throws Exception {
		if(countryRepo.count()>1) {
			return "Country Table is not empty";
		}
		String filename = "upload/" + getFileName() + "_" + file.getOriginalFilename();
		File newfile = multipartToFile(filename, file);
		InputStreamReader streamReader = new InputStreamReader(new FileInputStream(newfile), StandardCharsets.UTF_8);

		BufferedReader reader = null;
		try {
			
			// Read through the file
			reader = new BufferedReader(streamReader);
			reader.readLine();
			String data;
			List<Country> countryList = new ArrayList<>();
			while ((data = reader.readLine()) != null) {
				// System.out.println("Reding file: " + fileCount);
				String[] line = data.trim().split(",");
				if (line.length == 0) {
					continue;
				}
				String countryCode = line[0];
				String countryName = line[1];
				String ShortCode = line[2];
				Country country = new Country();
				country.setCountryCode(countryCode);
				country.setCountryName(countryName);
				country.setShortCode(ShortCode);
				countryList.add(country);

			}

			if (countryList.size() > 0) {
				countryRepo.saveAll(countryList);

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		return "Upload successful";

	}

	private String getFileName() {

		SimpleDateFormat datetimeformatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String datetime = datetimeformatter.format(new Date());

		return datetime;
	}

	private File multipartToFile(String fileName, MultipartFile file) throws Exception {
		File convFile = new File(fileName);
		if (!convFile.getParentFile().exists()) {
			// LOGGER.info("directory does not exist. It will be created");
			convFile.getParentFile().mkdirs();
		}

		FileUtils.writeByteArrayToFile(convFile, file.getBytes());
		return convFile;

	}
}
