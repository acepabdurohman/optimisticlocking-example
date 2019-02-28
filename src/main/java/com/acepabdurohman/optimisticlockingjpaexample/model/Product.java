package com.acepabdurohman.optimisticlockingjpaexample.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_product")
@Data
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToOne
    @JoinColumn(name = "stock_id")
    private Stock stock;
}