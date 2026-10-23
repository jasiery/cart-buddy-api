package com.cartbuddy.api.entities;

import jakarta.persistence.*;
import lombok.Data;
import tools.jackson.core.ObjectReadContext;

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


}
