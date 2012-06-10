package com.brazen.releasemanager.servers;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brazen.releasemanager.domain.Server;
import com.brazen.releasemanager.domain.ServerStatus;
import com.brazen.releasemanager.domain.ServerStatus.Health;
import com.brazen.releasemanager.service.ServerService;

import jodd.jtx.JtxPropagationBehavior;
import jodd.jtx.meta.Transaction;
import jodd.petite.meta.PetiteBean;
import jodd.petite.meta.PetiteInject;


@PetiteBean
public class ServerMonitor {
	
	private final static Logger log = LoggerFactory.getLogger(ServerMonitor.class);
	
	public final static String UNABLE_TO_PING = "Unable to ping server";
	public final static String UNABLE_TO_RESOLVE = "Unable to resolve address";
	
	@PetiteInject
	ServerService serverService;
	
	public void updateServerStatuses() {
		List<Server> servers = serverService.list();
		
		
	}
	
	@Transaction(propagation=JtxPropagationBehavior.PROPAGATION_REQUIRES_NEW)
	public ServerStatus getServerStatus(Server s) {
		ServerStatus status = new ServerStatus();
		status.setHealth(Health.OK);
		try {
			InetAddress inetAddress = InetAddress.getByName(s.getAddress());
			if (!pingable(inetAddress)) {
				status.setHealth(Health.ERROR);
				status.setWarningMessage(UNABLE_TO_PING);
			}
		} catch (UnknownHostException e) {
			if (log.isDebugEnabled()){
				log.debug("Unable to resolve address: " + s.getAddress() + " (" + e.getMessage() + ")");
			}
			status.setHealth(Health.ERROR);
			status.setWarningMessage(UNABLE_TO_RESOLVE);
		}
		
		return status;
		
	}
	
	protected boolean pingable(InetAddress inetAddress) {
		try {
			return inetAddress.isReachable(20);
		} catch (IOException e) {
			log.debug("Unable to contact server: " + inetAddress.getHostAddress() + " (" + e.getMessage() + ")");
			return false;
		}
	}
	
	

}
