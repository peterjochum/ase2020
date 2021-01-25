package org.steambuddy.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.steambuddy.app.entity.GameCollectionEntity;

public interface GameCollectionRepository extends CrudRepository<GameCollectionEntity, Long> {

}
