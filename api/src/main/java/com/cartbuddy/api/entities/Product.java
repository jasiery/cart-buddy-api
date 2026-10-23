package com.cartbuddy.api.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 100)
    private String brand;

    @Column(nullable = false, length = 20)
    private ProductCategory category =  ProductCategory.OTHERS;
}
