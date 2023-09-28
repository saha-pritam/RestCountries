package org.pritam.restCountries.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.pritam.restCountries.entity.Demonyms;
import org.pritam.restCountries.repository.DemonymsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class DemonymsService {
	@Autowired
	private DemonymsRepository demonymsRepository;

	@Transactional
	public TreeMap<String, LinkedHashMap<String, String>> getDemonyms(String cca2) {
		TreeMap<String, LinkedHashMap<String, String>> treeMap = null;
		Optional<List<Demonyms>> demonyms = demonymsRepository.findByCca2(cca2);
		if (demonyms.isPresent()) {
			treeMap = new TreeMap<String, LinkedHashMap<String, String>>(
					demonyms.get().stream().collect(Collectors.toMap(demonym -> demonym.getCode(), demonym -> {
						LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
						if (demonym.getF() != null)
							linkedHashMap.put("f", demonym.getF());
						if (demonym.getM() != null)
							linkedHashMap.put("m", demonym.getM());
						return linkedHashMap;
					})));
		}
		return treeMap;
	}
}
