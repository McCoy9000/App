package com.mikelcuenca.app.infastructure.authentication.service;

import org.springframework.security.core.Authentication;

public interface AuthenticationHolder {

	public Authentication getAuthentication();
}
