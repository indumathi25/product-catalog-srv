package com.productcatalogservice.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.productcatalogservice.data.Product;
import com.productcatalogservice.model.ProductRequest;
import com.productcatalogservice.repositories.ProductRepository;
import com.productcatalogservice.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

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
	public boolean removeProduct(ProductRequest productName) {
		try {
			Product updateProduct = new Product();
			Product isExist = productRepository.findByName(productName.getName());
			if (isExist != null && isExist.getQuantity() > 0) {
				int existingQuantity = isExist.getQuantity();
				int reducedQuantity = existingQuantity - 1;
				updateProduct.setQuantity(reducedQuantity);
				updateProduct.setName(productName.getName());
				productRepository.save(updateProduct);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}
}

