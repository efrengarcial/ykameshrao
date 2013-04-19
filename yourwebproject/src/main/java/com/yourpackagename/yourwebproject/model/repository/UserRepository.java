package com.yourpackagename.yourwebproject.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yourpackagename.yourwebproject.model.entity.User;

/**
 * DD Repository for User related actions and events
 * 
 * @author: Y Kamesh Rao
 * @created: 3/25/12 11:02 AM
 * @company: &copy; 2012, Kaleidosoft Labs
 * http://blog.42.nl/articles/spring-data-with-querydsl-repositories-made-easy
 */
@Repository 
public interface UserRepository extends UserRepositoryCustom, JpaRepository<User, Long> {
    /**
     * Finds a user with the given email
     *
     * @param email
     * @return
     */
	@Query("from User u where u.email = ?")
    public User findByEmail(String email);


    /**
     * Finds a user with the given username
     *
     * @param username
     * @return
     */
	@Query("from User u where u.userName = ?")
    public User findByUsername(String username);
}
