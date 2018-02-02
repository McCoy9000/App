/**
 * 
 */
package com.mikelcuenca.app.utilidades;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

/**
 * Clase de utilidad para simplificar el acceso a los mensajes de internacionalización. Ej de uso:
 * @Autowired
 *	Messages messages;
 *  
 *  messages.get("key")
 * @author BICUGUAL
 */
@Component
public class Messages {

	@Resource
	private MessageSource messageSource;

	private MessageSourceAccessor accessor;

	@PostConstruct
	private void init() {
		accessor = new MessageSourceAccessor(messageSource,  LocaleContextHolder.getLocale());
	}

	public String get(String code) {
		return accessor.getMessage(code);
	}

}
