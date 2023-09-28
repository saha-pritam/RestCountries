package org.pritam.restCountries.services;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.repository.TldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class TldService {

	@Autowired
	private TldRepository tldRepository;

	@Transactional
	public List<String> getTld(String cca2) {
		Optional<List<String>> tld = tldRepository.findByCca2(cca2);
		if (tld.isPresent())
			return tld.get();
		return null;
	}
}
