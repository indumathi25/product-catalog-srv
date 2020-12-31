package com.productcatalogservice.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.productcatalogservice.data.Product;

@Service
public class Publisher {
	
	private static final Logger logger = LoggerFactory.getLogger(Publisher.class);
	private static final String TOPIC = "updateInventory";
	
	@Autowired
	private KafkaTemplate<String, Product> kafkaTemplate;

	public void sendMessage(Product message) {
		logger.info(String.format("Producing message --> %s", message));
		this.kafkaTemplate.send(TOPIC, message);
	}
}
