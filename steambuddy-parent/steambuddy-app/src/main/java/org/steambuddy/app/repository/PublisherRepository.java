package org.steambuddy.app.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.steambuddy.app.entity.PublisherEntity;

public interface PublisherRepository extends PagingAndSortingRepository<PublisherEntity, Long> {

	@Query(value = "select g from PublisherEntity g where name like CONCAT('%', CONCAT(:name, '%'))")
	List<PublisherEntity> findByName(String name, Pageable pageable);

	
}
