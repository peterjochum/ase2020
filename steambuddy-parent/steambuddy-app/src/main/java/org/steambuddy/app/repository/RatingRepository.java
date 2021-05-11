package org.steambuddy.app.repository;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Pair;
import org.steambuddy.app.compositekeys.GameRatingKey;
import org.steambuddy.app.entity.GameEntity;
import org.steambuddy.app.entity.RatingEntity;

@Repository
public interface RatingRepository extends CrudRepository<RatingEntity, GameRatingKey> {
	
    @Query(value="SELECT g.ratingKey.gameId,AVG(g.rating) FROM RatingEntity g GROUP BY g.ratingKey.gameId ORDER BY AVG(g.rating)")
    List<Tuple> getGameToRatingAggregation();
	
}