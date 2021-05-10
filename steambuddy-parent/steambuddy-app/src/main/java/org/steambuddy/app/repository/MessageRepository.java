package org.steambuddy.app.repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.steambuddy.app.compositekeys.GameRatingKey;
import org.steambuddy.app.compositekeys.MessageKey;
import org.steambuddy.app.entity.MessageEntity;
import org.steambuddy.app.entity.RatingEntity;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity, MessageKey> {
	
}