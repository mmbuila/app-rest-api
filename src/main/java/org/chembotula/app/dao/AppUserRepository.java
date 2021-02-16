package org.chembotula.app.dao;

import java.util.Optional;

import org.chembotula.app.models.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
	
	Optional<AppUser> findByUsernameAndPassword(String username, String password);
}
