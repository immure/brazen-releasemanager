package com.brazen.releasemanager.web;

import jodd.madvoc.petite.PetiteMadvocController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The heart of the Madvoc. We will extend it to simply bypass all
 * requests that starts with '/google' - since it is used for
 * Google site verification (webmaster tools). 
 * <p>
 * Note: common problem is to subclass just <code>MadvocController</code>
 * but we need Petite-aware Madvoc controller here.
 */
public class AppMadvocController extends PetiteMadvocController {

	private static final String GOOGLE = "/google";

	@Override
	public String invoke(String actionPath, HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws Exception {
		if (actionPath.startsWith(GOOGLE)) {
			return actionPath;
		}
		return super.invoke(actionPath, servletRequest, servletResponse);
	}
}
