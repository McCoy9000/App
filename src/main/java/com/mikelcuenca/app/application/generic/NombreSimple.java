package com.mikelcuenca.app.application.generic;

import javax.persistence.Embeddable;

import org.apache.commons.text.WordUtils;

@Embeddable
public class NombreSimple extends Nombre {

	protected NombreSimple() {
	
	}
	
	protected NombreSimple(String nombre) {
		this.identificacion = nombre;
	}

	public NombreSimple of(String nombre) {
		return new NombreSimple(nombre);
	}

	@Override
	public String getIdentificacion() {
		return identificacion;
	}

	@Override
	public String getNombre() {
		return identificacion;
	}

	@Override
	public String getNombreCompleto() {
		return WordUtils.capitalize(identificacion);
	}

	@Override
	public void setNombreCompleto(String nombre, String apellido1, String apellido2) {	
		StringBuilder sb = new StringBuilder(nombre);
		sb.append(" ");
		sb.append(apellido1);
		sb.append(" ");
		sb.append(apellido2);
		this.identificacion = sb.toString().trim();
	}
}
