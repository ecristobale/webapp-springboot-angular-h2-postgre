package com.ecristobale.microserv.restapi.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MyEntityMapper implements RowMapper<MyEntity> {

	@Override
	public MyEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		MyEntity myEntity = new MyEntity();
		myEntity.setId(rs.getLong("id"));
		myEntity.setName(rs.getString("name"));
		myEntity.setDescription(rs.getString("description"));
		return myEntity;
	}

}
