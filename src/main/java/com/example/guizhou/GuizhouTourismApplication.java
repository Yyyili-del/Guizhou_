package com.example.guizhou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.example.guizhou.entity")
@EnableJpaRepositories(basePackages = "com.example.guizhou.repository")
public class GuizhouTourismApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuizhouTourismApplication.class, args);
    }
}

