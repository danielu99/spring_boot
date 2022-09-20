package com.formaciondbi.springboot.app.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.formaciondbi.springboot.app.commons.models.entity"})
public class SpringbootServiciosProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServiciosProductosApplication.class, args);
	}

}
