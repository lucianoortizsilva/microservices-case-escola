package com.lucianoortizsilva.aluno.config;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

/**
 * 
 * @see https://resilience4j.readme.io/docs/circuitbreaker
 *
 */
@Configuration
public class Resilience4JConfiguration {

	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration() {
		final CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()//
				.failureRateThreshold(50)//Limite taxa de falha
				.waitDurationInOpenState(Duration.ofMillis(60000))//
				.slidingWindowSize(100)//
				.build();//
		final TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()//
				.timeoutDuration(Duration.ofSeconds(1))//
				.build();//
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)//
				.timeLimiterConfig(timeLimiterConfig)//
				.circuitBreakerConfig(circuitBreakerConfig)//
				.build());//
	}

}