package org.pritam.restCountries.repository;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.entity.Languages;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LanguagesRepository extends CrudRepository<Languages, Integer> {
	public Optional<List<Languages>> findByCca2(String cca2);
	
	@Query("select languages.cca2 from Languages languages where languages.langcode=:languagesCodeOrName or languages.langname=:languagesCodeOrName")
	public Optional<List<String>> findByLanguagesCodeOrName(@Param("languagesCodeOrName")String languagesCodeOrName);
}
