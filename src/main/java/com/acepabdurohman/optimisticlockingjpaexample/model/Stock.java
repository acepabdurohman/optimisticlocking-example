package com.acepabdurohman.optimisticlockingjpaexample.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_stock")
@Data
public class Stock {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer quantity;

    @Version
    private long version;
}