package com.acepabdurohman.optimisticlockingjpaexample.repository;

import com.acepabdurohman.optimisticlockingjpaexample.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
