package org.pritam.restCountries.repository;

import java.util.Optional;

import org.pritam.restCountries.entity.Name;
import org.springframework.data.repository.CrudRepository;

public interface NameRepository extends CrudRepository<Name, Integer> {
	public Optional<Name> findByCca2(String cca2);
}
