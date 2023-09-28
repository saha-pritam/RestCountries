package org.pritam.restCountries.services;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.repository.AltSpellingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class AltSpellingsService {

	@Autowired
	private AltSpellingsRepository altSpellingsRepository;

	@Transactional
	public List<String> getAltSpellings(String cca2) {
		Optional<List<String>> altSpellings = altSpellingsRepository.findByCca2(cca2);
		if (altSpellings.isPresent())
			return altSpellings.get();
		return null;
	}
}
