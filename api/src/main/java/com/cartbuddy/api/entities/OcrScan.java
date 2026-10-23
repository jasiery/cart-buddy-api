package com.cartbuddy.api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ocr_scan")
public class OcrScan extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OcrScanStatus status = OcrScanStatus.PENDING;
}
