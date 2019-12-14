package com.ecristobale.microserv.restapi.repositories;

import java.util.List;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ecristobale.microserv.restapi.models.MyEntity;
import com.ecristobale.microserv.restapi.models.MyEntityMapper;

@Repository
public class MyRepository implements IMyRepository {

JdbcTemplate jdbcTemplate;
	
	private final String SQL_GET_ALL = "select * from entity";
	private final String SQL_FIND_ENTITY = "select * from entity where id = ?";
	private final String SQL_INSERT_ENTITY = "insert into entity(name, description) values(?,?)";
	private final String SQL_DELETE_ENTITY = "delete from entity where id = ?";
	private final String SQL_UPDATE_ENTITY = "update entity set name = ?, description = ? where id = ?";
	private final String SQL_SEARCH_ENTITY = "select * from entity where name like ?";
	
	@Autowired
	public MyRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<MyEntity> getAllEntidad() {
		//return Arrays.asList(new MyEntity("entidad1", "descripcion"), new MyEntity("entidad2","descripcion"));
		//String SQL = "SELECT id, name, description FROM entity";
		
		return jdbcTemplate.query(SQL_GET_ALL, new MyEntityMapper()) ;
	}

	@Override
	public MyEntity getEntidadById(long id) {
		try {
			return jdbcTemplate.queryForObject(SQL_FIND_ENTITY, new Object[] {id},new MyEntityMapper()) ;
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public boolean createMyEntity(MyEntity myEntity) {
		return (jdbcTemplate.update(SQL_INSERT_ENTITY, myEntity.getName(), myEntity.getDescription())) > 0;
	}

	@Override
	public boolean deleteMyEntity(long id) {
		return (jdbcTemplate.update(SQL_DELETE_ENTITY, id)) > 0;
	}

	@Override
	public boolean updateMyEntity(@Valid MyEntity myEntity, Long id) {
		return (jdbcTemplate.update(SQL_UPDATE_ENTITY, myEntity.getName(), myEntity.getDescription(), id)) > 0;
	}

	@Override
	public List<MyEntity> searchEntitiesByName(String name) {
		name = "%".concat(name).concat("%");
		return jdbcTemplate.query(SQL_SEARCH_ENTITY, new Object[] {name}, new MyEntityMapper());
	}
}
