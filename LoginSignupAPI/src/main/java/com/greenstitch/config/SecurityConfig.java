package com.greenstitch.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.greenstitch.service.UserDetailsServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {

	
	private static final AntPathRequestMatcher[] AUTH_WHITELIST = {
	       new AntPathRequestMatcher("/signUp"),
	       new AntPathRequestMatcher("/h2-console/**")
	};

	@Bean
	public SecurityFilterChain springSecurityConfig(HttpSecurity http) throws Exception {
		
		http
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf().disable()
		.cors().configurationSource( new CorsConfigurationSource() {
	
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				
				CorsConfiguration cfg = new CorsConfiguration();
				cfg.setAllowedOrigins(Collections.singletonList("*"));
				cfg.setAllowedMethods(Collections.singletonList("*"));
				cfg.setAllowCredentials(true);
				cfg.setAllowedHeaders(Collections.singletonList("*"));
				cfg.setExposedHeaders(Arrays.asList("Authorization"));
				cfg.setMaxAge(3600L);
				return cfg;			
			}
		})
		.and()
		.authorizeHttpRequests(auth -> {
			auth.requestMatchers(AUTH_WHITELIST)
			.permitAll()
			.anyRequest()
			.authenticated();
		})	
		.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
		.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
		.headers().frameOptions().sameOrigin()
		.and()
		.formLogin()
		.and()
		.httpBasic();

		return http.build();

	}
	


	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}

}
