package com.productcatalogservice.service;

import java.util.List;

import com.productcatalogservice.data.Product;
import com.productcatalogservice.model.ProductRequest;
import com.warehouse.inventorymanagement.model.ProductMessage;

public interface ProductService {

	List<Product> listProducts();

	boolean sellProduct(ProductRequest productName);
	
	boolean updateProducts(List<ProductMessage> messages);
}
