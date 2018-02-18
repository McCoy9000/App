package com.mikelcuenca.app.application.profile;

import java.util.Set;

import javax.persistence.OneToMany;

import com.mikelcuenca.app.application.partida.Partida;

public class Director extends Profile {

	@OneToMany
	private Set<Partida> partidas;
	
	protected Director() {
		
	}
	
	public Director of() {
		return new Director();
	}
}
