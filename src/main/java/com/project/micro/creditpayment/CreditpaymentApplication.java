package com.project.micro.creditpayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CreditpaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditpaymentApplication.class, args);
	}

}
