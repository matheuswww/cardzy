package com.example.mscreditanalyst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MscreditanalystApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscreditanalystApplication.class, args);
	}

}
