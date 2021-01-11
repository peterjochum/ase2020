package org.steambuddy.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.steambuddy.app.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

	@Query(value = "Select * from user where name = ?1", nativeQuery = true)
	UserEntity findByName(String name);
	
	@Query(value = "Select u.id, u.name, u.password from user u, group_member g where g.group_id = ?1", nativeQuery = true)
	List<UserEntity> findGroupMembers(Long group_id);
	
}
