package com.mikelcuenca.app.application.generic;

public abstract class Nombre {

	protected String identificacion;
	
	public abstract String getIdentificacion();
	public abstract String getNombre();
	public abstract String getNombreCompleto();
	public abstract void setNombreCompleto(String nombre, String apellido1, String apellido2);
}
