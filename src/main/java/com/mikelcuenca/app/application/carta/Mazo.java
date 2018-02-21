package com.mikelcuenca.app.application.carta;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.mikelcuenca.app.application.profile.Jugador;

@Entity
public class Mazo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger mazoId;
	@ManyToOne
	private Jugador jugador;
	@ManyToMany
	@JoinTable(name="MAZOS_CARTAS")
	private List<Carta> cartas;
	
	protected Mazo() {
		
	}
	
	public Mazo of() {
		return new Mazo();
	}

	public BigInteger getMazoId() {
		return mazoId;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public List<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(ArrayList<Carta> cartas) {
		this.cartas = cartas;
	}
	
	
}
