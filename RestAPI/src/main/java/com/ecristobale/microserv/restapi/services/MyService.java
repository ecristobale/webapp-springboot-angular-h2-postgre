package com.ecristobale.microserv.restapi.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecristobale.microserv.restapi.models.MyEntity;
import com.ecristobale.microserv.restapi.repositories.MyRepository;

@Service
public class MyService {
	
	@Autowired
	private MyRepository myRepository;

	public List<MyEntity> getAllEntidad() {
		return myRepository.getAllEntidad();
	}
	
	public MyEntity getEntidadById(long id) {
		return myRepository.getEntidadById(id);
	}
	
	public boolean createMyEntity(MyEntity myEntity) {
		return myRepository.createMyEntity(myEntity);
	}
	
	public boolean deleteMyEntity(long id) {
		return myRepository.deleteMyEntity(id);
	}
	
	public boolean updateMyEntity(@Valid MyEntity myEntity, Long id) {
		return myRepository.updateMyEntity(myEntity, id);
	}

	public MyRepository getMyRepository() {
		return myRepository;
	}
	public void setMyRepository(MyRepository myRepository) {
		this.myRepository = myRepository;
	}
}
