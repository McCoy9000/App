package com.mikelcuenca.app.application.profile;

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

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long profileId;
	@Column
	private String profileName;
	@Column
	private String profileDescription;
	@ManyToMany(mappedBy="profiles")
	private Set<Usuario> usuarios;
	
	protected Profile() {
	
	}
	
	public Profile of() {
		return new Profile();
	}
}
