package com.mikelcuenca.app.config;

import java.util.Arrays;

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

import com.mikelcuenca.app.service.infrastructure.authentication.CustomAccessDeniedHandler;
import com.mikelcuenca.app.service.infrastructure.authentication.CustomAuthenticationFailureHandler;
import com.mikelcuenca.app.service.infrastructure.authentication.CustomAuthenticationSuccessHandler;
import com.mikelcuenca.app.service.infrastructure.authentication.CustomLogoutHandler;
import com.mikelcuenca.app.service.infrastructure.authentication.CustomLogoutSuccessHandler;
import com.mikelcuenca.app.utilidades.Messages;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

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
	private PasswordEncoder encoder;
	
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
			.anyRequest().authenticated()
			.antMatchers("/recursoProhibido").denyAll()  
			.antMatchers("/resources/**", "/signup", "/about, /accessDenied").permitAll()  
			.antMatchers("/**").hasAnyRole("USER", "ADMIN", "ROOT")
			.antMatchers("/admin/**").hasAnyRole("ADMIN", "ROOT")
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
		authProvider.setPasswordEncoder(encoder);
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
		configuration.setAllowedMethods(Arrays.asList("GET","POST"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
