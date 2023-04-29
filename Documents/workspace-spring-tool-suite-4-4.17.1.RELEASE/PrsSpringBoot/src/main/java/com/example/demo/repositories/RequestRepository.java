package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Requests;

public interface RequestRepository extends JpaRepository<Requests, Integer> {
	
	List<Requests> findByStatusAndUserIdNot(String status, int userId);
	
}