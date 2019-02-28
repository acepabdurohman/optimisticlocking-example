package com.acepabdurohman.optimisticlockingjpaexample.service.impl;

import com.acepabdurohman.optimisticlockingjpaexample.model.Order;
import com.acepabdurohman.optimisticlockingjpaexample.model.Product;
import com.acepabdurohman.optimisticlockingjpaexample.model.Stock;
import com.acepabdurohman.optimisticlockingjpaexample.repository.OrderRepository;
import com.acepabdurohman.optimisticlockingjpaexample.repository.ProductRepository;
import com.acepabdurohman.optimisticlockingjpaexample.repository.StockRepository;
import com.acepabdurohman.optimisticlockingjpaexample.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional
    public void save() {

        Stock stock = saveStock();

        Product product = saveProduct(stock);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // thread (user) 1
        executorService.execute(() -> saveOrder(stock, product, "user1"));

        // thread (user) 2
        executorService.execute(() -> saveOrder(stock, product, "user2"));

    }

    private Product saveProduct(Stock stock) {
        Product product = new Product();
        product.setName("Chitato");

        product.setStock(stock);

        return productRepository.save(product);
    }


    private void saveOrder(Stock stock, Product product, String threadName) {

        log.info(threadName + " is running");

        Stock currentStock = stockRepository.findOne(stock.getId());
        if (currentStock.getQuantity() == 0) {
            log.info("Stock barang habis");
        } else {
            try {
                log.info("Stock -> " + currentStock);

                Integer quantity = currentStock.getQuantity() - 1;
                currentStock.setQuantity(quantity);
                stockRepository.save(currentStock);

                Order order = new Order();
                order.setProduct(product);
                order.setTotal(new BigDecimal("10000"));

                orderRepository.save(order);

                log.info(threadName + " success update stock, current quantity is -> " + currentStock.getQuantity());

            } catch (ObjectOptimisticLockingFailureException e) {
                e.printStackTrace();
                log.error(threadName + " error update stock, wait for the other user until updating done");

                log.info("retry transaction");
                // retry
                saveOrder(stock, product, threadName);
            }
        }
    }

    private Stock saveStock() {
        Stock stock = new Stock();
        stock.setQuantity(1);
        return stockRepository.save(stock);
    }
}
