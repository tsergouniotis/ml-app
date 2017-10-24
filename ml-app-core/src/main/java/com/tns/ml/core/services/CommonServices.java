package com.tns.ml.core.services;

import java.security.Principal;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tns.ml.core.domain.User;
import com.tns.ml.core.errors.UserNotFoundException;
import com.tns.ml.core.repository.UserRepository;

@Stateless
public class CommonServices {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonServices.class);

	@Inject
	private Principal principal;

	@Inject
	private UserRepository userRepository;

	public User getLoggedUser() throws UserNotFoundException {

		try {
			String username = "admin";// principal.getName();
			return userRepository.findByUsername(username);
		} catch (Exception e) {
			LOGGER.warn("Could not find user ", e);
			throw new UserNotFoundException();
		}
	}

}
