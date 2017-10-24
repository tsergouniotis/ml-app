package com.tns.ml.core.filters;

import java.io.IOException;
import java.security.Key;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tns.ml.core.jwt.KeyGenerator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Provider
@JWTTokenNeeded
@Priority(Priorities.AUTHENTICATION)
public class JWTAuthFilter implements ContainerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(JWTAuthFilter.class);

	@Inject
	private KeyGenerator keyGenerator;

	// ======================================
	// = Business methods =
	// ======================================

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		// Get the HTTP Authorization header from the request
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		logger.info("#### authorizationHeader : " + authorizationHeader);

		// Check if the HTTP Authorization header is present and formatted
		// correctly
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			logger.error("#### invalid authorizationHeader : " + authorizationHeader);
			throw new NotAuthorizedException("Authorization header must be provided");
		}

		// Extract the token from the HTTP Authorization header
		String token = authorizationHeader.substring("Bearer".length()).trim();

		try {

			// Validate the token
			Key key = keyGenerator.generateKey();
			Jws<Claims> res = Jwts.parser().setSigningKey(key).parseClaimsJws(token);

			logger.info("#### valid token : " + token);

			Object username = res.getBody().get("username");

			SecurityContext originalContext = requestContext.getSecurityContext();

			Set<String> roles = new HashSet<>();
			roles.add("ADMIN");
			Authorizer authorizer = new Authorizer("admin", roles, originalContext.isSecure());

			requestContext.setSecurityContext(authorizer);

		} catch (Exception e) {
			logger.error("#### invalid token : " + token);
			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
		}
	}

}
