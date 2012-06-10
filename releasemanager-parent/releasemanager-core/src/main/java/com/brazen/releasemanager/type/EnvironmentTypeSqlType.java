package com.brazen.releasemanager.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.brazen.releasemanager.domain.EnvironmentType;

import jodd.db.type.SqlType;

public class EnvironmentTypeSqlType extends SqlType<EnvironmentType> {
	
	@Override
	public EnvironmentType get(ResultSet rs, int index, int dbSqlType)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void set(PreparedStatement st, int index, EnvironmentType value,
			int dbSqlType) throws SQLException {
		st.setLong(index, value.getId());
		
	}

}
