package com.ecristobale.microserv.restapi.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ecristobale.microserv.restapi.models.MyEntity;
import com.ecristobale.microserv.restapi.models.MyFile;

public interface IMyService {

	List<MyEntity> getAllEntidad();

	MyEntity getEntidadById(long id);

	boolean createMyEntity(MyEntity myEntity);

	boolean deleteMyEntity(long id);

	boolean updateMyEntity(MyEntity myEntity, Long id);

	List<MyEntity> searchEntitiesByName(String name);

	void createMyFile(MultipartFile file, long idMyEntity);
}