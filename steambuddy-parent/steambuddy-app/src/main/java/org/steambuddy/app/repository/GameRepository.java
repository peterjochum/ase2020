package org.steambuddy.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.steambuddy.app.entity.GameEntity;

public interface GameRepository extends CrudRepository<GameEntity, Long>
{

    @Query(value = "select g from GameEntity g where name like CONCAT('%', CONCAT(:name, '%'))")
    List<GameEntity> findByName(String name);
	
}
