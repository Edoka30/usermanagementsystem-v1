package com.UserMgtSystem.UserMgtSystem.ServiceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.UserMgtSystem.UserMgtSystem.Entities.Country;
import com.UserMgtSystem.UserMgtSystem.Repos.CountryRepository;
import com.UserMgtSystem.UserMgtSystem.Service.CountryService;
import com.UserMgtSystem.UserMgtSystem.dto.CountryDto;
import com.UserMgtSystem.UserMgtSystem.dto.CountryWrapperDTO;
import com.UserMgtSystem.UserMgtSystem.utils.CsvHelper;

@Service
public class CountryServiceImpl implements CountryService {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private CountryRepository repos;

	@Override
	public List<Country> fetchCountries() {

		String url = "https://countriesnow.space/api/v0.1/countries/codes";
		ResponseEntity<CountryWrapperDTO> response = restTemplate.exchange(url, HttpMethod.GET, null,
				CountryWrapperDTO.class);
		List<CountryDto> countries = response.getBody().getData();

		List<Country> countryList = new ArrayList<>();
		for (CountryDto c : countries)
		{
			if (repos.findByCountryName(c.getName()) != null) {
				continue;
			}
			Country country = new Country();
			country.setCountryCode(c.getCode());
			country.setCountryName(c.getName());
			country.setShortCode(c.getDial_code());
			countryList.add(country);

		}

		if (countryList.size() > 0) {
			repos.saveAll(countryList);
		}

		return repos.findAll();
	}
	public List<Country> getAllcountries()  {                                                                                                                                                                            {
		return repos.findAll();
	//	return new Re
	}
	}
	public String uploadCsvfile( MultipartFile file) throws Exception {
	if(repos.count()>1) {
		return "Country Table is not empty";
	}
	CsvHelper helper  = new CsvHelper();
	String filename = "upload/" + helper.getFileName() + "_" + file.getOriginalFilename();
	File newfile = helper.multipartToFile(filename, file);
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
			repos.saveAll(countryList);

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
//
//private String getFileName() {
//
//	SimpleDateFormat datetimeformatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//	String datetime = datetimeformatter.format(new Date());
//
//	return datetime;
//}
//
//private File multipartToFile(String fileName, MultipartFile file) throws Exception {
//	File convFile = new File(fileName);
//	if (!convFile.getParentFile().exists()) {
//		// LOGGER.info("directory does not exist. It will be created");
//		convFile.getParentFile().mkdirs();
//	}
//
//	FileUtils.writeByteArrayToFile(convFile, file.getBytes());
//	return convFile;
//
//}
} 
	

