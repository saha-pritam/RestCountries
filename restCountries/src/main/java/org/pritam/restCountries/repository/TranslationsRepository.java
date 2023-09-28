package org.pritam.restCountries.repository;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.entity.Translations;
import org.springframework.data.repository.CrudRepository;

public interface TranslationsRepository extends CrudRepository<Translations, Integer> {
	public Optional<List<Translations>> findByCca2(String cca2);
}
