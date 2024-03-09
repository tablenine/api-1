package com.tablenine.application.exam.api.controller;

import com.rabbitmq.stream.Producer;
import com.tablenine.application.exam.api.config.Configuration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {
	private final Configuration configuration;
	private final Producer producer;

	@GetMapping("")
	public String getData() {
		return configuration.getDatasource().getUrl();
	}

	@PostMapping("")
	public String publish() {
		byte[] messagePayload = "hello".getBytes();

		producer.send(
				producer.messageBuilder().addData(messagePayload).build(),  // <2>
				confirmationStatus -> {  // <3>
					if (confirmationStatus.isConfirmed()) {
						// the message made it to the broker
						log.info(confirmationStatus.toString());
					} else {
						log.info(confirmationStatus.toString());
					}
				});
		return "";
	}
}
