package com.mikelcuenca.app.application.partida;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.mikelcuenca.app.application.profile.Director;
import com.mikelcuenca.app.application.profile.Jugador;

@Entity
public class Partida {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger partidaId;
	@ManyToOne
	private Director director;
	@ManyToMany(mappedBy="partidas")
	private Set<Jugador> jugadores;
	
	
	protected Partida() {
		
	}
	
	public Partida of() {
		return new Partida();
	}
	
	
	public BigInteger getPartidaId() {
		return partidaId;
	}
	public Director getDirector() {
		return director;
	}
	public void setDirector(Director director) {
		this.director = director;
	}
	public Set<Jugador> getJugadores() {
		return jugadores;
	}
	public void setJugadores(HashSet<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	
}
