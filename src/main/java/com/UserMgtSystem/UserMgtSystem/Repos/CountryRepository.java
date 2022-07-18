package com.UserMgtSystem.UserMgtSystem.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.UserMgtSystem.UserMgtSystem.Entities.Country;
@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

	Country findByCountryName(String name);

	

	

}
