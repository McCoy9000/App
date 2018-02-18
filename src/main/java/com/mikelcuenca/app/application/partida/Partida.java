package com.mikelcuenca.app.application.partida;

import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.mikelcuenca.app.application.profile.Director;
import com.mikelcuenca.app.application.profile.Jugador;

public class Partida {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long partidaId;
	@ManyToOne
	private Director director;
	@ManyToMany(mappedBy="partidas")
	private Set<Jugador> jugadores;
}
