package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Products;

public interface ProductRepository extends JpaRepository<Products, Integer> {

}