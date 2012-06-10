package com.brazen.releasemanager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brazen.releasemanager.domain.Environment;
import com.brazen.releasemanager.domain.EnvironmentType;

import jodd.joy.db.AppDao;
import jodd.jtx.JtxPropagationBehavior;
import jodd.jtx.meta.Transaction;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean
public class EnvironmentService {
	
	private static final Logger log = LoggerFactory.getLogger(EnvironmentService.class);
	
	@PetiteInject
	AppDao appDao;
	
	@Transaction
	public Environment store(Environment e) {
		return appDao.store(e);
	}
	
	@Transaction
	public Environment findEnvironmentById(Long id) {
		return appDao.findById(Environment.class, id);
	}
	
	@Transaction
	public EnvironmentType findEnvironmentTypeByName(String name) {
		return appDao.findOneByProperty(EnvironmentType.class, "environmentTypeName", name);
	}

}
