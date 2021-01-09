package org.steambuddy.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.steambuddy.app.entity.GroupEntity;

public interface GroupRepository extends CrudRepository<GroupEntity, Long>{

	@Query(value = "Select * from friend_group where name = ?1", nativeQuery = true)
	GroupEntity findByName(String name);
	
	@Query(value = "Select * from friend_group where owner_id = ?1", nativeQuery = true)
	List<GroupEntity> findGroupByUserId(Long user_id);
	
}
