package com.mikelcuenca.app._model.application.usuario;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * Clase de modelo para los roles del usuario
 * @author BICUGUAL
 */
@Entity
public class Role implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 8369703745305710922L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;
	@Column(nullable=false, unique=true)
	private String rolename;
	@Column
    private String description;
 	@ManyToMany(mappedBy="roles")
	private Set<Usuario> usuarios;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="ROLES_PERMISSIONS")
    private Set<Permission> permissions;
 
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", rolename=" + rolename + ", description=" + description + ", permissions="
				+ permissions + "]";
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
 	 
    public Set<Permission> getPermissions() {
        return permissions;
    }
 
    public void setPermissions(Set<Permission> privileges) {
        this.permissions = privileges;
    }
}