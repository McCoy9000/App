package com.mikelcuenca.app.application.partida;

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
@RequestMapping(value="/admin/partidas")
public class PartidaController {
	
	@Autowired
	PartidaService partidaService;
	
	@Autowired
	Messages messages;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<Partida> buscarTodos() {
		return partidaService.findAll();
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public Partida insertar(@RequestParam Partida partida) {
		try {
			return partidaService.add(partida);
		} catch (Exception e) {
			throw new GenericException(messages.get("partida.error.crear"), e);
		}
	}
	
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public Partida actualizar(@RequestParam Partida partida) {
		try {
			return partidaService.update(partida);
		} catch (Exception e) {
			throw new GenericException(messages.get("partida.error.actualizar"), e);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public Partida borrar(@PathVariable BigInteger id) {
		try {
			return partidaService.delete(id);
		} catch (Exception e) {
			throw new GenericException(messages.get("partida.error.borrar"), e);
		}
	}
			
	@RequestMapping(value="/filter", method=RequestMethod.POST)
	public List<Partida> buscarTodosFiltro(@RequestParam Partida partida, 
													@RequestParam Pageable pageable){
		if(partida == null) {
			if(pageable == null) {
				return partidaService.findAll();
			}
			return partidaService.findAll(pageable).getContent();
		} 
		if(pageable == null) {
			return partidaService.findAll(partida);
		} 
		return partidaService.findAll(partida, pageable).getContent();
	}
}
