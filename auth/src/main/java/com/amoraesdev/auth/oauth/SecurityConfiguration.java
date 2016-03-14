package com.amoraesdev.auth.oauth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security Configuration including queries to retrieve user data
 * @author Alessandro Moraes alessandro(at)amoraesdev.com
 */
@Configuration
@Order(-10)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final String SQL_USER_BY_USERNAME = 
			"SELECT email, password, active FROM auth.user_profile "
			+ "WHERE email = ?";
	
	private static final String SQL_AUTHORITIES_BY_USERNAME = 
			"SELECT email, 'ROLE_TRUSTED_USER'"
			+ "FROM auth.user_profile "
			+ "WHERE email = ?";
	
	@Autowired
    private DataSource dataSource;
	
	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login").permitAll();
		http.requestMatchers().antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access");
		http.authorizeRequests().anyRequest().authenticated();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/bower_components/**");
		web.ignoring().antMatchers("/imgs/**");
		web.ignoring().antMatchers("/css/**");
		
	}
	
	@Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery(SQL_USER_BY_USERNAME)
		.passwordEncoder(new CustomPasswordEncoder())
		.authoritiesByUsernameQuery(SQL_AUTHORITIES_BY_USERNAME);
    }


}
