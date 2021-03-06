package com.yourpackagename.yourwebproject.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author egarcia
 *
 */
public class CustomUsernamePasswordAuthenticationFilter  extends 	UsernamePasswordAuthenticationFilter   {

	@SuppressWarnings("deprecation")
	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, Authentication authResult)
					throws IOException, ServletException {
		super.successfulAuthentication(request, response, authResult);
		//super.successfulAuthentication(request, response, chain, authResult);
		System.out.println("==successful login==");
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed)
					throws IOException, ServletException {
		super.unsuccessfulAuthentication(request, response, failed);
		System.out.println("==failed login==");
	}

}
