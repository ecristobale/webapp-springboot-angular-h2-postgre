package com.ecristobale.microserv.restapi.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecristobale.microserv.restapi.models.MyEntity;
import com.ecristobale.microserv.restapi.repositories.IMyRepository;

@Service
public class MyService implements IMyService {
	
	@Autowired
	private IMyRepository myRepository;

	@Override
	public List<MyEntity> getAllEntidad() {
		return myRepository.getAllEntidad();
	}
	
	@Override
	public MyEntity getEntidadById(long id) {
		return myRepository.getEntidadById(id);
	}
	
	@Override
	public boolean createMyEntity(MyEntity myEntity) {
		return myRepository.createMyEntity(myEntity);
	}
	
	@Override
	public boolean deleteMyEntity(long id) {
		return myRepository.deleteMyEntity(id);
	}
	
	@Override
	public boolean updateMyEntity(@Valid MyEntity myEntity, Long id) {
		return myRepository.updateMyEntity(myEntity, id);
	}

	public IMyRepository getMyRepository() {
		return myRepository;
	}
	public void setMyRepository(IMyRepository myRepository) {
		this.myRepository = myRepository;
	}
}
