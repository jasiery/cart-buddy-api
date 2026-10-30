package com.cartbuddy.api.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "budget_group")
@Data
public class BudgetGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(
            mappedBy = "budgetGroup",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Budget> budgets = new ArrayList<>();
}
