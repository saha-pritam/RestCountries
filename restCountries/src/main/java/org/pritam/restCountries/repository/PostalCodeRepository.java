package org.pritam.restCountries.repository;

import java.util.Optional;

import org.pritam.restCountries.entity.PostalCode;
import org.springframework.data.repository.CrudRepository;

public interface PostalCodeRepository extends CrudRepository<PostalCode, String> {
	public Optional<PostalCode> findByCca2(String cca2);
}
