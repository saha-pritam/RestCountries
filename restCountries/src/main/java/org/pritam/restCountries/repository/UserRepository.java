package org.pritam.restCountries.repository;

import org.pritam.restCountries.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
