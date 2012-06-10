package com.brazen.releasemanager.standalone;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jodd.db.ThreadDbSessionProvider;

import com.brazen.releasemanager.ReleaseManagerAppCore;
import com.brazen.releasemanager.domain.Server;
import com.brazen.releasemanager.service.ServerService;

public class DbLocalRunner {
	
	private final static Logger log = LoggerFactory.getLogger(DbLocalRunner.class);
	
	public static void main(String[] args) {
		ReleaseManagerAppCore appCore = new ReleaseManagerAppCore();
		appCore.start();
		
		ServerService serverService = (ServerService) appCore.getBean("serverService");
		
		Server server = new Server();
		server.setAddress("127.0.0.1");
		server.setName("localhost");
		serverService.storeServer(server);
		log.info("Stored server with id " + server.getId());
		
		List<Server> servers = serverService.findServerByName("localhost");
		log.info("Found: " + servers.size() + " servers with name of 'localhost'");
		
		ThreadDbSessionProvider.closeThreadDbSession();
		appCore.stop();
		
		
	}

}
