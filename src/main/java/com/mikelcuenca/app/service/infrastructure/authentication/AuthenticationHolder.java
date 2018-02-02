package com.mikelcuenca.app.service.infrastructure.authentication;

import org.springframework.security.core.Authentication;

public interface AuthenticationHolder {

	public Authentication getAuthentication();
}
