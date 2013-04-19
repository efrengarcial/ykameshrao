package com.yourpackagename.yourwebproject.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yourpackagename.yourwebproject.model.entity.User;

@ContextConfiguration
(
  {
   //"classpath:beans.xml",
   "file:src/main/resources/config/spring/applicationContext-service.xml",
   "file:src/main/resources/config/spring/applicationContext-properties.xml"
  }
)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
	
	private Logger log = LoggerFactory.getLogger(UserServiceTest.class);
	 
	@Autowired
	private UserService userService;  
	
	@Test
    public void isEmailExists() {
		userService.isEmailExists("efren.gl@gmail.com");
    }
	
	@Test
    public void findInactiveUsers() {
		List<User> usarios= userService.findInactiveUsers();
		log.info("findInactiveUsers=" + usarios.size());
    }
}
