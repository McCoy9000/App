package com.mikelcuenca.app.control.infrastructure;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.mikelcuenca.app._model.infrastructure.ErrorInfo;
import com.mikelcuenca.app._model.infrastructure._exceptions.GenericException;
import com.mikelcuenca.app._model.infrastructure._exceptions.GenericJsonException;

@ControllerAdvice
public class GlobalExceptionController {

	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);
	
	public static final String DEFAULT_ERROR_VIEW = "error";
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = {GenericException.class })
	public ModelAndView handleDefaultException(HttpServletRequest req, GenericException ex) {
        logger.info("Se ha lanzado una excepcion: " + ex.getMessageForUser());
        ErrorInfo error= new ErrorInfo(ex,req.getRequestURL().toString());
        ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW, "errorInfo",error);
        
	    return mav;
	  }
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = {GenericJsonException.class })
	public @ResponseBody ErrorInfo handleDefaultJsonException(HttpServletRequest req, GenericJsonException ex){
        logger.info("Se ha lanzado una excepcion Json: " + ex.getMessageForUser());
        ex.getMessage();
        ErrorInfo error= new ErrorInfo(ex,req.getRequestURL().toString());
 	    
        return error;
	  }
}
