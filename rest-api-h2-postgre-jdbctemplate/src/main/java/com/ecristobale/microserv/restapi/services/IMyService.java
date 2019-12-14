package com.ecristobale.microserv.restapi.services;

import java.util.List;

import com.ecristobale.microserv.restapi.models.MyEntity;

public interface IMyService {

	List<MyEntity> getAllEntidad();

	MyEntity getEntidadById(long id);

	boolean createMyEntity(MyEntity myEntity);

	boolean deleteMyEntity(long id);

	boolean updateMyEntity(MyEntity myEntity, Long id);

	List<MyEntity> searchEntitiesByName(String name);
}