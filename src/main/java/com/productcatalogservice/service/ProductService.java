package com.productcatalogservice.service;

import java.util.List;

import com.productcatalogservice.data.Product;
import com.productcatalogservice.model.ProductRequest;

public interface ProductService {

	List<Product> listProducts();

	boolean removeProduct(ProductRequest productName);
}
