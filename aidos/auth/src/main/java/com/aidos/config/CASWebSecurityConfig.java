package com.aidos.config;

import net.sf.ehcache.Cache;

import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.authentication.EhCacheBasedTicketCache;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;

import com.aidos.service.CASUserDetailsService;

@Configuration
@EnableWebSecurity
@Order((Ordered.HIGHEST_PRECEDENCE + 10))
public class CASWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${aidos.cas.host}")
	private String casHost;

	@Value("${aidos.cas.key}")
	private String casKey;

	@Value("${aidos.service.host}")
	private String serviceHost;

	@Bean
	public ServiceProperties serviceProperties() {
		ServiceProperties serviceProperties = new ServiceProperties();
		serviceProperties.setService(serviceHost + "/uaa/j_spring_cas_security_check");
		serviceProperties.setSendRenew(false);
		return serviceProperties;
	}

	@Bean
	public CasAuthenticationProvider casAuthenticationProvider() {
		CasAuthenticationProvider casAuthenticationProvider = new CasAuthenticationProvider();
		casAuthenticationProvider.setAuthenticationUserDetailsService(authenticationUserDetailsService());
		casAuthenticationProvider.setServiceProperties(serviceProperties());
		casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator());
		casAuthenticationProvider.setStatelessTicketCache(statelessTicketCache());
		casAuthenticationProvider.setKey(casKey);
		return casAuthenticationProvider;
	}

	@Bean
	public AuthenticationUserDetailsService<Authentication> authenticationUserDetailsService() {
		return new CASUserDetailsService();
	}

	@Bean
	public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
		return new Cas20ServiceTicketValidator(casHost + "/cas");
	}

	@Bean
	public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
		CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
		casAuthenticationFilter.setAuthenticationManager(authenticationManager());
		return casAuthenticationFilter;
	}

	@Bean
	public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
		CasAuthenticationEntryPoint casAuthenticationEntryPoint = new CasAuthenticationEntryPoint();
		casAuthenticationEntryPoint.setLoginUrl(casHost + "/cas/login");
		casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
		return casAuthenticationEntryPoint;
	}

	@Bean
	public EhCacheBasedTicketCache statelessTicketCache() {
		EhCacheBasedTicketCache statelessTicketCache = new EhCacheBasedTicketCache();
		Cache cache = new Cache("casTickets", 50, true, false, 3600, 900);
		statelessTicketCache.setCache(cache);
		return statelessTicketCache;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilter(casAuthenticationFilter());
		http
			.exceptionHandling().authenticationEntryPoint(casAuthenticationEntryPoint())
		.and()
			.authorizeRequests().antMatchers("/j_spring_cas_security_check").permitAll().anyRequest().authenticated();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(casAuthenticationProvider());
	}

}