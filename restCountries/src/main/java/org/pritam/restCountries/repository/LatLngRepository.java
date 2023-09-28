package org.pritam.restCountries.repository;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.entity.LatLng;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LatLngRepository extends CrudRepository<LatLng, Integer> {
	@Query("select latLng.lat from LatLng latLng where latLng.cca2=:cca2")
	public Optional<List<Double>> getLatByCca2(@Param("cca2")String cca2);
	
	@Query("select latLng.lng from LatLng latLng where latLng.cca2=:cca2")
	public Optional<List<Double>> getLngByCca2(@Param("cca2")String cca2);
}
