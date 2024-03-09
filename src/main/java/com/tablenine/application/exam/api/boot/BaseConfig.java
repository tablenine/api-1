package com.tablenine.application.exam.api.boot;

import com.rabbitmq.stream.Address;
import com.rabbitmq.stream.Environment;
import com.rabbitmq.stream.Producer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseConfig {
	@Bean
	Environment streamEnvironment() {
		Address entryPoint = new Address("localhost", 5552);

		Environment environment = Environment.builder()
				.uri("rabbitmq-stream://rabbit:123123@localhost:5552/%2f")  // <1>
				.host(entryPoint.host())
				.port(entryPoint.port())
				.addressResolver(address -> entryPoint)
				.build();

		return environment;
	}

	@Bean
	Producer producer(Environment streamEnvironment) {
		Producer producer = streamEnvironment.producerBuilder()
				.superStream("test_super_stream")  // <1>
				.routing(message -> "test_super_stream-2") // <2>
				.producerBuilder()
				.build();  // <3>
		return producer;
	}
}
