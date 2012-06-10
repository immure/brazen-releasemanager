package com.brazen.releasemanager.web.action;

import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brazen.releasemanager.domain.Server;
import com.brazen.releasemanager.domain.UserSession;
import com.brazen.releasemanager.service.ServerService;

import jodd.datetime.JDateTime;
import jodd.joy.madvoc.action.AppAction;
import jodd.joy.madvoc.meta.PostAction;
import jodd.jtx.meta.Transaction;
import jodd.madvoc.ScopeType;
import jodd.madvoc.meta.Action;
import jodd.madvoc.meta.In;
import jodd.madvoc.meta.MadvocAction;
import jodd.madvoc.meta.Out;
import jodd.petite.meta.PetiteInject;

import static jodd.joy.auth.AuthUtil.AUTH_SESSION_NAME;
import static jodd.madvoc.ScopeType.SESSION;

@MadvocAction
public class IndexAction extends AppAction {

	private static final Logger log = LoggerFactory
			.getLogger(IndexAction.class);

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

	@Action
	@Transaction
	public void view() {
		
	}
	
}
