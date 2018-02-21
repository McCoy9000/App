package com.mikelcuenca.app.application.profile;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.mikelcuenca.app.application.usuario.Usuario;

@Entity
public class Profile {

	private enum ProfileName {
		DIRECTOR ("director"),
		JUGADOR ("jugador");
		
		private String nombre;
		
		private ProfileName(String nombre) {
			this.nombre = nombre;
		}
		public String getNombre() {
			return nombre;
		}
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger profileId;
	@Column
	private ProfileName profileName;
	@Column
	private String profileDescription;
	@ManyToMany(mappedBy="profiles")
	private Set<Usuario> usuarios;
	
	protected Profile() {
	
	}

	protected Profile(ProfileName profileName) {
		this.profileName = profileName;
	}
	public Profile of() {
		return new Profile();
	}
	
	public Profile of(ProfileName profileName) {
		return new Profile(profileName);
	}
	
	public BigInteger getProfileId() {
		return profileId;
	}

	public String getProfileDescription() {
		return profileDescription;
	}

	public void setProfileDescription(String profileDescription) {
		this.profileDescription = profileDescription;
	}

	public Set <Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(HashSet<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void setProfileName(ProfileName profileName) {
		this.profileName = profileName;
	}

	public String getProfileName() {
		return profileName.getNombre();
	}
}
