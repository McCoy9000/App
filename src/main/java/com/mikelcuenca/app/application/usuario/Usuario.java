package com.mikelcuenca.app.application.usuario;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
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
	@JoinTable(name="Usuarios_Roles")
	private Set<Role> roles;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="Usuarios_Profiles")
	private Set<Profile> profiles;
	@ManyToOne
	private Identidad identidad;
	@Column
	private HashMap<String, Object> opciones;
	
	protected Usuario() {
	}
	
	public static final Usuario of() {
		return new Usuario();
	}

	public BigInteger getUsuarioId() {
		return usuarioId;
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
		return (roles == null) ? new HashSet<Role>() : roles;
	}

	public void setRoles(Set<Role> role) {
		this.roles = role;
	}

	public Set<Profile> getProfiles() {
		return (profiles == null) ? new HashSet<Profile>() : profiles;
	}

	public void setProfiles(Set<Profile> profiles) {
		this.profiles = profiles;
	}

	public Identidad getIdentidad() {
		return identidad;
	}

	public void setIdentidad(Identidad identidad) {
		this.identidad = identidad;
	}

	public Map<String, Object> getOpciones() {
		return (opciones == null) ? new HashMap<String, Object>() : opciones;
	}

	public void setOpciones(HashMap<String, Object> opciones) {
		this.opciones = opciones;
	}
}
