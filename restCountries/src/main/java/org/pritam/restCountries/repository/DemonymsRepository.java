package org.pritam.restCountries.repository;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.entity.Demonyms;
import org.springframework.data.repository.CrudRepository;

public interface DemonymsRepository extends CrudRepository<Demonyms, Integer> {
	public Optional<List<Demonyms>> findByCca2(String cca2);
}
