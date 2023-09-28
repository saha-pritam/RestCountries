package org.pritam.restCountries.repository;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.entity.AltSpellings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AltSpellingsRepository extends CrudRepository<AltSpellings, Integer> {

	@Query("select altSpellings.spelling from AltSpellings altSpellings where altSpellings.cca2=:cca2")
	public Optional<List<String>> findByCca2(@Param("cca2") String cca2);
}
