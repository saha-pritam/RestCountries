package org.pritam.restCountries.repository;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.entity.Capital;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CapitalRepository extends CrudRepository<Capital, Integer> {

	@Query("select c.capitalname from Capital c where c.cca2=:cca2")
	public Optional<List<String>> findByCca2(@Param("cca2") String cca2);
	
	@Query("select capital.cca2 from Capital capital where capital.capitalname=:capitalname")
	public Optional<List<String>> findByCapital(@Param("capitalname")String capitalName);
}
