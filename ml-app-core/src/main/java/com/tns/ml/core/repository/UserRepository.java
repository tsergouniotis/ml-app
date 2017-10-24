package com.tns.ml.core.repository;

import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tns.ml.core.domain.User;

public class UserRepository extends AbstractRepository<User, Long> {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

	protected UserRepository() {
		super(User.class);
	}

	public User findByUsernameAndPassword(String username, String password) {

		try {

			TypedQuery<User> q = em.createNamedQuery("User.findByUsernameAndPassword", User.class);
			q.setParameter("username", username);
			q.setParameter("password", password);

			return q.getSingleResult();
		} catch (Exception e) {
			LOGGER.warn("Could not find user due to error", e);
			return null;
		}

	}

	public User findByUsername(String username) {

		try {

			TypedQuery<User> q = em.createNamedQuery("User.findByUsername", User.class);
			q.setParameter("username", username);

			return q.getSingleResult();
		} catch (Exception e) {
			LOGGER.warn("Could not find user due to error", e);
			return null;
		}

	}

}
