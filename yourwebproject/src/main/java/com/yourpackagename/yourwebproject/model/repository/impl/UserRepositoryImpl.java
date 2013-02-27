package com.yourpackagename.yourwebproject.model.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.yourpackagename.yourwebproject.model.entity.User;
import com.yourpackagename.yourwebproject.model.repository.UserRepositoryCustom;

public class UserRepositoryImpl implements UserRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<User> findInactiveUsers() {
		return em.createQuery(
				"from User where active = false", User.class
				).getResultList();
	}
}