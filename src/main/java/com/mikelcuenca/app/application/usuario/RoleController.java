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
@RequestMapping(value="/admin/roles")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	Messages messages;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<Role> buscarTodos() {
		return roleService.findAll();
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public Role insertar(@RequestParam Role role) {
		try {
			return roleService.add(role);
		} catch (Exception e) {
			throw new GenericException(messages.get("role.error.crear"), e);
		}
	}
	
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public Role actualizar(@RequestParam Role role) {
		try {
			return roleService.update(role);
		} catch (Exception e) {
			throw new GenericException(messages.get("role.error.actualizar"), e);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public Role borrar(@PathVariable BigInteger id) {
		try {
			return roleService.delete(id);
		} catch (Exception e) {
			throw new GenericException(messages.get("role.error.borrar"), e);
		}
	}
			
	@RequestMapping(value="/filter", method=RequestMethod.POST)
	public List<Role> buscarTodosFiltro(@RequestParam Role role, 
													@RequestParam Pageable pageable){
		if(role == null) {
			if(pageable == null) {
				return roleService.findAll();
			}
			return roleService.findAll(pageable).getContent();
		} 
		if(pageable == null) {
			return roleService.findAll(role);
		} 
		return roleService.findAll(role, pageable).getContent();
	}
}
