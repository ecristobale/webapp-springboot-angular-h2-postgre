package com.ecristobale.microserv.restapi.services;

import java.util.List;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ecristobale.microserv.restapi.models.MyEntity;
import com.ecristobale.microserv.restapi.models.MyEntityMapper;

@Service
public class MyService {
	
	JdbcTemplate jdbcTemplate;
	
	private final String SQL_GET_ALL = "select * from entity";
	private final String SQL_FIND_ENTITY = "select * from entity where id = ?";
	private final String SQL_INSERT_ENTITY = "insert into entity(name, description) values(?,?)";
	private final String SQL_DELETE_ENTITY = "delete from entity where id = ?";
	private final String SQL_UPDATE_ENTITY = "update entity set name = ?, description = ? where id = ?";
	
	
	@Autowired
	public MyService(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<MyEntity> getAllEntidad() {
//		return Arrays.asList
//				(new MyEntity("entidad1", "descripcion"), new MyEntity("entidad2","descripcion"));
		//String SQL = "SELECT id, name, description FROM entity";
		
		return jdbcTemplate.query(SQL_GET_ALL, new MyEntityMapper()) ;
	}

	public MyEntity getEntidadById(long id) {
//		return Arrays.asList
//				(new MyEntity("entidad1", "descripcion"), new MyEntity("entidad2","descripcion"));
		//String SQL = "SELECT id, name, description FROM entity";
		try {
			return jdbcTemplate.queryForObject(SQL_FIND_ENTITY, new Object[] {id},new MyEntityMapper()) ;
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

	public boolean createMyEntity(MyEntity myEntity) {
		return (jdbcTemplate.update(SQL_INSERT_ENTITY, myEntity.getName(), myEntity.getDescription())) > 0;
	}

	public boolean deleteMyEntity(long id) {
		return (jdbcTemplate.update(SQL_DELETE_ENTITY, id)) > 0;
	}

	public boolean updateMyEntity(@Valid MyEntity myEntity, Long id) {
		return (jdbcTemplate.update(SQL_UPDATE_ENTITY, myEntity.getName(), myEntity.getDescription(), id)) > 0;
	}

	
}
