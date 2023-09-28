package org.pritam.restCountries.repository;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.entity.CapitalInfoLatLng;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CapitalInfoLatLngRepository extends CrudRepository<CapitalInfoLatLng, Integer> {
	@Query("select capitalInfoLatLngRepository.lat from CapitalInfoLatLng capitalInfoLatLngRepository where capitalInfoLatLngRepository.cca2=:cca2")
	public Optional<List<Double>> getLatByCca2(@Param("cca2")String cca2);
	
	@Query("select capitalInfoLatLngRepository.lng from CapitalInfoLatLng capitalInfoLatLngRepository where capitalInfoLatLngRepository.cca2=:cca2")
	public Optional<List<Double>> getLngByCca2(@Param("cca2")String cca2);
}
