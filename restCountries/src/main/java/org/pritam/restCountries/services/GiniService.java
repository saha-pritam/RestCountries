package org.pritam.restCountries.services;

import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.pritam.restCountries.entity.Gini;
import org.pritam.restCountries.repository.GiniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GiniService {
	@Autowired
	private GiniRepository giniRepository;

	@Transactional
	public TreeMap<String, Double> getGini(String cca2) {
		TreeMap<String, Double> treeMap = null;
		Optional<List<Gini>> gini = giniRepository.findByCca2(cca2);
		if (gini.isPresent() && gini.get().size() > 0) {
			treeMap = new TreeMap<String, Double>(
					gini.get().stream().collect(Collectors.toMap(item -> item.getYear(), item -> item.getValue())));
		}
		return treeMap;
	}
}
