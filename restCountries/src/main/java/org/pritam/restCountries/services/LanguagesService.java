package org.pritam.restCountries.services;

import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.pritam.restCountries.entity.Languages;
import org.pritam.restCountries.repository.LanguagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LanguagesService {
	@Autowired
	private LanguagesRepository languagesRepository;

	@Transactional
	public TreeMap<String, String> getLanguages(String cca2) {
		TreeMap<String, String> treeMap = null;
		Optional<List<Languages>> languages = languagesRepository.findByCca2(cca2);
		if (languages.isPresent()) {
			treeMap = new TreeMap<String, String>(languages.get().stream()
					.collect(Collectors.toMap(language -> language.getLangcode(), language -> language.getLangname())));
		}
		return treeMap;
	}
	
	@Transactional
	public List<String> getCountryByLanguagesCodeOrName(String languagesCodeOrName) {
		Optional<List<String>> cca2List= languagesRepository.findByLanguagesCodeOrName(languagesCodeOrName);
		if(cca2List.isPresent() && cca2List.get().size()>0)
			return cca2List.get();
		return null;
	}
}
