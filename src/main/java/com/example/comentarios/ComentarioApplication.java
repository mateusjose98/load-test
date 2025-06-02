package com.example.comentarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ComentarioApplication {
    public static void main(String[] args) {
        SpringApplication.run(ComentarioApplication.class, args);
    }
}
