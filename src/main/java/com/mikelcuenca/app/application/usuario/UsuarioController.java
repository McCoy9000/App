package com.mikelcuenca.app.application.usuario;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mikelcuenca.app.infastructure._exception.model.GenericException;
import com.mikelcuenca.app.infastructure.utils.Messages;

@RestController
@RequestMapping(value="/admin/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	Messages messages;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<Usuario> buscarTodos() {
		return usuarioService.findAll();
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public Usuario insertar(@RequestParam Usuario usuario) {
		try {
			return usuarioService.add(usuario);
		} catch (Exception e) {
			throw new GenericException(messages.get("usuario.error.crear"), e);
		}
	}
	
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public Usuario actualizar(@RequestParam Usuario usuario) {
		try {
			return usuarioService.update(usuario);
		} catch (Exception e) {
			throw new GenericException(messages.get("usuario.error.actualizar"), e);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public Usuario borrar(@PathVariable BigInteger id) {
		try {
			return usuarioService.delete(id);
		} catch (Exception e) {
			throw new GenericException(messages.get("usuario.error.borrar"), e);
		}
	}
			
	@RequestMapping(value="/filter", method=RequestMethod.POST)
	public List<Usuario> buscarTodosFiltro(@RequestParam Usuario usuario, 
													@RequestParam Pageable pageable){
		if(usuario == null) {
			if(pageable == null) {
				return usuarioService.findAll();
			}
			return usuarioService.findAll(pageable).getContent();
		} 
		if(pageable == null) {
			return usuarioService.findAll(usuario);
		} 
		return usuarioService.findAll(usuario, pageable).getContent();
	}
}
