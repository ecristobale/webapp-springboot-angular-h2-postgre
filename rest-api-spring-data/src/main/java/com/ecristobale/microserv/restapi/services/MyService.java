package com.ecristobale.microserv.restapi.services;

import java.util.List;
import java.util.Optional;

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
		return (List<MyEntity>) myRepository.findAll();
	}
	
	@Override
	public MyEntity getEntidadById(long id) {
		Optional<MyEntity> myEntity = myRepository.findById(id);
		return myEntity.isPresent() ? myEntity.get():null;
	}
	
	@Override
	public boolean createMyEntity(MyEntity myEntity) {
		return myRepository.save(myEntity) != null;
	}
	
	@Override
	public boolean deleteMyEntity(long id) {
		myRepository.deleteById(id);
		return true;
	}
	
	@Override
	public boolean updateMyEntity(@Valid MyEntity myEntity, Long id) {
		MyEntity myEntityDB = getEntidadById(id);
		myEntityDB.setName(myEntity.getName());
		myEntityDB.setDescription(myEntity.getDescription());
		myRepository.save(myEntityDB);
		return true;
	}

	public IMyRepository getMyRepository() {
		return myRepository;
	}
	public void setMyRepository(IMyRepository myRepository) {
		this.myRepository = myRepository;
	}

	@Override
	public List<MyEntity> searchEntitiesByName(String name) {
		return (List<MyEntity>) myRepository.searchEntitiesByName(name);
	}
}
