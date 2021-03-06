package com.bead.rentonow.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class MainSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disable csrf
        http.csrf().disable();

        http
                .httpBasic()
                .and()
                //Enables cross origin requests
                .cors()
                .and()
                //Makes the system stateless and requires auth in every call
                //Comment out in order to integrate system with thymeleaf
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/ping")
                .permitAll()

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/property/filter")
                .hasAnyRole("ADMIN","GUEST")


                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/property/{id}")
                .hasAnyRole("ADMIN","GUEST")


                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/property/{personId}")
                .hasAnyRole("ADMIN","HOST")

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/property/{propertyId}/person/{personId}/image")
                .hasAnyRole("ADMIN","HOST")

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/property")
                .hasAnyRole("ADMIN","GUEST")

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/person")
                .hasRole("ADMIN")

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/person")
                .hasRole("ADMIN")

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/booking/{id}")
                .hasAnyRole("ADMIN","GUEST","HOST")

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE,"/booking/{id}")
                .hasRole("ADMIN")

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/booking/property/{propertyId}/person/{personId}")
                .hasAnyRole("ADMIN","GUEST")

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/booking")
                .hasRole("ADMIN")

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/statistics/{type}")
                .hasRole("ADMIN")


                .anyRequest()
                .authenticated() ;
    }

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from Person where username=?")
                .authoritiesByUsernameQuery("select username, role as authority from Person where username=?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}