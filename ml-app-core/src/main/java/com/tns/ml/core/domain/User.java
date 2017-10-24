package com.tns.ml.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.tns.ml.core.repository.converters.JPACryptoConverter;

@Entity
@Table(name = "users")
@NamedQueries({ @NamedQuery(name = "User.findByUsernameAndPassword", query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password"),
		@NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username") })
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1348607099672953094L;

	@Id
	@Column(name = "database_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERSEQ")
	@SequenceGenerator(name = "USERSEQ", sequenceName = "userseq", allocationSize = 1)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	@Convert(converter = JPACryptoConverter.class)
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
