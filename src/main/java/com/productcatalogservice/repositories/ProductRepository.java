package com.productcatalogservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.productcatalogservice.data.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {
	@Query(value = "{'name' : ?0}")
	Product findByName(String name);
	
	@Query(value = "{'name' : ?0}")
	Product deleteByName(String name);
}
