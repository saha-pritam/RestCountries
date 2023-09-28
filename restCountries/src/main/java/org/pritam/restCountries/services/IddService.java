package org.pritam.restCountries.services;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.entity.IddRoot;
import org.pritam.restCountries.repository.IddRootRepository;
import org.pritam.restCountries.repository.IddSuffixesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class IddService {
	@Autowired
	private IddRootRepository iddRootRepository;

	@Autowired
	private IddSuffixesRepository iddSuffixesRepository;

	@Transactional
	public String getIddRoot(String cca2) {
		Optional<IddRoot> iddRoot = iddRootRepository.findById(cca2);
		if (iddRoot.isPresent())
			return iddRoot.get().getRoot();
		return null;
	}

	@Transactional
	public List<String> getIddSuffixes(String cca2) {
		Optional<List<String>> iddSuffixes = iddSuffixesRepository.findByCca2(cca2);
		if (iddSuffixes.isPresent())
			return iddSuffixes.get();
		return null;
	}
}
