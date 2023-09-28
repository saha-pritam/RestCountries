package org.pritam.restCountries.services;

import java.util.Optional;

import org.pritam.restCountries.entity.Country;
import org.pritam.restCountries.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	@Transactional
	public Iterable<Country> getAllCountries() {
		return countryRepository.findAll();
	}
	
	@Transactional
	public Country getCountryByCca2(String cca2) {
		Optional<Country> country= countryRepository.findById(cca2);
		if(country.isPresent())
			return country.get();
		return null;
	}
}
