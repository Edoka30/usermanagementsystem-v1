package com.UserMgtSystem.UserMgtSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.UserMgtSystem.UserMgtSystem.Entities.Country;
import com.UserMgtSystem.UserMgtSystem.Service.CountryService;

@RestController
@RequestMapping("/api/v1")
public class CountryController {

	@Autowired
	CountryService countryService;

	@GetMapping(value = "/countries")
	public ResponseEntity<?> ftechCountries() {
		try {
			return new ResponseEntity<>(countryService.fetchCountries(), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@GetMapping(value = "/fetch-countries")
	public List<Country> getAllcountries() {
		return countryService.getAllcountries();

	}

	public String csvUploadCountryList(@RequestParam("file") MultipartFile file) throws Exception {
		// countryService.uploadCsvfile(file);
		return countryService.uploadCsvfile(file);
	}
	
}
