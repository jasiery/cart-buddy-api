package com.cartbuddy.api.controllers;

import com.cartbuddy.api.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyRepository currencyRepository;

    @GetMapping
    public ResponseEntity<?> getCurrencies() {

        return ResponseEntity.ok(List.of(currencyRepository.findAll()));
    }
}
