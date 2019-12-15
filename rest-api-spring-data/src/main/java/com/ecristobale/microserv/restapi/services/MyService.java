package com.ecristobale.microserv.restapi.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecristobale.microserv.restapi.models.MyEntity;
import com.ecristobale.microserv.restapi.models.MyFile;
import com.ecristobale.microserv.restapi.repositories.IMyRepository;
import com.ecristobale.microserv.restapi.repositories.IMyRepository2;

@Service
public class MyService implements IMyService {
	
	@Autowired
	private IMyRepository myRepository;
	
	@Autowired
	private IMyRepository2 myRepository2;

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

	@Override
	public void createMyFile(MultipartFile file, long idMyEntity) {
		MyEntity myEntity = getEntidadById(idMyEntity);
		if(myEntity != null) {
			MyFile myFile = new MyFile();
			myFile.setMyEntity(myEntity);
			myFile.setDocName(file.getOriginalFilename());
			try {
				myFile.setFile(file.getBytes());
				myFile.setFileUri(ServletUriComponentsBuilder.fromCurrentRequestUri()
						.path("/download").toUriString());
				myEntity.setMyFile(myFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			myRepository.save(myEntity);
		}
	}
}
