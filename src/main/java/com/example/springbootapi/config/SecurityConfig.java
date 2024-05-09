package com.example.springbootapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // private PasswordEncoder pEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

	@Bean
	public UserDetailsService userDetailsService() {
		@SuppressWarnings("deprecation")
        UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("Fulano")
				.password("123")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}

}