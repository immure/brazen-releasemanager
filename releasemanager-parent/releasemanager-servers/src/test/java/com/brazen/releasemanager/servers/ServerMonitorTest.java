package com.brazen.releasemanager.servers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.brazen.releasemanager.ReleaseManagerAppCore;
import com.brazen.releasemanager.domain.Server;
import com.brazen.releasemanager.domain.ServerStatus;
import com.brazen.releasemanager.domain.ServerStatus.Health;
import com.brazen.releasemanager.service.ServerService;

public class ServerMonitorTest {
	
	private static ReleaseManagerAppCore appCore;
	
	private static ServerService serverService;
	
	private static ServerMonitor serverMonitor;
	
	@BeforeClass
	public static void setUp() throws Exception {
		
		appCore = new ReleaseManagerAppCore();
		appCore.start();
	
		serverService = (ServerService) appCore.getBean("serverService");
		serverMonitor = (ServerMonitor) appCore.getBean("serverMonitor");
		
	}
	


	@Test
	public void testHealthy() {
		
		Server s = new Server();
		s.setAddress("127.0.0.1");
		s.setName("localhost");
		
		ServerStatus ss = serverMonitor.getServerStatus(s);
		
		assertEquals(ss.getHealth(), Health.OK);
		assertNull(ss.getWarningMessage());
		
	}
	
	@Test
	@Ignore // Takes a while to run..
	public void testBadAddress() {
		
		Server s = new Server();
		s.setAddress("giwrgoiwhoiuho");
		s.setName("localhost");
		
		ServerStatus ss = serverMonitor.getServerStatus(s);
		
		assertEquals(ss.getHealth(), Health.ERROR);
		assertEquals(ServerMonitor.UNABLE_TO_RESOLVE, ss.getWarningMessage());
		
	}
	
	@Test
	public void testUnpingable() {
		Server s = new Server();
		s.setAddress("1.0.0.0");
		s.setName("bad address");
		
		ServerStatus ss = serverMonitor.getServerStatus(s);
		assertEquals(ss.getHealth(), Health.ERROR);
		assertEquals(ServerMonitor.UNABLE_TO_PING, ss.getWarningMessage());
		
	}

}
