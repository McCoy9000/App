package com.mikelcuenca.app.application.contacto;

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
@RequestMapping(value="/admin/contactos")
public class ContactoController {
	
	@Autowired
	ContactoService contactoService;
	
	@Autowired
	Messages messages;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<Contacto> buscarTodos() {
		return contactoService.findAll();
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public Contacto insertar(@RequestParam Contacto contacto) {
		try {
			return contactoService.add(contacto);
		} catch (Exception e) {
			throw new GenericException(messages.get("contacto.error.crear"), e);
		}
	}
	
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public Contacto actualizar(@RequestParam Contacto contacto) {
		try {
			return contactoService.update(contacto);
		} catch (Exception e) {
			throw new GenericException(messages.get("contacto.error.actualizar"), e);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public Contacto borrar(@PathVariable BigInteger id) {
		try {
			return contactoService.delete(id);
		} catch (Exception e) {
			throw new GenericException(messages.get("contacto.error.borrar"), e);
		}
	}
			
	@RequestMapping(value="/filter", method=RequestMethod.POST)
	public List<Contacto> buscarTodosFiltro(@RequestParam Contacto contacto, 
													@RequestParam Pageable pageable){
		if(contacto == null) {
			if(pageable == null) {
				return contactoService.findAll();
			}
			return contactoService.findAll(pageable).getContent();
		} 
		if(pageable == null) {
			return contactoService.findAll(contacto);
		} 
		return contactoService.findAll(contacto, pageable).getContent();
	}
}
