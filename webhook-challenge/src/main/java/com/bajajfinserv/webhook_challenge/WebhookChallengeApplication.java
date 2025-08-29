package com.bajajfinserv.webhook_challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WebhookChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebhookChallengeApplication.class, args);
		System.out.println("Application started successfully.");
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
