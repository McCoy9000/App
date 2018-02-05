package com.mikelcuenca.app.service.infrastructure.authentication;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class CustomLogoutHandler extends SecurityContextLogoutHandler {

}
