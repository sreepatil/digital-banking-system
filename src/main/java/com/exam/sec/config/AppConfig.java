package com.exam.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.exam.sec.repo.UserRepo;

@Configuration
@SuppressWarnings("deprecation")
public class AppConfig {

	@Bean
	UserDetailsService userDetailsService(UserRepo userRepo) {
		return username -> userRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("user not found!"));
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
