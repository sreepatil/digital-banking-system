package com.exam.sec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.exam.sec.filter.JwtAuthFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	JwtAuthFilter authFilter;

	@Autowired
	AuthEntryPoint authEntryPoint;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.csrf(csrf -> csrf.disable());
		http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
		http.authorizeHttpRequests(httpConfig -> {
			// PUBLIC
			httpConfig.requestMatchers(HttpMethod.POST, "/api/users/login").permitAll();
			httpConfig.requestMatchers(HttpMethod.GET, "/api/models").permitAll();
			// USER
			httpConfig.requestMatchers(HttpMethod.POST, "/api/prompts").hasAnyAuthority("USER");
				httpConfig.requestMatchers(HttpMethod.GET, "/api/prompts/my").hasAnyAuthority("USER");
			// ADMIN
			httpConfig.requestMatchers(HttpMethod.POST, "/api/admin/models").hasAnyAuthority("ADMIN");
			httpConfig.requestMatchers(HttpMethod.PUT, "/api/admin/models/{modelId}").hasAnyAuthority("ADMIN");
			httpConfig.requestMatchers(HttpMethod.PUT, "/api/admin/models/{modelId}/deactivate")
					.hasAnyAuthority("ADMIN");
			httpConfig.requestMatchers(HttpMethod.GET, "/api/admin/prompts").hasAnyAuthority("ADMIN");
			httpConfig.requestMatchers(HttpMethod.GET, "/api/admin/stats").hasAnyAuthority("ADMIN");
			httpConfig.requestMatchers(HttpMethod.DELETE, "/api/admin/prompts/{submissionId}").hasAnyAuthority("ADMIN");
			// USER + ADMIN
			httpConfig.requestMatchers(HttpMethod.GET, "/api/prompts/{submissionId}").hasAnyAuthority("USER", "ADMIN");

			httpConfig.anyRequest().authenticated();
		});
		http.exceptionHandling(excep -> excep.authenticationEntryPoint(authEntryPoint));
		return http.build();
	}

}
