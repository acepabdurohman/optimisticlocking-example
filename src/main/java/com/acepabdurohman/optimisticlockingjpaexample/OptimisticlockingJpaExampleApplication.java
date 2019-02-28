package com.acepabdurohman.optimisticlockingjpaexample;

import com.acepabdurohman.optimisticlockingjpaexample.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class OptimisticlockingJpaExampleApplication implements CommandLineRunner {

    @Autowired
    private OrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(OptimisticlockingJpaExampleApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        orderService.save();

    }
}
