package com.epam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.epam.repo")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }
}
