package com.mikelcuenca.app._model.application.usuario;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Embeddable
@Access(AccessType.FIELD)
@Slf4j
@Getter @Setter @NoArgsConstructor
@NamedQuery(name = "Usuario.findFirst", query = "SELECT 1 FROM Usuario WHERE rownum = 1")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 5620593760401949871L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long usuarioId;
	@Column(nullable=false, unique=true)
	private String username;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private String descripcion;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="USUARIOS_ROLES")
	private Set<Role> roles;
	
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
}
