package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.RequestLines;

public interface RequestLinesRepository extends JpaRepository<RequestLines, Integer> {

	List<RequestLines> findByRequestId(int requestId);
}
