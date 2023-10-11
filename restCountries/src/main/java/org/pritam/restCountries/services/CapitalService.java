package org.pritam.restCountries.services;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.repository.CapitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CapitalService {

	@Autowired
	private CapitalRepository capitalRepository;

	@Transactional
	public List<String> getCapital(String cca2) {
		Optional<List<String>> capital = capitalRepository.findByCca2(cca2);
		if (capital.isPresent())
			return capital.get();
		return null;
	}
	
	@Transactional
	public List<String> getCountryByCapitalName(String capitalName) {
		Optional<List<String>> cca2List= capitalRepository.findByCapital(capitalName);
		if(cca2List.isPresent() && cca2List.get().size()>0)
			return cca2List.get();
		return null;
	}
}
