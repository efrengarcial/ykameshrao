package com.yourpackagename.yourwebproject.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yourpackagename.framework.exception.database.NotFoundException;
import com.yourpackagename.framework.validation.EntityValidator;
import com.yourpackagename.framework.validation.Validity;
import com.yourpackagename.yourwebproject.common.Key;
import com.yourpackagename.yourwebproject.common.Message;
import com.yourpackagename.yourwebproject.model.entity.User;
import com.yourpackagename.yourwebproject.model.repository.UserRepository;
import com.yourpackagename.yourwebproject.service.UserService;

/**
 * Service impl class to implement services for accessing the User object entity.
 * This class acts as an interface between the outer world and the UserRepository
 *
 * @author: Y Kamesh Rao
 * @created: 3/25/12 11:05 AM
 * @company: &copy; 2012, Kaleidosoft Labs
 */
@Service(value="userService")
@Transactional
public class UserServiceImpl  implements UserService, UserDetailsService{
	
	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
    private  UserRepository userRepository;
	
	@Autowired
    private  Message msg;
	
	@Autowired
    private  Key key;


    @PostConstruct
    public void setupService() {
     
    }


    @Override public boolean isValidPass(User user, String pass) {
        return user.getPassWord().equals(User.hashPassword(pass));
    }


    @Override public User registerUser(User user, HttpServletRequest request) {
        user.setPassWord(User.hashPassword(user.getPassWord()));
        user.setCurrentLoginAt(new Date());
        user.setCurrentLoginIp(request.getRemoteHost());
        user.setLoginCount(0);
        return userRepository.save(user);
    }


    @Override public User loginUser(final User user, final HttpServletRequest request) {
        user.setLastLoginAt(user.getCurrentLoginAt());
        user.setLastLoginIp(user.getCurrentLoginIp());
        user.setCurrentLoginAt(new Date());
        user.setCurrentLoginIp(request.getRemoteHost());
        user.setLoginCount(user.getLoginCount() + 1);
        user.setUpdatedAt(new Date());

        return userRepository.save(user);
    }


    @Override public boolean isUsernameExists(String username) {
        if (userRepository.findByUsername(username) != null) {
            return true;
        } else
            return false;
    }


    @Override public boolean isEmailExists(String email) {
        if (userRepository.findByEmail(email) != null) {
            return true;
        } else
            return false;
    }


    @Override public Validity validate(User user) {
        EntityValidator<User> entityValidator = new EntityValidator<User>();
        Validity validity = entityValidator.validate(user, User.class);

        // Check for username and email uniqueness
        if (isUsernameExists(user.getUserName())) {
            validity.addError(msg.userExists);
        }

        if (isEmailExists(user.getEmail())) {
            validity.addError(msg.emailExists);
        }

        return validity;
    }


    @Override public User findByUsername(String username) throws NotFoundException {
        User user = userRepository.findByUsername(username);

        if(user != null) {
            return user;
        } else {
            throw new NotFoundException(key.unfMsg, key.unfCode);
        }
    }
    
    @Override
	public List<User> findInactiveUsers() {
		return userRepository.findInactiveUsers();
	}


    @Override
    public UserDetails loadUserByUsername(String username)
    		throws UsernameNotFoundException {
    	// Declare a null Spring User
    	UserDetails userDetails = null;

    	User user = userRepository.findByUsername(username);
    	if(user != null) {
    		userDetails =  new org.springframework.security.core.userdetails.User(
    				user.getUserName(), 
    				user.getPassWord(),
    				true,
    				true,
    				true,
    				true,
    				getAuthorities(1)); //TODO: configuracion de roless
    	} else {
    		throw new UsernameNotFoundException(key.unfMsg );
    	}
    	return userDetails;
    }

	/**
	 * Retrieves the correct ROLE type depending on the access level, where access level is an Integer.
	 * Basically, this interprets the access value whether it's for a regular user or admin.
	 * 
	 * @param access an integer value representing the access of the user
	 * @return collection of granted authorities
	 */
	public Collection<GrantedAuthority> getAuthorities(Integer access) {
		// Create a list of grants for this user
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

		// All users are granted with ROLE_USER access
		// Therefore this user gets a ROLE_USER by default
		log.debug("Grant ROLE_USER to this user");
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));

		// Check if this user has admin access 
		// We interpret Integer(1) as an admin user
		if ( access.compareTo(1) == 0) {
			// User has admin access
			log.debug("Grant ROLE_ADMIN to this user");
			authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		// Return list of granted authorities
		return authList;
	}
}
