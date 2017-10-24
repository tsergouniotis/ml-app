package com.tns.ml.core.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.tns.ml.core.domain.User;
import com.tns.ml.core.errors.UserNotFoundException;
import com.tns.ml.core.repository.UserRepository;

@Stateless
public class UserService {

	@Inject
	private UserRepository userRepository;

	public User findUser(Long userId) {
		return userRepository.find(userId);
	}

	public User findUser(String username, String password) throws UserNotFoundException {
		User user = userRepository.findByUsernameAndPassword(username, password);
		if (null != user) {
			return user;
		}

		throw new UserNotFoundException();

	}

}
