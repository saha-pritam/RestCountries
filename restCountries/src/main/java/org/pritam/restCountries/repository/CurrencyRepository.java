package org.pritam.restCountries.repository;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.entity.Currencies;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<Currencies, Integer> {
	public Optional<List<Currencies>> findByCca2(String cca2);
}
