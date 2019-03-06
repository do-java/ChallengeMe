package com.dj.cm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = "com.dj.cm")
@EnableCaching
public class BizRemoteServer {
	public static void main(String[] args) {
		SpringApplication.run(BizRemoteServer.class, args);
	}
}