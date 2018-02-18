package com.mikelcuenca.app.infastructure.authentication.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationHolderImpl implements AuthenticationHolder{

	public Authentication getAuthentication() {
		
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
