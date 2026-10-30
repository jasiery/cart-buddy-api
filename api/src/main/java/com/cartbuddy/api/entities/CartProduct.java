package com.cartbuddy.api.entities;

import jakarta.persistence.*;
import lombok.Data;
import tools.jackson.core.ObjectReadContext;

import java.math.BigDecimal;

@Entity
@Table(name = "cart_product")
@Data
public class CartProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cart", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product", nullable = false)
    private Product product;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal unitPrice;

    private int quantity;

    private String source;

}
