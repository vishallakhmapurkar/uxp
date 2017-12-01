package com.uxpsystems.assignment.config;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.uxpsystems.assignment.service.MyAppBasicAuthenticationEntryPoint;

@Configuration
@ComponentScan("com.uxpsystems") 
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyAppBasicAuthenticationEntryPoint myAppBasicAuthenticationEntryPoint;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/").permitAll().and().authorizeRequests()
		.antMatchers("/console/**").permitAll()
		.antMatchers("/user/**").hasAnyRole("ADMIN","USER")
		.antMatchers("/users/**").hasAnyRole("ADMIN","USER")
		.and().httpBasic().realmName("MY APP REALM")
		.authenticationEntryPoint(myAppBasicAuthenticationEntryPoint);
		
		http.headers().frameOptions().disable();
	} 
	@Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
        @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        	auth.inMemoryAuthentication()
			.withUser("user").password("user").roles("USER")
			.and()
			.withUser("admin").password("admin").roles("ADMIN");
	}
} 