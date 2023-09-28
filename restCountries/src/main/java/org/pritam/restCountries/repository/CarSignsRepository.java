package org.pritam.restCountries.repository;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.entity.CarSigns;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CarSignsRepository extends CrudRepository<CarSigns, Integer> {
	
	@Query("select carSigns.sign from CarSigns carSigns where carSigns.cca2=:cca2")
	public Optional<List<String>> getAllSignsByCca2(@Param("cca2") String cca2);

}
