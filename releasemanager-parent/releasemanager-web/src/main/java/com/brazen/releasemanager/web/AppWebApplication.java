package com.brazen.releasemanager.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brazen.releasemanager.ReleaseManagerAppCore;
import com.brazen.releasemanager.service.ServerService;

import jodd.joy.madvoc.ProxettaAwareActionsManager;
import jodd.madvoc.component.MadvocConfig;
import jodd.madvoc.petite.PetiteWebApplication;
import jodd.petite.PetiteContainer;


public class AppWebApplication extends PetiteWebApplication {

	private final static Logger log = LoggerFactory.getLogger(AppWebApplication.class);
	
	protected final ReleaseManagerAppCore appCore;

	/**
	 * Creates web application.
	 */
	public AppWebApplication(boolean init) {
		appCore = new ReleaseManagerAppCore();
		if (init) {
			log.debug("Initialising web app - standalone?");
			super.initWebApplication();
		}
	}
	
	public AppWebApplication() {
		this(false);
	}

	/**
	 * Starts {@link AppCore application} before web application is initialized.
	 */
	@Override
	protected void initWebApplication() {
		appCore.start();
		super.initWebApplication();
		
		
	}

	/**
	 * Registers default and custom Madvoc components.
	 */
	@Override
	public void registerMadvocComponents() {
		super.registerMadvocComponents();
		registerComponent(AppMadvocController.class);
		registerComponent(AppMadvocConfig.class);
		registerComponent(new ProxettaAwareActionsManager(appCore.getProxetta()));
	}

	/**
	 * Defines application container for Madvoc usage. We will share applications
	 * Petite container from the appCore, so Madvoc can use it when creating
	 * Madvoc actions. By sharing the application container with the Madvoc,
	 * Petite beans may be injected in the actions.
	 * <p>
	 * If container is not shared, PetiteWebApplication would create
	 * new Petite container; that is fine when e.g. there are no layers.
	 */
	@Override
	protected PetiteContainer providePetiteContainer() {
		return appCore.getPetite();
	}

	/**
	 * Destroys application context and Madvoc.
	 */
	@Override
	protected void destroy(MadvocConfig madvocConfig) {
		appCore.stop();
		super.destroy(madvocConfig);
	}

}