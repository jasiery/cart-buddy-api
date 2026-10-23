package com.cartbuddy.api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "user_detail")
public class UserDetail {

    @Id
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(unique = true, length = 255)
    private String email;

    @Column(length = 30)
    private String phone;
}
