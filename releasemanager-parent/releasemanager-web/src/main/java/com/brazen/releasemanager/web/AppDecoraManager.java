package com.brazen.releasemanager.web;

import jodd.decora.DecoraManager;
import jodd.util.Wildcard;

import javax.servlet.http.HttpServletRequest;

/**
 * Application custom Decora manager.
 */
public class AppDecoraManager extends DecoraManager {

	/**
	 * Resolves decorators for action paths. In additional to defaults,
	 * we need to enable decoration for "/q/*" paths as well.
	 */
	@Override
	public String resolveDecorator(HttpServletRequest request, String actionPath) {
		if (actionPath.endsWith(".html")) {
			return DEFAULT_DECORATOR;
		}
		if (Wildcard.match(actionPath, "/q/*")) {
			return DEFAULT_DECORATOR;
		}
		return null;
	}
}
