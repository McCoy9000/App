package com.mikelcuenca.app.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.mikelcuenca.app._model.infrastructure.ErrorInfo;
import com.mikelcuenca.app.service.infrastructure.CustomMappingExceptionResolver;
import com.mikelcuenca.app.service.infrastructure.JsonViewResolver;
import com.mikelcuenca.app.utilidades.Messages;

@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer{

	
	//TODO Cors Mappings
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	Messages messages;
	
	//ReloadableResourceBundleMessageSource no está limitado, como ResourceBundleMessageSource a archivos
	//.properties en el classpath, por lo que hay que indicar explícitamente la ruta.
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("${messages.source.basename}");
        source.setUseCodeAsDefaultMessage(true);
        source.setDefaultEncoding("UTF-8");
        return source;
    }
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties(){
	  PropertySourcesPlaceholderConfigurer pspc
	    = new PropertySourcesPlaceholderConfigurer();
	  Resource[] resources = new ClassPathResource[ ]
	    { new ClassPathResource("infrastructure.properties"), 
	    		new ClassPathResource("application.properties") };
	  pspc.setLocations(resources);
	  pspc.setIgnoreUnresolvablePlaceholders(true);
	  return pspc;
	}
	
	//TODO Decidir si invocarlo como bean y si singleton o prototype
	@Bean
	ErrorInfo errorInfo() {
		return new ErrorInfo();
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer
			.ignoreAcceptHeader(false)
			.defaultContentType(MediaType.TEXT_HTML);
	}
   
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);
		resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
		
		// Define all possible view resolvers
       	List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
		resolvers.add(jsonViewResolver());
		resolvers.add(jspViewResolver());
 		
		resolver.setViewResolvers(resolvers);

 		List<View> defaultViews = new ArrayList<View>();
 		defaultViews.add(defaultView());
 		resolver.setDefaultViews(defaultViews);
		return resolver;
	}
	
	//TODO decidir si se va a utilizar
//   @Bean
//   ResourceBundleViewResolver resourceBundleResolver() {
//	   ResourceBundleViewResolver bundleResolver = new ResourceBundleViewResolver();
//	   bundleResolver.setBasename("views/views");
//	   bundleResolver.setOrder(2);
//	   return bundleResolver;
//   }

   	@Bean
	public ViewResolver jsonViewResolver() {
		return new JsonViewResolver();
	}
   
	@Bean
	public ViewResolver jspViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("${views.jsp.locationPrefix}");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
   
	@Bean
	public View defaultView() {
		return new JstlView("${views.default}", messageSource);
	}
	
	@Bean
	public CustomMappingExceptionResolver customMappingExceptionResolver() {
	    CustomMappingExceptionResolver r = new CustomMappingExceptionResolver();

	    Properties mappings = new Properties();
	    mappings.setProperty("Exception", "unhandledError");
	    
	    r.setExceptionMappings(mappings);
	    r.setDefaultErrorView("${views.default.error}");
	    r.setDefaultStatusCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//	    Descomentar al pasar a producción para que no le llegue la excepción al cliente.
//	    r.setExceptionAttribute(null); 
	    return r;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
		registry.addInterceptor(localeInterceptor);
	}
	
	@Bean
	public LocaleResolver localeResolver () {
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		Locale defaultLocale = new Locale("${locale.default}");
		resolver.setDefaultLocale(defaultLocale);
		resolver.setCookieName("language");
		return resolver;
	}
}
