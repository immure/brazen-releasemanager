package com.brazen.releasemanager.domain;

import java.util.ArrayList;
import java.util.List;

import jodd.db.oom.meta.DbColumn;
import jodd.db.oom.meta.DbTable;
import jodd.joy.db.Entity;

/**
 * 
 * A server is a logical unit capable of hosting a deployment
 * 
 * @author mike
 *
 */
@DbTable
public class Server extends Entity {

	@DbColumn
	String name;
	
	@DbColumn
	String address;
	
	@DbColumn
	private
	ServerStatus status;
	
	
	// Relationships
	
	@DbColumn
	private
	Environment environment;

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	private List<Deployment> deployments = new ArrayList<Deployment>();
	
	public List<Deployment> getDeployments() {
		return deployments;
	}

	public void setDeployments(List<Deployment> deployments) {
		this.deployments = deployments;
		for (Deployment deployment : deployments) {
			deployment.setServer(this);
		}
	}
	
	public void addDeployment(Deployment deployment) {
		deployments.add(deployment);
		deployment.setServer(this);
	}
	
	public ServerStatus getStatus() {
		return status;
	}

	public void setStatus(ServerStatus status) {
		this.status = status;
	}

	// Properties
	


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}