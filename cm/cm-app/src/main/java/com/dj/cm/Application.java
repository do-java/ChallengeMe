package com.dj.cm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaRepositories("com.dj.cm.persistence.repo")
@SpringBootApplication(scanBasePackages = "com.dj.cm")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}