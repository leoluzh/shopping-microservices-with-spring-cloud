package com.lambdasys.microservices.productserver.repository;

import com.lambdasys.microservices.productserver.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
}
