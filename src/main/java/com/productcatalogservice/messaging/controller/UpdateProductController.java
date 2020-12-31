package com.productcatalogservice.messaging.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.productcatalogservice.service.ProductService;
import com.warehouse.inventorymanagement.model.ProductMessage;

@Service
public class UpdateProductController {

	@Autowired
	private ProductService productService;

	private final Logger logger = LoggerFactory.getLogger(UpdateProductController.class);

	@KafkaListener(topics = "inventory", groupId = "inventory")
	public void consume(List<ProductMessage> messages) {
		logger.debug(String.format("Consumed Message -> %s", messages.get(0).getName()));
		logger.debug(String.format("Consumed Message quatity -> %s", messages.get(0).getQuantity()));
		boolean isUpdated = productService.updateProducts(messages);
		logger.info("Products updated : " + isUpdated);
	}

}
