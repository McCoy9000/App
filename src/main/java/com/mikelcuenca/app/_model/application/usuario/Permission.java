package com.mikelcuenca.app._model.application.usuario;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Permission implements Serializable {
	
	private static final long serialVersionUID = 987915197363889984L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idPermission;
	@NotNull
	@Column(nullable=false, unique=true)
	private String permissionname;
	@Column
	private String description;

	@ManyToMany(mappedBy="permissions")
	Set<Role> roles;
	
	public Permission() {
		
	}
	
	@Override
	public String toString() {
		return "Permission [idPermission=" + idPermission + ", permissionname=" + permissionname + ", description="
				+ description + "]";
	}
	public Long getIdPermission() {
		return idPermission;
	}
	public void setIdPermission(Long idPermission) {
		this.idPermission = idPermission;
	}
	public String getPermissionname() {
		return permissionname;
	}
	public void setPermissionname(String permissionname) {
		this.permissionname = permissionname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
