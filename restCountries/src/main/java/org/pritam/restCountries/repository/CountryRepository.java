package org.pritam.restCountries.repository;

import org.pritam.restCountries.entity.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, String> {

}
