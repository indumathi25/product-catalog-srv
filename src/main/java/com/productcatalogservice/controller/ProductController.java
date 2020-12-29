package com.productcatalogservice.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.productcatalogservice.data.Product;
import com.productcatalogservice.model.ProductRequest;
import com.productcatalogservice.service.ProductService;

@RestController
@RequestMapping("api/v1")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/listproducts")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<List<Product>> productList(HttpServletRequest request) {

		List<Product> listProducts = productService.listProducts();

		return ResponseEntity.status(HttpStatus.CREATED).body(listProducts);
	}

	@PutMapping("/sellproduct")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Boolean> sellProduct(@RequestBody ProductRequest productRequest) {
		//
		if (productRequest != null) {
			boolean isRemoved = productService.removeProduct(productRequest);
			return ResponseEntity.status(HttpStatus.CREATED).body(isRemoved);
		}
		return null;

	}

}