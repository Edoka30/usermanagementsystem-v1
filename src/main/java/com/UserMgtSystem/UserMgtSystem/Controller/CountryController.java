package com.UserMgtSystem.UserMgtSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.UserMgtSystem.UserMgtSystem.Entities.Country;
import com.UserMgtSystem.UserMgtSystem.Service.CountryService;

@RestController
@RequestMapping("/api/v1")
public class CountryController {

	@Autowired
	CountryService countryService;

	@GetMapping(value = "/fetch-countries")
	public ResponseEntity<?> ftechCountries()throws Exception {
		return new ResponseEntity<>(countryService.fetchCountries(), HttpStatus.CREATED);
	}

	@GetMapping(value = "/countries")
	public List<Country> getAllcountries() {
		return countryService.getAllcountries();

	}

}
