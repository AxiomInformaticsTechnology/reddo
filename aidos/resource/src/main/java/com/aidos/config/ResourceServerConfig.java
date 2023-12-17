package com.aidos.config;

import static org.springframework.http.HttpMethod.GET;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/**")
			.access("#oauth2.isClient() or hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
			.antMatchers(GET, "/manager/**", "/public/**").permitAll()
			.anyRequest().authenticated();
	}

}