package org.pritam.restCountries.services;

import java.util.LinkedHashMap;
import java.util.Optional;

import org.pritam.restCountries.entity.Flags;
import org.pritam.restCountries.repository.FlagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FlagsService {
	@Autowired
	private FlagsRepository flagsRepository;

	@Transactional
	public LinkedHashMap<String, String> getCoatOfArms(String cca2) {
		LinkedHashMap<String, String> linkedHashMap = null;
		Optional<Flags> flags = flagsRepository.findById(cca2);
		if (flags.isPresent()) {
			linkedHashMap = new LinkedHashMap<String, String>();
			if (flags.get().getPng() != null)
				linkedHashMap.put("png", flags.get().getPng());
			if (flags.get().getSvg() != null)
				linkedHashMap.put("svg", flags.get().getSvg());
			if (flags.get().getAlt() != null)
				linkedHashMap.put("alt", flags.get().getAlt());
		}
		return linkedHashMap;
	}
}
