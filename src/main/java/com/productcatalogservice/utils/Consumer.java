package com.productcatalogservice.utils;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.warehouse.inventorymanagement.model.ProductMessage;
import com.productcatalogservice.data.Product;
import com.productcatalogservice.repositories.ProductRepository;

@Service
public class Consumer {

	@Autowired
	private ProductRepository productRepository;

	private final Logger logger = LoggerFactory.getLogger(Consumer.class);

	@KafkaListener(topics = "inventory", groupId = "inventory")
	public void consume(List<ProductMessage> messages) {

		logger.info(String.format("$$ -> Consumed Message -> %s", messages.get(0).getName()));
		logger.info(String.format("$$ -> Consumed Message quatity -> %s", messages.get(0).getQuantity()));

		if (messages.size() > 0) {
			//
			for (ProductMessage message : messages) {
	            //
				if (message != null && message.getName() != null && message.getQuantity() != 0) {
					//
					Product product = new Product();
					product.setName(message.getName());
					product.setQuantity(message.getQuantity());
					productRepository.save(product);
				}
			}
		}
	}
}
