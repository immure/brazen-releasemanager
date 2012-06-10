package com.brazen.releasemanager.domain;

import jodd.db.oom.meta.DbColumn;
import jodd.db.oom.meta.DbTable;
import jodd.joy.db.Entity;

@DbTable
public class Product extends Entity {

	@DbColumn String productName;
	
}
