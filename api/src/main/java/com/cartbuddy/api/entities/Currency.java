package com.cartbuddy.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "currency")
@Data
public class Currency {

    @Id
    @Column(name = "code")
    private String code;
    private String name;
    private String symbol;
}
