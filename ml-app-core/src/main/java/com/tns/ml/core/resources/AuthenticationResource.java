package com.tns.ml.core.resources;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tns.ml.core.domain.User;
import com.tns.ml.core.errors.UserNotFoundException;
import com.tns.ml.core.jwt.KeyGenerator;
import com.tns.ml.core.services.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("auth")
public class AuthenticationResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationResource.class);

	@Context
	private UriInfo uriInfo;

	@Context
	SecurityContext sctx;

	@Inject
	private KeyGenerator keyGenerator;

	@Inject
	private UserService userService;

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response authenticateUser(@FormParam("login") String login, @FormParam("password") String password) {

		try {

			LOGGER.info("login: " + login);
			if (Objects.isNull(login)) {
				return Response.status(Status.BAD_REQUEST).build();
			}

			// Authenticate the user using the credentials provided
			authenticate(login, password);

			// Issue a token for the user
			String token = issueToken(login);

			// Return the token on the response
			return Response.ok(token).header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();

		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

	private String issueToken(String login) {
		Key key = keyGenerator.generateKey();
		return Jwts.builder().setSubject(login).setIssuer(uriInfo.getAbsolutePath().toString()).setIssuedAt(new Date()).setExpiration(toDate(LocalDateTime.now().plusHours(2L)))
				.signWith(SignatureAlgorithm.HS512, key).compact();
	}

	private void authenticate(String login, String password) throws Exception {

		try {
			User user = userService.findUser(login, password);
			LOGGER.info("User authenticated " + user.getUsername());
		} catch (UserNotFoundException e) {
			throw new SecurityException("Invalid user/password");
		}
	}

	private Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

}
