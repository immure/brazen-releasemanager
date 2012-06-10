package com.brazen.releasemanager.domain;

import jodd.db.oom.meta.DbColumn;
import jodd.db.oom.meta.DbTable;
import jodd.joy.db.Entity;

/**
 * Different types of environment, e.g. DEVELOPMENT, STAGING, REFERENCE, PRODUCTION, etc.
 * 
 * @author mike
 *
 */
@DbTable
public class EnvironmentType extends Entity {
	
	@DbColumn
	private String environmentTypeName;
	
	// Properties

	public String getEnvironmentTypeName() {
		return environmentTypeName;
	}

	public void setEnvironmentTypeName(String environmentName) {
		this.environmentTypeName = environmentName;
	}
	
	

}
