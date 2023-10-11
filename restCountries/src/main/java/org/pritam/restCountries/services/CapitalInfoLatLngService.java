package org.pritam.restCountries.services;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.repository.CapitalInfoLatLngRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CapitalInfoLatLngService {
	@Autowired
	private CapitalInfoLatLngRepository capitalInfoLatLngRepository;

	@Transactional
	public HashMap<String, List<Double>> getLatLng(String cca2) {
		HashMap<String, List<Double>> hashMap = null;
		List<Double> list = null;
		Optional<List<Double>> lat = capitalInfoLatLngRepository.getLatByCca2(cca2);
		if (lat.isPresent()) {
			list = lat.get();
		}
		Optional<List<Double>> lng = capitalInfoLatLngRepository.getLngByCca2(cca2);
		if (lng.isPresent()) {
			if (list == null)
				list = lng.get();
			else
				list.addAll(lng.get());
		}
		if (list != null) {
			hashMap = new HashMap<String, List<Double>>();
			hashMap.put("latlng", list);
		}
		return hashMap;
	}
}
