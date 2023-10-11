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
	
	@Query("select country.cca2 from Country country where country.cca2 IN :codes or country.ccn3 IN :codes or country.cca3 IN :codes or country.cioc IN :codes")
	public Optional<List<String>> findByListOfCodes(@Param("codes")List<String> codes);
	
	@Query("select country.cca2 from Country country where country.region=:regionName")
	public Optional<List<String>> findByRegion(@Param("regionName")String regionName);
	
	@Query("select country.cca2 from Country country where country.subregion=:subRegionName")
	public Optional<List<String>> findBySubRegion(@Param("subRegionName")String subRegionName);
	
	@Query("select country.cca2 from Country country")
	public Optional<List<String>> findAllCca2();
}
