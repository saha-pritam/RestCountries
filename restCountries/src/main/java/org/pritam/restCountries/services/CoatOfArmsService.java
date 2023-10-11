package org.pritam.restCountries.services;

import java.util.LinkedHashMap;
import java.util.Optional;

import org.pritam.restCountries.entity.CoatOfArms;
import org.pritam.restCountries.repository.CoatOfArmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CoatOfArmsService {
	@Autowired
	private CoatOfArmsRepository coatOfArmsRepository;

	@Transactional
	public LinkedHashMap<String, String> getCoatOfArms(String cca2) {
		LinkedHashMap<String, String> linkedHashMap = null;
		Optional<CoatOfArms> coatOfArms = coatOfArmsRepository.findById(cca2);
		if (coatOfArms.isPresent()) {
			linkedHashMap = new LinkedHashMap<String, String>();
			if (coatOfArms.get().getPng() != null)
				linkedHashMap.put("png", coatOfArms.get().getPng());
			if (coatOfArms.get().getSvg() != null)
				linkedHashMap.put("svg", coatOfArms.get().getSvg());
		}
		return linkedHashMap;
	}
}
