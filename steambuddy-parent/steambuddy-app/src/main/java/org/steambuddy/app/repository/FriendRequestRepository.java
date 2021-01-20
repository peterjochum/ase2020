package org.steambuddy.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.steambuddy.app.entity.FriendRequestEntity;

public interface FriendRequestRepository extends CrudRepository<FriendRequestEntity, Long>{

	@Query(value = "Select * from friend_request where sender = ?1 and accepted is NULL", nativeQuery = true)
	List<FriendRequestEntity> findBySenderId(Long id);
	
	@Query(value = "Select * from friend_request where receiver = ?1 and accepted is NULL", nativeQuery = true)
	List<FriendRequestEntity> findByReceiverId(Long id);
	
}
