package com.example.springboot.Spring_HATEOAS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude=HibernateJpaAutoConfiguration.class) 
public class SpringBootSpringHATEOASApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSpringHATEOASApplication.class, args);
	}

}
