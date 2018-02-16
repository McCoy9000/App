package com.mikelcuenca.app._model.application.usuario;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mikelcuenca.app.control.PruebasController;

@Entity
@Table(indexes = {@Index(columnList = "codUsuario"), @Index(columnList = "username")})
public class Usuario implements Serializable{

	private static final long serialVersionUID = 5620593760401949871L;
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PruebasController.class);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger usuarioId;
	@NotNull
	@Column(nullable=false, unique=true)
	private UUID codUsuario = UUID.randomUUID();
	@NotNull
	@Column(nullable=false, unique=true)
	private String username;
	@Column
	private String password;
	@ManyToOne
	private Identidad identidad;
	//TODO ajustar politicas de carga
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="USUARIOS_ROLES")
	private Set<Role> roles;
	
	protected Usuario() {
	}
	
	public static final Usuario of() {
		return new Usuario();
	}
	

	@Override
	public String toString() {
		return "Usuario [usuarioId=" + usuarioId + ", username=" + username + ", password=" + password + ", identidad=" + identidad.getDescripcion() + ", roles=" + roles + "]";
	}


	public BigInteger getUsuarioId() {
		return usuarioId;
	}
	
	public void setUsuarioId(@NotNull BigInteger usuarioId) {
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
	
	public Identidad getIdentidad() {
		return identidad;
	}
	
	public void setIdentidad(Identidad identidad) {
		this.identidad = identidad;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}	
}
