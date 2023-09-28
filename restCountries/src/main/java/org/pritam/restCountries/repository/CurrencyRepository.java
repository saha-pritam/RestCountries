package org.pritam.restCountries.repository;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.entity.Currencies;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CurrencyRepository extends CrudRepository<Currencies, Integer> {
	public Optional<List<Currencies>> findByCca2(String cca2);
	
	@Query("select currencies.cca2 from Currencies currencies where currencies.currencycode=:name or currencies.name=:name")
	public Optional<List<String>> findByCurrencyCodeOrName(@Param("name")String currencyNameOrCode);
}
