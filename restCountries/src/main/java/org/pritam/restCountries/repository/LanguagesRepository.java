package org.pritam.restCountries.repository;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.entity.Languages;
import org.springframework.data.repository.CrudRepository;

public interface LanguagesRepository extends CrudRepository<Languages, Integer> {
	public Optional<List<Languages>> findByCca2(String cca2);
}
