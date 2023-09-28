package org.pritam.restCountries.repository;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.entity.Borders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BordersRepository extends CrudRepository<Borders, Integer> {

	@Query("select borders.code from Borders borders where borders.cca2=:cca2")
	public Optional<List<String>> findByCca2(@Param("cca2") String cca2);
}
