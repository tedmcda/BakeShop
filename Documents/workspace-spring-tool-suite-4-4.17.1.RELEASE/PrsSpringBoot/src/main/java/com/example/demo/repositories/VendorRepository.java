package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Vendors;

public interface VendorRepository extends JpaRepository<Vendors, Integer> {

}