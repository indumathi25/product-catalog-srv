package com.productcatalogservice.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.productcatalogservice.data.Product;
import com.productcatalogservice.model.ProductRequest;
import com.productcatalogservice.repositories.ProductRepository;
import com.productcatalogservice.service.ProductService;
import com.productcatalogservice.utils.Publisher;
import com.warehouse.inventorymanagement.model.ProductMessage;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
    private Publisher producer;
	
	private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Override
	public List<Product> listProducts() {
		try {
			return productRepository.findAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean sellProduct(ProductRequest productName) {
		try {
			Product updateProduct = new Product();
			Product isExist = productRepository.findByName(productName.getName());
			
			logger.debug(String.format("sellProduct--> Name exist in DB --> %s", isExist));
			
			if (isExist != null && isExist.getQuantity() > 0) {
				int existingQuantity = isExist.getQuantity();
				int reducedQuantity = existingQuantity - 1;
				
				logger.debug(String.format("sellProduct--> reducedQuantity --> %s", reducedQuantity));
				
				updateProduct.setQuantity(reducedQuantity);
				updateProduct.setName(productName.getName());
				productRepository.save(updateProduct);
				this.producer.sendMessage(updateProduct);
			}else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateProducts(List<ProductMessage> messages) {
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
		return false;
	}
}

