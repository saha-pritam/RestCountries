package org.pritam.restCountries.repository;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.entity.Tld;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TldRepository extends CrudRepository<Tld, Integer> {

	@Query("select t.value from Tld t where t.cca2=:cca2")
	public Optional<List<String>> findByCca2(@Param("cca2") String cca2);
}
