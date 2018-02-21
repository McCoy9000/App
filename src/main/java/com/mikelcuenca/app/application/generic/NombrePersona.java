package com.mikelcuenca.app.application.generic;

import javax.persistence.Embeddable;

@Embeddable
public class NombrePersona extends Nombre {

	private String nombre;
	private String apellido1, apellido2;
	private String apodo;
	
	private static final String ANONIMO = "ANONIMO";
	
	protected NombrePersona() {
		this.identificacion = ANONIMO;
		this.nombre = ANONIMO;
	}
	
	protected NombrePersona(String nombre) {
		this.identificacion = nombre;
		this.nombre = nombre;
	}

	public static Nombre of() {
		return new NombrePersona();
	}
	public static Nombre ofName(String nombre) {
		return new NombrePersona(nombre);
	}
	
	public String getNombreCompleto() {
		StringBuilder nombreCompleto = new StringBuilder(nombre);
			if (apellido1 != null) {
				nombreCompleto.append(" ");
				nombreCompleto.append(apellido1);
			} else {
				nombreCompleto.append(" ");
				nombreCompleto.append("no hay primer apellido");
			}
				nombreCompleto.append(" ");
				nombreCompleto.append(apellido2);
				
		return nombreCompleto.toString();
	}

	public void setNombreCompleto(String nombre, String apellido1, String apellido2) {
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2	= apellido2;
	}
	
	@Override
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	
	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getApodo() {
		return apodo;
	}
	
	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	@Override
	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
}

