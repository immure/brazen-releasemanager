package com.brazen.releasemanager.domain;

import jodd.db.oom.meta.DbColumn;
import jodd.db.oom.meta.DbTable;

@DbTable
public class ServerStatus {
	
	@DbColumn String health;
	@DbColumn String warningMessage;
	
	public static enum Health { OK, WARNING, ERROR };
	
	public Health getHealth() {
		return Health.valueOf(health);
	}
	public void setHealth(Health health) {
		this.health = health.name();
	}
	public String getWarningMessage() {
		return warningMessage;
	}
	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}
	

}
