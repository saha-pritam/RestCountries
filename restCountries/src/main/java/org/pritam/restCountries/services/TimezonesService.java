package org.pritam.restCountries.services;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.repository.TimezonesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class TimezonesService {

	@Autowired
	private TimezonesRepository timezonesRepository;

	@Transactional
	public List<String> getTimezones(String cca2) {
		Optional<List<String>> timezones = timezonesRepository.findByCca2(cca2);
		if (timezones.isPresent() && timezones.get().size() > 0)
			return timezones.get();
		return null;
	}
}
