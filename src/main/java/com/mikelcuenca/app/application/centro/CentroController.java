package com.mikelcuenca.app.application.centro;

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
@RequestMapping(value="/admin/centros")
public class CentroController {
	
	@Autowired
	CentroService centroService;
	
	@Autowired
	Messages messages;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<Centro> buscarTodos() {
		return centroService.findAll();
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public Centro insertar(@RequestParam Centro centro) {
		try {
			return centroService.add(centro);
		} catch (Exception e) {
			throw new GenericException(messages.get("centro.error.crear"), e);
		}
	}
	
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public Centro actualizar(@RequestParam Centro centro) {
		try {
			return centroService.update(centro);
		} catch (Exception e) {
			throw new GenericException(messages.get("centro.error.actualizar"), e);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public Centro borrar(@PathVariable BigInteger id) {
		try {
			return centroService.delete(id);
		} catch (Exception e) {
			throw new GenericException(messages.get("centro.error.borrar"), e);
		}
	}
			
	@RequestMapping(value="/filter", method=RequestMethod.POST)
	public List<Centro> buscarTodosFiltro(@RequestParam Centro centro, 
													@RequestParam Pageable pageable){
		if(centro == null) {
			if(pageable == null) {
				return centroService.findAll();
			}
			return centroService.findAll(pageable).getContent();
		} 
		if(pageable == null) {
			return centroService.findAll(centro);
		} 
		return centroService.findAll(centro, pageable).getContent();
	}
}
