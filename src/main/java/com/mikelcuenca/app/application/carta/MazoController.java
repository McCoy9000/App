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
@RequestMapping(value="/admin/mazos")
public class MazoController {
	
	@Autowired
	MazoService mazoService;
	
	@Autowired
	Messages messages;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<Mazo> buscarTodos() {
		return mazoService.findAll();
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public Mazo insertar(@RequestParam Mazo mazo) {
		try {
			return mazoService.add(mazo);
		} catch (Exception e) {
			throw new GenericException(messages.get("mazo.error.crear"), e);
		}
	}
	
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public Mazo actualizar(@RequestParam Mazo mazo) {
		try {
			return mazoService.update(mazo);
		} catch (Exception e) {
			throw new GenericException(messages.get("mazo.error.actualizar"), e);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public Mazo borrar(@PathVariable BigInteger id) {
		try {
			return mazoService.delete(id);
		} catch (Exception e) {
			throw new GenericException(messages.get("mazo.error.borrar"), e);
		}
	}
			
	@RequestMapping(value="/filter", method=RequestMethod.POST)
	public List<Mazo> buscarTodosFiltro(@RequestParam Mazo mazo, 
													@RequestParam Pageable pageable){
		if(mazo == null) {
			if(pageable == null) {
				return mazoService.findAll();
			}
			return mazoService.findAll(pageable).getContent();
		} 
		if(pageable == null) {
			return mazoService.findAll(mazo);
		} 
		return mazoService.findAll(mazo, pageable).getContent();
	}
}
