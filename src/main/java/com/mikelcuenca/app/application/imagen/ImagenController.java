package com.mikelcuenca.app.application.imagen;

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
@RequestMapping(value="/admin/imagens")
public class ImagenController {
	
	@Autowired
	ImagenService imagenService;
	
	@Autowired
	Messages messages;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<Imagen> buscarTodos() {
		return imagenService.findAll();
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public Imagen insertar(@RequestParam Imagen imagen) {
		try {
			return imagenService.add(imagen);
		} catch (Exception e) {
			throw new GenericException(messages.get("imagen.error.crear"), e);
		}
	}
	
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public Imagen actualizar(@RequestParam Imagen imagen) {
		try {
			return imagenService.update(imagen);
		} catch (Exception e) {
			throw new GenericException(messages.get("imagen.error.actualizar"), e);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public Imagen borrar(@PathVariable BigInteger id) {
		try {
			return imagenService.delete(id);
		} catch (Exception e) {
			throw new GenericException(messages.get("imagen.error.borrar"), e);
		}
	}
			
	@RequestMapping(value="/filter", method=RequestMethod.POST)
	public List<Imagen> buscarTodosFiltro(@RequestParam Imagen imagen, 
													@RequestParam Pageable pageable){
		if(imagen == null) {
			if(pageable == null) {
				return imagenService.findAll();
			}
			return imagenService.findAll(pageable).getContent();
		} 
		if(pageable == null) {
			return imagenService.findAll(imagen);
		} 
		return imagenService.findAll(imagen, pageable).getContent();
	}
}
