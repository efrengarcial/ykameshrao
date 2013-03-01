package com.yourpackagename.commons.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    public MyAuthenticationSuccessHandler() {
        super();
    }

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws ServletException, IOException {
    	 // This is actually not an error, but an OK message. It is sent to avoid redirects.
        response.sendError(HttpServletResponse.SC_OK);
    }

}
