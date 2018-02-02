package com.mikelcuenca.app._model.infrastructure.authentication;

import org.springframework.security.core.GrantedAuthority;

/**
 * Clase de modelo que representa las authoritys del usuario
 * @author BICUGUAL
 */
public class Privilege implements GrantedAuthority {
	
	private static final long serialVersionUID = -6511833692857696569L;

	private Long privilegeId;

	private String name;

	public Privilege() {
		
	}
	
	public Privilege(String name) {
		this.name = name;
	}
	
	@Override  
	public String toString() {
	        StringBuilder builder = new StringBuilder();
	        builder.append("Privilege [name=");
	        builder.append(name);
	        builder.append("]");
	        return builder.toString();
    }
    
	public Long getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(Long id) {
		this.privilegeId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return this.name;
	}
}
