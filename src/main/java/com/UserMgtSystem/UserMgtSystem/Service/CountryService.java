package com.UserMgtSystem.UserMgtSystem.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.UserMgtSystem.UserMgtSystem.Entities.Country;
public interface CountryService {

	public List<Country> fetchCountries();
	
	
	public List<Country> getAllcountries()  ;

	public String uploadCsvfile( MultipartFile file) throws Exception ;
	//public void uploadExcel(MultipartFile file);


//	public void save(MultipartFile file);
	
	
	
	
	
}
