package org.steambuddy.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.steambuddy.app.entity.GenreEntity;

public interface GenreRepository extends CrudRepository<GenreEntity, Long> {

	@Query(value = "select g from GenreEntity g where name like CONCAT('%', CONCAT(:name, '%'))")
	List<GenreEntity> findByName(String name);

}
