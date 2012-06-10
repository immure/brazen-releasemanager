package com.brazen.releasemanager.domain;

import jodd.db.oom.meta.DbColumn;
import jodd.db.oom.meta.DbTable;
import jodd.joy.db.Entity;

/**
 * An artifact is a discrete deployable file which forms part of a 'build'. 
 * 
 * @author mike
 *
 */
@DbTable
public class Artifact extends Entity {
	
	@DbColumn String artifactId;
	@DbColumn String version;
	
	// Properties
	
	public String getArtifactId() {
		return artifactId;
	}
	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	

}
