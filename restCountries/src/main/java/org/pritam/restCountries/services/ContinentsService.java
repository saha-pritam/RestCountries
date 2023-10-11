package org.pritam.restCountries.services;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.repository.ContinentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ContinentsService {

	@Autowired
	private ContinentsRepository continentsRepository;

	@Transactional
	public List<String> getContinents(String cca2) {
		Optional<List<String>> continents = continentsRepository.findByCca2(cca2);
		if (continents.isPresent() && continents.get().size() > 0)
			return continents.get();
		return null;
	}
}
