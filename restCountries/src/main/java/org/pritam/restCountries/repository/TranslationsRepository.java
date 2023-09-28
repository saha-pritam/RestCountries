package org.pritam.restCountries.repository;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.entity.Translations;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TranslationsRepository extends CrudRepository<Translations, Integer> {
	public Optional<List<Translations>> findByCca2(String cca2);
	
	@Query("select translations.cca2 from Translations translations where translations.official=:param or translations.common=:param")
	public Optional<List<String>> findByTranslationsOfficialOrCommon(@Param("param")String translationsOfficialOrCommon);
}
