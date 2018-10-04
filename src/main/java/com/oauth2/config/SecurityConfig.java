package com.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
@Import({ RootConfig.class })
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

//		auth.inMemoryAuthentication().
//		withUser("user").
//		password("1234").
//		roles("USER");

		auth.authenticationProvider(daoAuthenticationProvider());
	}

//	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/signup","/user/registration","/user/registrationtest","/confirmRegistration").permitAll()
            .antMatchers("/secured").access("hasRole('ADMIN')")
			.anyRequest().authenticated()

		.and()
		.formLogin()
				.successHandler(customAuthenticationSuccessHandler)
			.loginPage("/login").permitAll()
			.loginProcessingUrl("/doLogin")
			.failureUrl("/login?login_error=1")
			.defaultSuccessUrl("/welcome",true)
		.and()
			.logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/doLogout", "POST"))
				.clearAuthentication(true)
				.invalidateHttpSession(true)
				.deleteCookies()
				.logoutSuccessUrl("/login")
		.and()
		.csrf().disable();
	}

	@Bean
	public AuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		authenticationProvider.setUserDetailsService(userDetailsService);
		return authenticationProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

}
