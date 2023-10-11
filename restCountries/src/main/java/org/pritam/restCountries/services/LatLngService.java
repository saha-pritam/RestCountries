package org.pritam.restCountries.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.pritam.restCountries.repository.LatLngRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LatLngService {

	@Autowired
	private LatLngRepository latLngRepository;

	@Transactional
	public List<Double> getLatLng(String cca2) {
		List<Double> list = null;
		Optional<List<Double>> lat = latLngRepository.getLatByCca2(cca2);
		if (lat.isPresent())
			list = lat.get();
		Optional<List<Double>> lng = latLngRepository.getLngByCca2(cca2);
		if (lng.isPresent()) {
			if (list == null)
				list = lng.get();
			else
				list.addAll(lng.get());
		}
		return list;
	}
}
