package org.steambuddy.app.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.steambuddy.app.entity.GameEntity;
import org.steambuddy.app.entity.GenreEntity;

public interface GameRepository extends CrudRepository<GameEntity, Long>
{

    @Query(value = "select g from GameEntity g where name like CONCAT('%', CONCAT(:name, '%'))")
    List<GameEntity> findByName(String name);
    
    //Get games that contain some of the given categories. Of all found rows that randomly *pageable* of them

    //add later: also make sure that game is not already in users library
    
    
    @Query(value="SELECT DISTINCT g FROM GameEntity g INNER JOIN g.genres ge WHERE ge.id in (:categories) ORDER BY RAND()")
    List<GameEntity> findByCategoryRandomly(@Param("categories") List<Long> categories,Pageable pageable);
	
}
