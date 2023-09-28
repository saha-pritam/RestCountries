package org.pritam.restCountries.services;

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
}
