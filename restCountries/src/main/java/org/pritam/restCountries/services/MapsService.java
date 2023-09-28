package org.pritam.restCountries.services;

import java.util.LinkedHashMap;
import java.util.Optional;

import org.pritam.restCountries.entity.Maps;
import org.pritam.restCountries.repository.MapsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class MapsService {
	@Autowired
	private MapsRepository mapsRepository;

	@Transactional
	public LinkedHashMap<String, String> getMaps(String cca2) {
		LinkedHashMap<String, String> linkedHashMap = null;
		Optional<Maps> maps = mapsRepository.findById(cca2);
		if (maps.isPresent()) {
			linkedHashMap = new LinkedHashMap<String, String>();
			if (maps.get().getGoogleMaps() != null)
				linkedHashMap.put("googleMaps", maps.get().getGoogleMaps());
			if (maps.get().getOpenStreetMaps() != null)
				linkedHashMap.put("openStreetMaps", maps.get().getOpenStreetMaps());
		}
		return linkedHashMap;
	}
}
