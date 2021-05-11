package org.steambuddy.app.repository;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;
import org.steambuddy.app.compositekeys.GameRatingKey;
import org.steambuddy.app.compositekeys.GroupMessageKey;
import org.steambuddy.app.compositekeys.MessageKey;
import org.steambuddy.app.entity.GroupMessageEntity;
import org.steambuddy.app.entity.MessageEntity;
import org.steambuddy.app.entity.RatingEntity;

@Repository
public interface GroupMessageRepository extends CrudRepository<GroupMessageEntity, GroupMessageKey> {
	List<GroupMessageEntity> findAll(Example<GroupMessageEntity> message);

	
	
}