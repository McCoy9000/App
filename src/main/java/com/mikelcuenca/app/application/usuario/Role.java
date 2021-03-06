package com.mikelcuenca.app.application.usuario;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(indexes = {@Index(columnList = "codRole")})
public class Role implements Serializable {
 
	private static final long serialVersionUID = 8369703745305710922L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger roleId;
	@NotNull
	@Column(nullable=false, unique=true)
	private UUID codRole = UUID.randomUUID();
	@NotNull
	@Column(nullable=false, unique=true)
	private String rolename;
	@Column
    private String description;
 	@ManyToMany(mappedBy="roles")
	private Set<Usuario> usuarios;
 	//TODO ajustar políticas de carga
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="ROLES_PERMISSIONS")
    private Set<Permission> permissions;
 
	protected Role() {
		
	}
	
	protected Role(String authname) {
		this.rolename=authname;
	}

	public static Role of() {
		return new Role();
	}

	public static Role of(String authname) {
		return new Role(authname);
	}
	
	@Override
	public String toString() {
		return "Permission [roleId=" + roleId + ", rolename=" + rolename + ", description=" + description + ", permissions="
				+ permissions + "]";
	}

	public BigInteger getRoleId() {
		return roleId;
	}

	public void setRoleId(BigInteger roleId) {
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