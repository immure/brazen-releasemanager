package com.brazen.releasemanager.domain;

import jodd.datetime.JDateTime;

/**
 * Session information for logged user.
 * Will be stored in HTTP session of container, by web module.
 */
public class UserSession {

	protected final JDateTime sessionStart;
	protected User user;

	public UserSession(User user) {
		this.user = user;
		sessionStart = new JDateTime();
	}

	/**
	 * Returns current user.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Replaces current user withing this session.
	 * It should be done only when user data changes
	 * (on profile edit)
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Returns session start time. This is time of user login.
	 */
	public JDateTime getSessionStart() {
		return sessionStart;
	}

	@Override
	public String toString() {
		return '[' + user.getScreenName() + ']';
	}
}