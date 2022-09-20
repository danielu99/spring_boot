package com.formaciondbi.springboot.app.item;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4jBulkheadConfigurationBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration //Configuration de Spring nos permite crear Beans con @Bean
public class AppConfig {
	
	@Bean("clienteRest")
	@LoadBalanced
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();
		/*
		 * Es un cliente HTTP para poder acceder a recursos que están en otros
		 * microservicios
		 */
	}
	
	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
		return factory -> factory.configureDefault(id ->{
			return new Resilience4JConfigBuilder(id)
					.circuitBreakerConfig(CircuitBreakerConfig.custom()
							.slidingWindowSize(10)
							.failureRateThreshold(50)
							.waitDurationInOpenState(Duration.ofSeconds(10))
							.permittedNumberOfCallsInHalfOpenState(5)
							.slowCallRateThreshold(50)
							.slowCallDurationThreshold(Duration.ofSeconds(2))
							.build())
					.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(6)).build())
					.build();
		});
	}
}
