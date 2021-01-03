package org.steambuddy.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.steambuddy.app.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

	@Query(value = "Select * from user where name = ?1", nativeQuery = true)
	UserEntity findByName(String name);
	
}
