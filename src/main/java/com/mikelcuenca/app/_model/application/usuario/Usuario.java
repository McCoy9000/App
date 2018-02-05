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
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
//TODO Trasladar named query al DAO
@NamedQuery(name = "Usuario.findFirst", query = "SELECT 1 FROM Usuario WHERE rownum = 1")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 5620593760401949871L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long usuarioId;
	@NotNull
	@Column(nullable=false, unique=true)
	private String username;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private String descripcion;
	//TODO ajustar politicas de carga
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="USUARIOS_ROLES")
	private Set<Role> roles;
	
	public Usuario() {
		
	}
		
	@Override
	public String toString() {
		return "Usuario [usuarioId=" + usuarioId + ", username=" + username + ", password=" + password + ", email="
				+ email + ", descripcion=" + descripcion + ", roles=" + roles + "]";
	}


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
