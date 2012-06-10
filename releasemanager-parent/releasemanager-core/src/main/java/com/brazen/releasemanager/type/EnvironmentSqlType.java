package com.brazen.releasemanager.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.brazen.releasemanager.domain.Environment;
import com.brazen.releasemanager.exception.BrazenException;

import jodd.db.type.SqlType;

public class EnvironmentSqlType extends SqlType<Environment> {
	
	@Override
	public Environment get(ResultSet rs, int index, int dbSqlType)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void set(PreparedStatement st, int index, Environment value,
			int dbSqlType) throws SQLException {
		if (value.isPersistent()) { 
			st.setLong(index, value.getId());
		} else {
			throw new BrazenException("Attempted to store a non-persisted environment value");
		}
	
		
	}

}
