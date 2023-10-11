package org.pritam.restCountries.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.entity.Name;
import org.pritam.restCountries.repository.NameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class NameService {

	@Autowired
	private NameRepository nameRepository;

	@Transactional
	public LinkedHashMap<String, Object> getName(String cca2) {
		LinkedHashMap<String, Object> linkedHashMap = null;
		Optional<Name> name = nameRepository.findByCca2(cca2);
		if (name.isPresent()) {
			linkedHashMap = new LinkedHashMap<String, Object>();
			linkedHashMap.put("common", name.get().getCommon());
			linkedHashMap.put("official", name.get().getOfficial());
		}
		return linkedHashMap;
	}
	
	@Transactional
	public List<String> getCca2ByCommonOrOfficialPatternMatch(String pattern){
		Optional<List<String>> listOfCca2 = nameRepository.findCca2ByCommonOrOfficialPatternMatch("%"+pattern+"%");
		if(listOfCca2.isPresent() && listOfCca2.get().size()>0)
			return listOfCca2.get();
		return null;
	}
	
	@Transactional
	public List<String> getCca2ByCommonOrOfficialFullMatch(String pattern){
		Optional<List<String>> listOfCca2 = nameRepository.findCca2ByCommonOrOfficialFullMatch(pattern);
		if(listOfCca2.isPresent() && listOfCca2.get().size()>0)
			return listOfCca2.get();
		return null;
	}
}
