package org.steambuddy.app.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.steambuddy.app.entity.GameEntity;
import org.steambuddy.app.entity.GenreEntity;

public interface GameRepository extends CrudRepository<GameEntity, Long>
{

    @Query(value = "select g from GameEntity g where name like CONCAT('%', CONCAT(:name, '%'))")
    List<GameEntity> findByName(String name);
    
    @Query(value="SELECT g FROM GameEntity g INNER JOIN g.genres ge WHERE ge.id in (0,1,2,3)")
    List<GameEntity> findByCategory(Set<GenreEntity> categories);
	
}
