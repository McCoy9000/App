package com.mikelcuenca.app.application.usuario;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Map;
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

import com.mikelcuenca.app.application.generic.Opcion;
import com.mikelcuenca.app.application.profile.Profile;

@Entity
@Table(indexes = {@Index(columnList = "codUsuario"), @Index(columnList = "username")})
public class Usuario implements Serializable{

	private static final long serialVersionUID = 5620593760401949871L;
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(Usuario.class);

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
	//TODO ajustar politicas de carga
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="USUARIOS_ROLES")
	private Set<Role> roles;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="USUARIOS_PROFILES")
	private Map<Long, Profile> profiles;
	@ManyToOne
	private Identidad identidad;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="USUARIOS_OPCIONES")
	private Map<Long, Opcion> opciones;
	
	protected Usuario() {
	}
	
	public static final Usuario of() {
		return new Usuario();
	}

	public BigInteger getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(BigInteger usuarioId) {
		this.usuarioId = usuarioId;
	}

	public UUID getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(UUID codUsuario) {
		this.codUsuario = codUsuario;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Map<Long, Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(Map<Long, Profile> profiles) {
		this.profiles = profiles;
	}

	public Identidad getIdentidad() {
		return identidad;
	}

	public void setIdentidad(Identidad identidad) {
		this.identidad = identidad;
	}

	public Map<Long, Opcion> getOpciones() {
		return opciones;
	}

	public void setOpciones(Map<Long, Opcion> opciones) {
		this.opciones = opciones;
	}
	
}