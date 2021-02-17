package org.steambuddy.app.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.steambuddy.app.entity.GameEntity;

public interface GameRepository extends PagingAndSortingRepository<GameEntity, Long>
{

    @Query(value = "select g from GameEntity g where name like CONCAT('%', CONCAT(:name, '%'))")
    List<GameEntity> findByName(String name, Pageable pageable);
    
    //Get games that contain some of the given categories. Of all found rows that randomly *pageable* of them
    //add later: also make sure that game is not already in users library
    @Query(value="SELECT DISTINCT g FROM GameEntity g INNER JOIN g.genres ge WHERE ge.id in (:categories) ORDER BY RAND()")
    List<GameEntity> findByCategoryRandomly(@Param("categories") List<Long> categories);
	
}
