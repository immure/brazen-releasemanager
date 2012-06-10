package com.brazen.releasemanager.domain;

import java.util.ArrayList;
import java.util.List;

import jodd.db.oom.meta.DbColumn;
import jodd.db.oom.meta.DbTable;
import jodd.joy.db.Entity;

/**
 * An environment is 1 or more servers where a single deployment is made.
 * 
 * @author mike
 *
 */
@DbTable
public class Environment extends Entity {
	
	@DbColumn
	private String environmentName;
	
	// Relationships
	
	private List<Server> servers = new ArrayList<Server>();

	public List<Server> getServers() {
		return servers;
	}

	public void setServers(List<Server> servers) {
		this.servers = servers;
		for (Server server : servers) {
			server.setEnvironment(this);
		}
	}

	@DbColumn
	private EnvironmentType environmentType;
	
	public EnvironmentType getEnvironmentType() {
		return environmentType;
	}

	public void setEnvironmentType(EnvironmentType environmentType) {
		this.environmentType = environmentType;
	}
	
	// Properties

	public String getEnvironmentName() {
		return environmentName;
	}

	public void setEnvironmentName(String environmentName) {
		this.environmentName = environmentName;
	}
	
	

}
