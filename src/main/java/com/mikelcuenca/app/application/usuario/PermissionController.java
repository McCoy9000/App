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
@RequestMapping(value="/admin/permissions")
public class PermissionController {
	
	@Autowired
	PermissionService permissionService;
	
	@Autowired
	Messages messages;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<Permission> buscarTodos() {
		return permissionService.findAll();
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public Permission insertar(@RequestParam Permission permission) {
		try {
			return permissionService.add(permission);
		} catch (Exception e) {
			throw new GenericException(messages.get("permission.error.crear"), e);
		}
	}
	
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public Permission actualizar(@RequestParam Permission permission) {
		try {
			return permissionService.update(permission);
		} catch (Exception e) {
			throw new GenericException(messages.get("permission.error.actualizar"), e);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public Permission borrar(@PathVariable BigInteger id) {
		try {
			return permissionService.delete(id);
		} catch (Exception e) {
			throw new GenericException(messages.get("permission.error.borrar"), e);
		}
	}
			
	@RequestMapping(value="/filter", method=RequestMethod.POST)
	public List<Permission> buscarTodosFiltro(@RequestParam Permission permission, 
													@RequestParam Pageable pageable){
		if(permission == null) {
			if(pageable == null) {
				return permissionService.findAll();
			}
			return permissionService.findAll(pageable).getContent();
		} 
		if(pageable == null) {
			return permissionService.findAll(permission);
		} 
		return permissionService.findAll(permission, pageable).getContent();
	}
}
