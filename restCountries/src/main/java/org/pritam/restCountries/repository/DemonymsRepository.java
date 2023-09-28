package org.pritam.restCountries.repository;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.entity.Demonyms;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DemonymsRepository extends CrudRepository<Demonyms, Integer> {
	public Optional<List<Demonyms>> findByCca2(String cca2);
	
	@Query("select demonyms.cca2 from Demonyms demonyms where demonyms.f=:demonym or demonyms.m=:demonym")
	public Optional<List<String>> findByDemonym(@Param("demonym")String demonym);
}
