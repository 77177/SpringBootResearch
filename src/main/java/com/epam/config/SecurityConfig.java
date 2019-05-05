package com.epam.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/model/*")
                .hasAnyRole("USER")
                .and().formLogin()
                .defaultSuccessUrl("/model/all")
                .and().csrf().disable();
    }

    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(bCryptPasswordEncoder())
                .withUser("username")
                .password("$2a$10$a7lypRqxnZha5yGcUsCLi..gi1MUlm.R3N3PhAKo8O/77Zm3ZaR3a")
                .roles("USER");
    }
}
