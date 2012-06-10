package com.brazen.releasemanager.web.filter;

import jodd.lagarto.TagAdapter;
import jodd.lagarto.TagWriter;
import jodd.lagarto.adapter.StripHtmlTagAdapter;
import jodd.lagarto.adapter.htmlstapler.HtmlStaplerBundlesManager;
import jodd.lagarto.adapter.htmlstapler.HtmlStaplerTagAdapter;
import jodd.lagarto.filter.SimpleLagartoServletFilter;
import jodd.log.Log;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.brazen.releasemanager.ReleaseManagerAppCore;

import static jodd.lagarto.adapter.htmlstapler.HtmlStaplerBundlesManager.Strategy.*;

/**
 * Lagarto tag adapters.
 */
public class AppLagartoServletFilter extends SimpleLagartoServletFilter {

	private static final Log log = Log.getLogger(AppLagartoServletFilter.class);

	private HtmlStaplerBundlesManager bundlesManager;

	protected boolean htmlStaplerEnabled = true;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);

/*
		HtmlStaplerBundlesManager bundlesManager = new HtmlStaplerBundlesManager(filterConfig.getServletContext(), ACTION_MANAGED) {
			@Override
			protected String resolveRealActionPath(String actionPath) {
				if (actionPath.startsWith("/q/")) {
					actionPath = "/index.html";
				}
				return actionPath;
			}
		};
*/

		bundlesManager = new HtmlStaplerBundlesManager(filterConfig.getServletContext(), RESOURCES_ONLY);

		bundlesManager.reset();
	}

	@Override
	protected LagartoParsingProcessor createParsingProcessor() {

		if (ReleaseManagerAppCore.ref.isStapleHtml() == false) {
			return null;
		}

		return new LagartoParsingProcessor() {
			@Override
			protected char[] parse(TagWriter rootTagWriter, HttpServletRequest request) {
				
				TagAdapter tagAdapter = new StripHtmlTagAdapter(rootTagWriter) {
					@Override
					public void end() {
						super.end();
						if (log.isDebugEnabled()) {
							log.debug("Stripped: " + getStrippedCharsCount() + " chars.");
						}
					}
				};

				HtmlStaplerTagAdapter htmlStaplerTagAdapter = null;

				if (htmlStaplerEnabled) {
					htmlStaplerTagAdapter = new HtmlStaplerTagAdapter(tagAdapter, request);
					tagAdapter = htmlStaplerTagAdapter;
				}

				char[] content = invokeLagarto(tagAdapter);

				if (htmlStaplerEnabled) {
					content = htmlStaplerTagAdapter.postProcess(content);
				}

				return content;
			}
		};
	}

	@Override
	protected boolean acceptActionPath(HttpServletRequest request, String actionPath) {
		// skip html stapler servlet path from lagarto processing!!!
		if (actionPath.equals(bundlesManager.getStaplerServletPath())) {
			return false;
		}
		return super.acceptActionPath(request, actionPath);
	}
}