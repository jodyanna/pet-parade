package com.petparade.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final MyUserDetailsService myUserDetailsService;
  private final JwtRequestFilter jwtRequestFilter;

  @Autowired
  public SecurityConfig(MyUserDetailsService myUserDetailsService, JwtRequestFilter jwtRequestFilter) {
    this.myUserDetailsService = myUserDetailsService;
    this.jwtRequestFilter = jwtRequestFilter;
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf().disable()
        .cors()
        .and()
        .authorizeRequests()
        .antMatchers("/species/update}").hasRole("ADMIN")
        .antMatchers("/species/save").hasRole("ADMIN")
        .antMatchers("/pets/flagged").hasRole("ADMIN")
        .antMatchers("/users").hasRole("USER")
        .antMatchers("/pets").hasRole("USER")
        .antMatchers("/ratings").hasRole("USER")
        .antMatchers("/likes").hasRole("USER")
        .antMatchers("/pets/species").permitAll()
        .antMatchers("/pets/{\\d+}").permitAll()
        .antMatchers("pets/recent").permitAll()
        .antMatchers("/auth").permitAll()
        .antMatchers("/users/login").permitAll()
        .antMatchers("/users/signup").permitAll()
        .antMatchers("/species").permitAll()
        .antMatchers("/").permitAll()
        .anyRequest().authenticated()
        .and()
        .exceptionHandling()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(myUserDetailsService);
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(myUserDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }
}

