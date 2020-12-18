package com.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configurable
//@Configuration
//@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	// roles admin allow to access /admin/**
	// roles user allow to access /user/**
	// custom 403 access denied handler
	// @formatter:off
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off
//		http.formLogin().defaultSuccessUrl("/usersList", true);

		// @formatter:off
		http.authorizeRequests()
			.antMatchers("/", "/user/userList/*").permitAll().anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/usersList", true).successHandler(new AuthenticationSuccessHandler() {
					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					        Authentication authentication) throws IOException, ServletException {
						// TODO Auto-generated method stub
						request.getSession().setMaxInactiveInterval(60);
						System.out.print("session expired");
					}
				}).permitAll()
					.and()
				.logout().permitAll();

		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired=true");
	}
}