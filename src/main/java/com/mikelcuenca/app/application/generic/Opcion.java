package com.mikelcuenca.app.application.generic;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.mikelcuenca.app.application.usuario.Usuario;

@Entity
public class Opcion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long opcionId;
	@Column
	private String nombreOpcion;
	@ManyToMany(mappedBy="opciones")
	private List<Usuario> usuarios;
}
