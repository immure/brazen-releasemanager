package com.brazen.releasemanager;

import com.brazen.releasemanager.domain.Environment;
import com.brazen.releasemanager.domain.EnvironmentType;
import com.brazen.releasemanager.type.EnvironmentSqlType;
import com.brazen.releasemanager.type.EnvironmentTypeSqlType;

import jodd.db.DbDefault;
import jodd.db.ThreadDbSessionProvider;
import jodd.db.oom.DbOomManager;
import jodd.db.oom.config.AutomagicDbOomConfigurator;
import jodd.db.type.SqlTypeManager;
import jodd.joy.core.DefaultAppCore;
import jodd.util.SystemUtil;

public class ReleaseManagerAppCore extends DefaultAppCore {
	
	public static ReleaseManagerAppCore	ref;
	
	public ReleaseManagerAppCore() {
		ReleaseManagerAppCore.ref = this;
	}
	
	/**
	 * Initializes system: uphea dir and custom types.
	 */
	@Override
	public void init() {
		// resolves uphea directory
		String upheaDir = System.getenv("brazen.dir");

		if (upheaDir == null) {
			upheaDir = System.getProperty("brazen.dir");
		}

		if (upheaDir == null) {
			upheaDir = SystemUtil.getUserDir();
		}
		System.setProperty("brazen.dir", upheaDir);

		System.out.println("brazen.dir: " + upheaDir);

		// additional sql types
		SqlTypeManager.register(Environment.class, EnvironmentSqlType.class);
		SqlTypeManager.register(EnvironmentType.class, EnvironmentTypeSqlType.class);
//		TypeConverterManager.register(UserLevel.class, new UserLevelTypeConverter());
		
		DbDefault.sessionProvider = new ThreadDbSessionProvider(true);
		
//		AutomagicDbOomConfigurator dbcfg = new AutomagicDbOomConfigurator();
//		dbcfg.setIncludedEntries(new String[] { this.getClass().getPackage().getName() + ".domain.*"});
//		dbcfg.configure(DbOomManager.getInstance());

		super.init();
		
		

		log.info("brazen.dir: " + upheaDir);
	}
	
	public Object getBean(String name) {
		return petite.getBean(name);
	}
	
	public boolean isStapleHtml() {
		return false;
	}
	
	@Override
	public void stop() {
		ref=null;
		super.stop();
	}

}
