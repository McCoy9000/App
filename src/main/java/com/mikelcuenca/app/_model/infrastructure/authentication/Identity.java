package com.mikelcuenca.app._model.infrastructure.authentication;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Identity implements UserDetails {

	private static final long serialVersionUID = -6739588973135518523L;

	private String password;
	private final String username;
	
	private String ip;
	private Timestamp logintime;
	private Timestamp logouttime;

	/* Spring Security fields*/
    private List<Privilege> authorities;
    private final boolean accountNonExpired = true;
	private final boolean accountNonLocked = true;
	private final boolean credentialsNonExpired = true;
	private final boolean enabled = true; 
    
	public Identity() {
		this.username = "Anonymous";
		this.password = "AnonymousPassword";
		
		Privilege anonymousPrivilege = new Privilege("ANONYMOUS_PRIVILEGE");
		List<Privilege> anonymousAuthorities = new ArrayList<Privilege>();
		anonymousAuthorities.add(anonymousPrivilege);
		this.authorities = anonymousAuthorities;
	}
	
	public Identity(String username, List<Privilege> authorities) {
		super();
		this.username = username;
		this.authorities = authorities;
	}

	public Identity(String username, String password, List<Privilege> authorities) {
		super();
		this.password = password;
		this.username = username;
		this.authorities = authorities;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for(Privilege privilege : this.authorities) {
			grantedAuthorities.add(privilege);
		}
		return grantedAuthorities;			
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
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

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	
}
