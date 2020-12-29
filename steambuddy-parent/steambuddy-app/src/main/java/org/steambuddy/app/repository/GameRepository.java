package org.steambuddy.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.steambuddy.app.entity.GameEntity;

public interface GameRepository extends CrudRepository<GameEntity, Long>
{

}
