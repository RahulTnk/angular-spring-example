package com.amoraesdev.spaexample.ui.web;

import org.springframework.cloud.security.oauth2.sso.OAuth2SsoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.csrf.CsrfFilter;

import com.amoraesdev.spautils.CsrfHeaderFilter;

/**
 * Security Configuration to allow unauthenticated access to static content 
 * @author Alessandro Moraes alessandro(at)amoraesdev.com
 */
@Configuration
public class SecurityConfiguration extends OAuth2SsoConfigurerAdapter {
	
	@Override
	public void match(RequestMatchers matchers) {
		matchers.anyRequest();
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.logout();
		http.authorizeRequests().antMatchers("/bower_components/**").permitAll();
		http.authorizeRequests().antMatchers("/index.html", "/home.html", "/")
        	.permitAll().anyRequest().authenticated()
        .and().csrf().csrfTokenRepository(CsrfHeaderFilter.csrfTokenRepository())
        .and().addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
	}
	
	
}
