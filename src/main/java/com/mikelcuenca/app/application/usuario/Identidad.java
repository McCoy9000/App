package com.mikelcuenca.app.application.usuario;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mikelcuenca.app.application.contacto.Contacto;
import com.mikelcuenca.app.application.contacto.Email;
import com.mikelcuenca.app.application.generic.Nombre;
import com.mikelcuenca.app.application.generic.NombrePersona;
import com.mikelcuenca.app.application.imagen.Imagen;
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
	@Embedded
	private Nombre nombre;
	@OneToMany
	private List<Contacto> contactos;
	@Column
	private String descripcion;
	@OneToMany
	private Set<Usuario> usuarios;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="USUARIOS_PROFILES")
	private Map<String, Imagen> imagenes;
	
	protected Identidad() {
	}

	public static Identidad ofName(String nombre) {
		Identidad identidad = new Identidad();
		identidad.codIdentidad = UUID.randomUUID();
		identidad.nombre = NombrePersona.ofName(nombre);
		identidad.contactos = new ArrayList<Contacto>();
		return identidad; 
	}
	
	public static Identidad ofEmail(String email) {
		Identidad identidad = new Identidad();
		identidad.codIdentidad = UUID.randomUUID();
		identidad.nombre = NombrePersona.of();
		identidad.contactos = new ArrayList<Contacto>();
		identidad.contactos.add(Email.of(email));
		return identidad; 
	}
	
	public UUID getCodIdentidad() {
		return codIdentidad;
	}

	public void setCodIdentidad(UUID codIdentidad) {
		this.codIdentidad = codIdentidad;
	}

	public Nombre getNombre() {
		return nombre;
	}

	public void setNombre(Nombre nombre) {
		this.nombre = nombre;
	}

	public List<Contacto> getContactos() {
		return (contactos == null) ? new ArrayList<Contacto>() : contactos;
	}
	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Usuario> getUsuarios() {
		return (usuarios == null) ? new HashSet<Usuario>() : usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Map<String, Imagen> getImagenes() {
		return (imagenes == null) ? new HashMap<String, Imagen>() : imagenes;
	}

	public void setImagenes(Map<String, Imagen> imagenes) {
		this.imagenes = imagenes;
	}

	protected String getContactoPrincipal() {
		if (contactos == null || contactos.get(0) == null) {
			return "No hay contactos registrados";
		}
		for (Contacto contacto : contactos) {
			if (true == contacto.isPrincipal()) {
				return contacto.getContactoCompleto();
			}
		}
		return contactos.get(0).getContactoCompleto();
	}

	protected String getImage(String alias) {
		try {
			return imagenes.get(alias).getImagenUrl().getUrlAddress();
		} catch (NullPointerException npe) {
			logger.info("NullPointerException caught. Empty image url returned");
			return "No URL available";
		}
	}

	public String nombreCompleto() {
		return getNombre().getNombreCompleto();
	}
}
