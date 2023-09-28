package org.pritam.restCountries.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.pritam.restCountries.entity.Translations;
import org.pritam.restCountries.repository.TranslationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class TranslationsService {

	@Autowired
	private TranslationsRepository translationsRepository;

	@Transactional
	public TreeMap<String, LinkedHashMap<String, String>> getTranslations(String cca2) {
		TreeMap<String, LinkedHashMap<String, String>> treeMap = null;
		Optional<List<Translations>> translations = translationsRepository.findByCca2(cca2);
		if (translations.isPresent()) {
			treeMap = new TreeMap<String, LinkedHashMap<String, String>>(translations.get().stream()
					.collect(Collectors.toMap(translation -> translation.getCode(), translation -> {
						LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
						linkedHashMap.put("official", translation.getOfficial());
						linkedHashMap.put("common", translation.getCommon());
						return linkedHashMap;
					})));
		}
		return treeMap;
	}
	
	@Transactional
	public List<String> getCountryByTranslationOfficialOrCommon(String translationOfficialOrCommon) {
		Optional<List<String>> cca2List= translationsRepository.findByTranslationsOfficialOrCommon(translationOfficialOrCommon);
		if(cca2List.isPresent() && cca2List.get().size()>0)
			return cca2List.get();
		return null;
	}
}
