package com.tns.ml.core.filters;

import java.security.Principal;
import java.util.Set;

import javax.ws.rs.core.SecurityContext;

public class Authorizer implements SecurityContext {

	private String username;

	private Set<String> roles;

	private boolean secure;

	public Authorizer(String username, Set<String> roles, boolean secure) {
		super();
		this.username = username;
		this.roles = roles;
		this.secure = secure;
	}

	@Override
	public Principal getUserPrincipal() {
		return new UserPrincipal(username);
	}

	@Override
	public boolean isUserInRole(String role) {
		return true;
	}

	@Override
	public boolean isSecure() {
		return secure;
	}

	@Override
	public String getAuthenticationScheme() {
		return "ml";
	}

}
