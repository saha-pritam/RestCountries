package org.pritam.restCountries.repository;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.entity.Timezones;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TimezonesRepository extends CrudRepository<Timezones, Integer> {

	@Query("select timezones.value from Timezones timezones where timezones.cca2=:cca2")
	public Optional<List<String>> findByCca2(@Param("cca2") String cca2);
}
