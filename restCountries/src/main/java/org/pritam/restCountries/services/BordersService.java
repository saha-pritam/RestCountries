package org.pritam.restCountries.services;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.repository.BordersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class BordersService {

	@Autowired
	private BordersRepository bordersRepository;

	@Transactional
	public List<String> getBorders(String cca2) {
		Optional<List<String>> borders = bordersRepository.findByCca2(cca2);
		if (borders.isPresent() && borders.get().size() > 0)
			return borders.get();
		return null;
	}
}
