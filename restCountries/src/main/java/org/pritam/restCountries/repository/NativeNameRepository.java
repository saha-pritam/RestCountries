package org.pritam.restCountries.repository;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.entity.NativeName;
import org.springframework.data.repository.CrudRepository;

public interface NativeNameRepository extends CrudRepository<NativeName, Integer> {
	
	public Optional<List<NativeName>> findByCca2(String cca2);

}
