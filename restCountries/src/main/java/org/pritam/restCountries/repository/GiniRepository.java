package org.pritam.restCountries.repository;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.entity.Gini;
import org.springframework.data.repository.CrudRepository;

public interface GiniRepository extends CrudRepository<Gini, Integer> {
	public Optional<List<Gini>> findByCca2(String cca2);
}
