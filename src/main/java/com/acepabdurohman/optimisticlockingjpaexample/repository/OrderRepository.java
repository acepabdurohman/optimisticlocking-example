package com.acepabdurohman.optimisticlockingjpaexample.repository;

import com.acepabdurohman.optimisticlockingjpaexample.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
