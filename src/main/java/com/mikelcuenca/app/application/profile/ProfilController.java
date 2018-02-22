package com.mikelcuenca.app.application.profile;

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
@RequestMapping(value="/admin/profiles")
public class ProfilController {
	
	@Autowired
	ProfileService profileService;
	
	@Autowired
	Messages messages;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<Profile> buscarTodos() {
		return profileService.findAll();
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public Profile insertar(@RequestParam Profile profile) {
		try {
			return profileService.add(profile);
		} catch (Exception e) {
			throw new GenericException(messages.get("profile.error.crear"), e);
		}
	}
	
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public Profile actualizar(@RequestParam Profile profile) {
		try {
			return profileService.update(profile);
		} catch (Exception e) {
			throw new GenericException(messages.get("profile.error.actualizar"), e);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public Profile borrar(@PathVariable BigInteger id) {
		try {
			return profileService.delete(id);
		} catch (Exception e) {
			throw new GenericException(messages.get("profile.error.borrar"), e);
		}
	}
			
	@RequestMapping(value="/filter", method=RequestMethod.POST)
	public List<Profile> buscarTodosFiltro(@RequestParam Profile profile, 
													@RequestParam Pageable pageable){
		if(profile == null) {
			if(pageable == null) {
				return profileService.findAll();
			}
			return profileService.findAll(pageable).getContent();
		} 
		if(pageable == null) {
			return profileService.findAll(profile);
		} 
		return profileService.findAll(profile, pageable).getContent();
	}
}
