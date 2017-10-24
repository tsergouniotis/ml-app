package com.tns.ml.core.resources;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tns.ml.core.domain.User;
import com.tns.ml.core.filters.JWTTokenNeeded;
import com.tns.ml.core.services.UserService;

@Path("users")
@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@JWTTokenNeeded
public class UserResource {

	@Inject
	private UserService userService;

	@GET
	@Path("{uid}")
	public User getOrder(@PathParam("uid") Long id) {
		return userService.findUser(id);
	}

}
