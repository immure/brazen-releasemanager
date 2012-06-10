package com.brazen.releasemanager.domain;

import jodd.db.oom.meta.DbColumn;
import jodd.db.oom.meta.DbTable;
import jodd.joy.db.Entity;
import jodd.vtor.constraint.MaxLength;

@DbTable
public class User extends Entity {
	
	@DbColumn
	protected String hashpw;
	
	@DbColumn
	@MaxLength(100)
	protected String name;
	
	@DbColumn
	@MaxLength(20)
	protected String screenName;

	public String getHashpw() {
		return hashpw;
	}

	public void setHashpw(String hashpw) {
		this.hashpw = hashpw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
	
}
