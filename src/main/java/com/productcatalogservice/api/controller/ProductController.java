package com.productcatalogservice.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.productcatalogservice.model.*;
import com.productcatalogservice.service.ProductService;
import com.warehouse.inventorymanagement.model.Response;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

	@Autowired
	private ProductService productService;

	private final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@GetMapping(value = "/listproducts")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Response> productList(HttpServletRequest request) {
		Response response = new Response();
		logger.info("List Products request recieved");
		List<Product> listProducts = productService.listProducts();
		response.setData(listProducts);
		response.setMessage("Available products list");
		response.setCode(200);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/sellproduct")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> sellProduct(@RequestBody ProductRequest productRequest) {
		Response response = new Response();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		logger.info("Sell Product request recieved");
		boolean isRemoved = false;
		if (productRequest != null) {
			isRemoved = productService.sellProduct(productRequest);
			if (!isRemoved) {
				response.setData(list);
				response.setMessage("Product sold is not succeded");
				response.setCode(500);
				return ResponseEntity.status(500).body(response);
			}
			map.put("sold", isRemoved);
			list.add(map);
			response.setData(list);
			response.setMessage("Product sold succesful");
			response.setCode(200);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);

		}
		response.setData(list);
		response.setMessage("Product request cannot be empty");
		response.setCode(400);
		return ResponseEntity.status(400).body(response);
	}
}
