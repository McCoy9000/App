package com.mikelcuenca.app.application.carta;

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
@RequestMapping(value="/admin/barajas")
public class BarajaController {
	
	@Autowired
	BarajaService barajaService;
	
	@Autowired
	Messages messages;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<Baraja> buscarTodos() {
		return barajaService.findAll();
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public Baraja insertar(@RequestParam Baraja baraja) {
		try {
			return barajaService.add(baraja);
		} catch (Exception e) {
			throw new GenericException(messages.get("baraja.error.crear"), e);
		}
	}
	
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public Baraja actualizar(@RequestParam Baraja baraja) {
		try {
			return barajaService.update(baraja);
		} catch (Exception e) {
			throw new GenericException(messages.get("baraja.error.actualizar"), e);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public Baraja borrar(@PathVariable BigInteger id) {
		try {
			return barajaService.delete(id);
		} catch (Exception e) {
			throw new GenericException(messages.get("baraja.error.borrar"), e);
		}
	}
			
	@RequestMapping(value="/filter", method=RequestMethod.POST)
	public List<Baraja> buscarTodosFiltro(@RequestParam Baraja baraja, 
													@RequestParam Pageable pageable){
		if(baraja == null) {
			if(pageable == null) {
				return barajaService.findAll();
			}
			return barajaService.findAll(pageable).getContent();
		} 
		if(pageable == null) {
			return barajaService.findAll(baraja);
		} 
		return barajaService.findAll(baraja, pageable).getContent();
	}
}
