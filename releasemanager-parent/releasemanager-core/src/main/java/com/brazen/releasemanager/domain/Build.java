package com.brazen.releasemanager.domain;

import java.util.ArrayList;
import java.util.List;

import jodd.db.oom.meta.DbColumn;
import jodd.db.oom.meta.DbTable;
import jodd.joy.db.Entity;

/**
 * A 'build' is a collection of artifacts which between them form a 
 * discrete deployable application or part of application.
 * 
 * @author mike
 *
 */
@DbTable
public class Build extends Entity {
	
	@DbColumn String buildId; 
	@DbColumn String version;
	
	// Relationships
	
	private List<Artifact> artifacts = new ArrayList<Artifact>();

	public List<Artifact> getArtifacts() {
		return artifacts;
	}

	public void setArtifacts(List<Artifact> artifacts) {
		this.artifacts = artifacts;
	}
	
	private Product product;
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
	
	
	
	// Properties 

	public String getBuildId() {
		return buildId;
	}

	public void setBuildId(String buildId) {
		this.buildId = buildId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	

	
	
	
	

}
