package com.mikelcuenca.app.application.profile;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mikelcuenca.app.infastructure._exception.model.GenericException;
import com.mikelcuenca.app.infastructure.utils.Messages;

@Service
public class ProfileServiceImpl implements ProfileService{

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);

	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	Messages messages;
	
	ExampleMatcher matcher = ExampleMatcher.matching().withIncludeNullValues();
	
	@Override
	public List<Profile> findAll() {
		return profileRepository.findAll();
	}
	
	@Override
	public Page<Profile> findAll(Pageable pageable) {
		return profileRepository.findAll(pageable);
	}

	@Override
	public List<Profile> findAll(Profile profile) {
		Example<Profile> example = Example.of(profile, matcher);		
		return profileRepository.findAll(example);
	}

	@Override
	public Page<Profile> findAll(Profile profile, Pageable pageable) {
		if (profile == null) {
			return profileRepository.findAll(pageable);
		}
		Example<Profile> example = Example.of(profile, matcher);		
		return profileRepository.findAll(example, pageable);
	}

	@Override
	public Profile add(Profile profile) {
		return profileRepository.saveAndFlush(profile);
	}
	
	@Override
	public Profile update(Profile profile) {
		Optional<Profile> target;
		try {
			target = profileRepository.findById(profile.getProfileId());
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("profile.error.actualizar.inexistente"), e);
		}
		if (target.isPresent()) {
			return profileRepository.saveAndFlush(profile);
		}
		throw new GenericException(messages.get("profile.error.actualizar.inexistente"), new Throwable());
	}
	
	@Override
	public Profile delete(Profile profile) {
		return this.delete(profile.getProfileId());
	}
	
	@Override 
	public Profile delete(BigInteger id) {
		Optional<Profile> target; 
		try {
			target = profileRepository.findById(id); 
		} catch (IllegalArgumentException e)  {
			throw new GenericException(messages.get("profile.error.actualizar.inexistente"), e);
		}
		Profile profileBorrado;
		if (target.isPresent()) {
			profileBorrado = target.get();
			profileRepository.delete(profileBorrado);
			return target.get();
		} 
		throw new GenericException(messages.get("profile.error.actualizar.inexistente"), new Throwable());
	}
}
