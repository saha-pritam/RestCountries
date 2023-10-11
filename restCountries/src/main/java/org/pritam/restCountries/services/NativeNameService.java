package org.pritam.restCountries.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.pritam.restCountries.entity.NativeName;
import org.pritam.restCountries.repository.NativeNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class NativeNameService {

	@Autowired
	private NativeNameRepository nativeNameRepository;
	
	@Transactional
	public TreeMap<String,LinkedHashMap<String,String>> getNativeNames(String cca2) {
		TreeMap<String,LinkedHashMap<String,String>> treeMap = null;
		Optional<List<NativeName>> nativeNames = nativeNameRepository.findByCca2(cca2);
		if(nativeNames.isPresent()) {
			treeMap = new TreeMap<String,LinkedHashMap<String,String>>(nativeNames.get().stream().collect(Collectors.toMap(nativeName->nativeName.getLangcode(), nativeName->{
				LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
				linkedHashMap.put("official", nativeName.getOfficial());
				linkedHashMap.put("common", nativeName.getCommon());
				return linkedHashMap;
			})));
		}
		return treeMap;
	}
}
