package com.UserMgtSystem.UserMgtSystem.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.UserMgtSystem.UserMgtSystem.Entities.Country;
import com.UserMgtSystem.UserMgtSystem.Repos.CountryRepository;
import com.UserMgtSystem.UserMgtSystem.Service.CountryService;
import com.UserMgtSystem.UserMgtSystem.dto.CountryDto;
import com.UserMgtSystem.UserMgtSystem.dto.CountryWrapperDTO;

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
	
	
}
