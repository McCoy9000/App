package com.mikelcuenca.app.application.mazo;

import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.mikelcuenca.app.application.carta.Carta;
import com.mikelcuenca.app.application.profile.Jugador;

public class Mazo {

	@ManyToOne
	private Jugador jugadores;
	@ManyToMany
	@JoinTable(name="MAZOS_CARTAS")
	private Carta cartas;
}
