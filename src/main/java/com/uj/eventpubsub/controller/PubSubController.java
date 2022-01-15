package com.uj.eventpubsub.controller;

import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PubSubController {

	private static final Logger LOG = LoggerFactory.getLogger(PubSubController.class);

	@Autowired
	private PubSubTemplate pubSubTemplate;

	@Value("${gcp.pubsub.topic}")
	private String topic;

	@PostMapping
	public void publish(@RequestBody String message) throws ExecutionException, InterruptedException {
		LOG.info("Publishing to the topic [{}], message [{}]", topic, message);
		pubSubTemplate.publish(topic, message).get();
	}
}
