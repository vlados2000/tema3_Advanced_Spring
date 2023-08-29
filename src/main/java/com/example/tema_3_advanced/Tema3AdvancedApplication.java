package com.example.tema_3_advanced;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Tema3AdvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(Tema3AdvancedApplication.class, args);
    }

}
