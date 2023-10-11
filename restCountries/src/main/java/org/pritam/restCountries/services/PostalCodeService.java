package org.pritam.restCountries.services;

import java.util.LinkedHashMap;
import java.util.Optional;

import org.pritam.restCountries.entity.PostalCode;
import org.pritam.restCountries.repository.PostalCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PostalCodeService {

	@Autowired
	private PostalCodeRepository postalCodeRepository;

	@Transactional
	public LinkedHashMap<String, String> getPostalCode(String cca2) {
		LinkedHashMap<String, String> linkedHashMap = null;
		Optional<PostalCode> postalCode = postalCodeRepository.findByCca2(cca2);
		if (postalCode.isPresent()) {
			linkedHashMap = new LinkedHashMap<String, String>();
			linkedHashMap.put("format", postalCode.get().getFormat());
			linkedHashMap.put("regex", postalCode.get().getRegex());
		}
		return linkedHashMap;
	}

}
