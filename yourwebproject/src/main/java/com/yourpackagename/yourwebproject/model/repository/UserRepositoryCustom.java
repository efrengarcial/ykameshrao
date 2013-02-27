package com.yourpackagename.yourwebproject.model.repository;

import java.util.List;

import com.yourpackagename.yourwebproject.model.entity.User;

public interface UserRepositoryCustom {
	// Custom method
	 List<User> findInactiveUsers();
}
