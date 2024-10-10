package com.oasis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EntityScan(basePackages = "com.oasis.model") 
public class OasisSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(OasisSpringBootApplication.class, args);
	}

}
