package com.epam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableSwagger2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final private DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setUsersByUsernameQuery("SELECT username,password,enabled FROM users WHERE username= ?");
        jdbcDao.setAuthoritiesByUsernameQuery("SELECT username, role FROM users WHERE username = ?");
        jdbcDao.setDataSource(dataSource);
        return jdbcDao;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/model/*", "/swagger-ui.html/**").hasAnyRole("USER")
                .and()
                .formLogin()
                .defaultSuccessUrl("/model/all")
                .and().csrf().disable();

        http.headers().frameOptions().disable();
    }

    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(bCryptPasswordEncoder());
//        auth.inMemoryAuthentication()
//                .passwordEncoder(bCryptPasswordEncoder())
//                .withUser("username")
//                .password("$2a$10$tc7mZ2surGOiJZDoZaZYzefLL5sx8B1.7t53jx4QgTN.EeaUTIqEm")
//                .roles("USER");
    }
}
