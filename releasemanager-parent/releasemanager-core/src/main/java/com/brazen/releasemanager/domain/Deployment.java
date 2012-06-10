package com.brazen.releasemanager.domain;

import jodd.datetime.JDateTime;
import jodd.db.oom.meta.DbColumn;
import jodd.db.oom.meta.DbTable;
import jodd.joy.db.Entity;

/**
 * A deployment is an instance of a Build deployed to an Environment
 * 
 * @author mike
 *
 */
@DbTable
public class Deployment extends Entity {
	
	@DbColumn
	private
	JDateTime deploymentTime;
	
	
	// Relationships
	
	private Server server;

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}
	
	private Build build;
	
	public Build getBuild() {
		return build;
	}

	public void setBuild(Build build) {
		this.build = build;
	}
	
	
	
	// Properties

	public JDateTime getDeploymentTime() {
		return deploymentTime;
	}

	public void setDeploymentTime(JDateTime deploymentTime) {
		this.deploymentTime = deploymentTime;
	}


	
	

}
