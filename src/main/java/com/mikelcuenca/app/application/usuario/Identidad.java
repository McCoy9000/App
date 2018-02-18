package com.mikelcuenca.app.application.usuario;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mikelcuenca.app.application.generic.Email;
import com.mikelcuenca.app.application.generic.Imagen;

@Entity
public class Identidad implements Serializable {

	private static final long serialVersionUID = 5109798777834960591L;

	private static final Logger logger = LoggerFactory.getLogger(Identidad.class);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger id;
	@NotNull
	@Column(nullable=false, unique=true)
	private UUID codIdentidad;
	@NotNull
	@Column(nullable=false, unique=true)
	private String nombre;
	private String apellidos;
	@OneToMany
	private Set<Usuario> usuarios;
	private String alias;
	@Embedded
	private Email email;
	private String descripcion;
	@Embedded
	private Imagen imagen;
	
	protected Identidad() {
	}

	public static Identidad of(String email) {
		Identidad identidad = new Identidad();
		identidad.codIdentidad = UUID.randomUUID();
		identidad.email = Email.of(email);
		return identidad; 
	}
	
	protected Identidad(Email email) {
		this.email = email;
	}

	protected String getNombre() {
		return nombre;
	}

	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}

	protected String getApellidos() {
		return apellidos;
	}

	protected void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	protected String getAlias() {
		return null;
	}

	protected void setAlias(String alias) {
		this.alias = alias;
	}

	protected Email getEmail() {
		return email;
	}

	protected void setEmail(Email email) {
		this.email = email;
	}

	protected String getDescripcion() {
		return descripcion;
	}

	protected void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	protected Imagen getImagen() {
		return imagen;
	}

	protected void setImagen(Imagen imagen) {
		this.imagen = imagen;
	}

	protected String getEmailAddress() {
		return email.getEmailAddress();
	}

	protected String getImagenUrl() {
		return imagen.getImagenUrl().getUrlAddress();
	}

	protected String getNombreCompleto() {
		return nombre + " " + apellidos;
	}
	
	protected String getCompletoNombre() {
		return apellidos + ", " + nombre;
	}
	
	public String toString() {
		return getNombreCompleto();
	}
}
