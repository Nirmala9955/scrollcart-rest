package com.sahu.scrollcart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.sahu.scrollcart.constants.PermissionConstants;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
	    return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/vendor/**" , "/images/**", "/lib/**");
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
			.antMatchers("/api/auth/**").permitAll()
		
			.antMatchers(HttpMethod.GET, "/api/product/list").hasAnyAuthority(PermissionConstants.GLOBAL_ADMINISTRATION)
		
			.anyRequest().authenticated().and()
			.formLogin().disable()
			.logout().disable()
			.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
				.maximumSessions(1).maxSessionsPreventsLogin(false);
		
		return http.build();
	}
	
}
