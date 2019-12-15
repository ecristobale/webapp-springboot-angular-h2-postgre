package com.ecristobale.microserv.restapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecristobale.microserv.restapi.models.MyFile;

@Repository
public interface IMyRepository2 extends CrudRepository<MyFile, Long> {
	
}