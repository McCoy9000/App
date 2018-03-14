package com.mikelcuenca.app.config;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.mikelcuenca.app.infastructure.authentication.service.CustomAccessDeniedHandler;
import com.mikelcuenca.app.infastructure.authentication.service.CustomAuthenticationFailureHandler;
import com.mikelcuenca.app.infastructure.authentication.service.CustomAuthenticationSuccessHandler;
import com.mikelcuenca.app.infastructure.authentication.service.CustomLogoutHandler;
import com.mikelcuenca.app.infastructure.authentication.service.CustomLogoutSuccessHandler;
import com.mikelcuenca.app.infastructure.utils.Messages;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	private LogoutHandler logoutHandler;
	
	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;
	
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private CorsConfigurationSource configurationSource;
	
	@Autowired
	Messages messages;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.authenticationProvider(authenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http
		.authorizeRequests()
			.antMatchers("/recursoProhibido").denyAll()  
			.antMatchers("/admin/**").hasAnyRole("ADMIN", "ROOT")
			.antMatchers("/resources/**", "/signup", "/about, /accessDenied").permitAll()  
			.antMatchers("/**").hasAnyRole("USER", "ADMIN", "ROOT")
			.anyRequest().authenticated()
			.and().httpBasic()
			.and()
		.formLogin()
			.loginPage("/login")
			.permitAll()
			.loginProcessingUrl("/login")
			.successHandler(authenticationSuccessHandler)
			.defaultSuccessUrl("/home")
			.failureHandler(authenticationFailureHandler)
			.failureUrl("/login?error")
			.and()
		.logout()
			.logoutUrl("/logout")
			.permitAll()
			.addLogoutHandler(logoutHandler)
			.logoutSuccessHandler(logoutSuccessHandler)
			.logoutSuccessUrl("/login?logout")
			.and()
		.cors()
			.configurationSource(configurationSource)
			.and()
		.exceptionHandling()
			.accessDeniedHandler(accessDeniedHandler);
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);;
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		CustomAuthenticationSuccessHandler handler = new CustomAuthenticationSuccessHandler();
		handler.setDefaultTargetUrl("/home");
		return handler;
	}
	
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		//TODO Darle  funcionalidad
		CustomAuthenticationFailureHandler handler =  new CustomAuthenticationFailureHandler();
		handler.setDefaultFailureUrl("/login?error");
		return handler;
	}
	
	@Bean
	public LogoutHandler logoutHandler() {
		//TODO Darle  funcionalidad
		return new CustomLogoutHandler();
	}
	
	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		//TODO Darle  funcionalidad
		CustomLogoutSuccessHandler handler = new CustomLogoutSuccessHandler();
		handler.setDefaultTargetUrl("/login?logout");
		return handler;
	}
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
