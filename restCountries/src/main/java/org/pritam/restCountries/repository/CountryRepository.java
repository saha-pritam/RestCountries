package org.pritam.restCountries.repository;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.entity.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CountryRepository extends CrudRepository<Country, String> {
	@Query("select country.cca2 from Country country where country.cca2=:code or country.ccn3=:code or country.cca3=:code or country.cioc=:code")
	public Optional<List<String>> findByCode(@Param("code")String code);
}