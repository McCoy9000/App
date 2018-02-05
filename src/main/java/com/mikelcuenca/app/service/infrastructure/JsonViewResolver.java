package com.mikelcuenca.app.service.infrastructure;

import java.util.Locale;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Service
public class JsonViewResolver implements ViewResolver {  
    public View resolveViewName(String viewName, Locale locale) throws Exception {  
          MappingJackson2JsonView view = new MappingJackson2JsonView();  
          view.setPrettyPrint(true);  
          return view;  
    }  
}  