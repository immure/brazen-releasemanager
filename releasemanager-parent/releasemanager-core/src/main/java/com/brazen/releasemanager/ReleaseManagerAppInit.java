package com.brazen.releasemanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brazen.releasemanager.install.DbInstaller;

import jodd.joy.core.AppInit;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;

@PetiteBean("appInit")
public class ReleaseManagerAppInit implements AppInit {

	private static final Logger log = LoggerFactory.getLogger(ReleaseManagerAppInit.class);
	
	@PetiteInject
	AppData appData;
	
	@PetiteInject
	DbInstaller dbInstaller;
	
	public void init() {
		log.debug("app init");
		log.debug("brazen doc root: " + appData.getDocRoot());
		
		if (!dbInstaller.checkDb()) {
			dbInstaller.installDb();
		} else if (appData.isResetDatabase()) {
			dbInstaller.resetDb();
			dbInstaller.installDb();
		}
		
	}
	
	public void stop() {
		log.debug("app stop");
		
	}
	
}
