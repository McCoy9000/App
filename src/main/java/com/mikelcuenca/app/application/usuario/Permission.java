package com.mikelcuenca.app.application.usuario;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(indexes = {@Index(columnList = "codPermission")})
public class Permission implements Serializable {
	
	private static final long serialVersionUID = 987915197363889984L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger permissionId;
	@NotNull
	@Column(nullable=false, unique=true)
	private UUID codPermission = UUID.randomUUID();
	@NotNull
	@Column(nullable=false, unique=true)
	private String permissionName;
	@Column
	private String description;

	@ManyToMany(mappedBy="permissions")
	Set<Role> roles;
	
	public Permission() {
	}
	
	public static Permission of() {
		return new Permission();
	}
	
	@Override
	public String toString() {
		return "Permission [codPermission=" + codPermission + ", permissionName=" + permissionName + ", description="
				+ description + "]";
	}
	public BigInteger getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(BigInteger permissionId) {
		this.permissionId = permissionId;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
