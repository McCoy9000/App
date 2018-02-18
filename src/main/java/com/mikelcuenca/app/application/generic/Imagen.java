package com.mikelcuenca.app.application.generic;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Lob;

@Embeddable
public class Imagen implements Serializable {

	private static final long serialVersionUID = -1581210471360476196L;
	
	@Column
	private String imagenNombre;
	@Column 
	private String imagenDescripcion;
	@Lob
	private byte[] imagenBytes;
	@Embedded
	private URL imagenUrl;
	@Column
	private String imagenFormat;
	
	protected Imagen() {
		
	}
		
	public static Imagen of() {
		return new Imagen();
	}
	
	
	public String getImagenNombre() {
		return imagenNombre;
	}

	public void setNombreImagen(String imagenNombre) {
		this.imagenNombre = imagenNombre;
	}

	public String getImagenDescripcion() {
		return imagenDescripcion;
	}

	public void setImagenDescripcion(String imagenDescripcion) {
		this.imagenDescripcion = imagenDescripcion;
	}

	public byte[] getImagenBytes() {
		return imagenBytes;
	}
	
	public void setImagenBytes(byte[] imagenBytes) {
		this.imagenBytes = imagenBytes;
	}
	
	public URL getImagenUrl() {
		return imagenUrl;
	}
	
	public void setImagenUrl(URL imagenUrl) {
		this.imagenUrl = imagenUrl;
	}

	public String getImagenFormat() {
		return imagenFormat;
	}

	public void setImagenFormat(String imagenFormat) {
		this.imagenFormat = imagenFormat;
	}
}
