package com.mikelcuenca.app._model.infrastructure.authentication;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class Identity extends User {

	private static final long serialVersionUID = -6739588973135518523L;

	private String ip;
	private Timestamp logintime;
	private Timestamp logouttime;

	/* Spring Security fields*/
    private List<Privilege> authorities;
     
    public Identity() {
		super("Anonymous", "AnonymousPassword", new ArrayList<Privilege>());
		
		Privilege anonymousPrivilege = new Privilege("ANONYMOUS_PRIVILEGE");
		List<Privilege> anonymousAuthorities = new ArrayList<Privilege>();
		anonymousAuthorities.add(anonymousPrivilege);
		this.setAuthorities(anonymousAuthorities);
	}
	
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for(Privilege privilege : this.authorities) {
			grantedAuthorities.add(privilege);
		}
		return grantedAuthorities;			
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Timestamp getLogintime() {
		return logintime;
	}

	public void setLogintime(Timestamp logintime) {
		this.logintime = logintime;
	}

	public Timestamp getLogouttime() {
		return logouttime;
	}

	public void setLogouttime(Timestamp logouttime) {
		this.logouttime = logouttime;
	}

	public void setAuthorities(List<Privilege> authorities) {
		this.authorities = authorities;
	}

}
