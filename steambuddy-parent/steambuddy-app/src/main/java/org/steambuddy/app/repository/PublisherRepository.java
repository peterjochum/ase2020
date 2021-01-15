package org.steambuddy.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.steambuddy.app.entity.PublisherEntity;

public interface PublisherRepository extends CrudRepository<PublisherEntity, Long> {

	@Query(value = "select g from PublisherEntity g where name like CONCAT('%', CONCAT(:name, '%'))")
	List<PublisherEntity> findByName(String name);

	
}
