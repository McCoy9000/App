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
@RequestMapping(value="/admin/cartas")
public class CartaController {
	
	@Autowired
	CartaService cartaService;
	
	@Autowired
	Messages messages;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<Carta> buscarTodos() {
		return cartaService.findAll();
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public Carta insertar(@RequestParam Carta carta) {
		try {
			return cartaService.add(carta);
		} catch (Exception e) {
			throw new GenericException(messages.get("carta.error.crear"), e);
		}
	}
	
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public Carta actualizar(@RequestParam Carta carta) {
		try {
			return cartaService.update(carta);
		} catch (Exception e) {
			throw new GenericException(messages.get("carta.error.actualizar"), e);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public Carta borrar(@PathVariable BigInteger id) {
		try {
			return cartaService.delete(id);
		} catch (Exception e) {
			throw new GenericException(messages.get("carta.error.borrar"), e);
		}
	}
			
	@RequestMapping(value="/filter", method=RequestMethod.POST)
	public List<Carta> buscarTodosFiltro(@RequestParam Carta carta, 
													@RequestParam Pageable pageable){
		if(carta == null) {
			if(pageable == null) {
				return cartaService.findAll();
			}
			return cartaService.findAll(pageable).getContent();
		} 
		if(pageable == null) {
			return cartaService.findAll(carta);
		} 
		return cartaService.findAll(carta, pageable).getContent();
	}
}
