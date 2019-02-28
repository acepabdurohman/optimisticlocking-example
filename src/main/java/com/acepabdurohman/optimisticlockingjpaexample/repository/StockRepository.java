package com.acepabdurohman.optimisticlockingjpaexample.repository;

import com.acepabdurohman.optimisticlockingjpaexample.model.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Integer> { }
