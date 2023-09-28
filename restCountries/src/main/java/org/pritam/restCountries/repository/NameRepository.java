package org.pritam.restCountries.repository;

import java.util.List;
import java.util.Optional;

import org.pritam.restCountries.entity.Name;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface NameRepository extends CrudRepository<Name, Integer> {
	public Optional<Name> findByCca2(String cca2);
	
	@Query("select name.cca2 from Name name where name.common like :pattern or name.official like :pattern")
	public Optional<List<String>> findCca2ByCommonOrOfficialPatternMatch(@Param("pattern")String pattern);
}
