package com.mikelcuenca.app.application.imagen;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import com.mikelcuenca.app.application.generic.Nombre;
import com.mikelcuenca.app.application.generic.URL;

@Entity
public class Imagen implements Serializable {

	private static final long serialVersionUID = -1581210471360476196L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger imagenId;
	@NotNull
	@Column(nullable=false, unique=true)
	private UUID codImagen = UUID.randomUUID();
	@Column
	private Nombre imagenNombre;
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
	
	
	public BigInteger getImagenId() {
		return imagenId;
	}

	public UUID getCodImagen() {
		return codImagen;
	}

	public void setCodImagen(UUID codImagen) {
		this.codImagen = codImagen;
	}

	public Nombre getImagenNombre() {
		return imagenNombre;
	}

	public void setImagenNombre(Nombre imagenNombre) {
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
