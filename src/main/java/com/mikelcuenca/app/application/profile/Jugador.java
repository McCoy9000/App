package com.mikelcuenca.app.application.profile;

import java.util.Set;

import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.mikelcuenca.app.application.mazo.Mazo;
import com.mikelcuenca.app.application.partida.Partida;

public class Jugador extends Profile {

	@ManyToMany
	@JoinTable(name="JUGADORES_PARTIDAS")
	private Set<Partida> partidas;
	@OneToMany
	private Set<Mazo> mazos;
	
	protected Jugador() {
		
	}
	
	public Jugador of() {
		return new Jugador();
	}
}
