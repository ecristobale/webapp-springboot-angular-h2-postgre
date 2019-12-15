package com.ecristobale.microserv.restapi.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecristobale.microserv.restapi.models.MyEntity;

@Repository
public interface IMyRepository extends CrudRepository<MyEntity, Long> {
	
	@Query(value = "SELECT e FROM MyEntity e WHERE LOWER(e.name) LIKE LOWER(concat('%', concat(:name, '%')))")
	Iterable<MyEntity> searchEntitiesByName(@Param("name") String name);
}