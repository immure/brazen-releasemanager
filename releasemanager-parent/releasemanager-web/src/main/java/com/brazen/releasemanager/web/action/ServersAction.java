package com.brazen.releasemanager.web.action;

import static jodd.madvoc.ScopeType.SESSION;

import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jodd.datetime.JDateTime;
import jodd.joy.madvoc.action.AppAction;
import jodd.jtx.meta.Transaction;
import jodd.madvoc.ScopeType;
import jodd.madvoc.meta.Action;
import jodd.madvoc.meta.In;
import jodd.madvoc.meta.MadvocAction;
import jodd.madvoc.meta.Out;
import jodd.petite.meta.PetiteInject;

import com.brazen.releasemanager.domain.Server;
import com.brazen.releasemanager.domain.UserSession;
import com.brazen.releasemanager.service.ServerService;

@MadvocAction
public class ServersAction extends AppAction {
	
	private final static Logger log = LoggerFactory.getLogger(ServersAction.class);
	
	// petite

	@PetiteInject
	ServerService serverService;

	// atts, params, etc.

	@In(scope = ScopeType.SERVLET)
	Cookie[] cookie;

	@Out
	JDateTime now;

	@Out
	Server[] servers;

	@In(scope = SESSION, value = "AUTH")
	UserSession userSession;
	
	@In
	String serverName;
	
	@In
	String serverAddress;
	
	

	@Action
	@Transaction
	public void view() {
		log.debug("index view");
		now = new JDateTime();
		
		
		// tmp - add a server if it doesn't exist
		if (serverService.list().size() == 0) {
			log.debug("Adding a test server");
			Server s = new Server();
			s.setAddress("127.0.0.1");
			s.setName("localhost");
			serverService.storeServer(s);
		}
		
		
		servers = serverService.list().toArray(new Server[] {});
		log.debug(servers.length + " servers in list");
		
	}
	
	@Action
	@Transaction
	public String post() {
		Server s = new Server();
		s.setAddress(serverAddress);
		s.setName(serverName);
		serverService.storeServer(s);
		return "redirect:/servers.html";
	}

}
