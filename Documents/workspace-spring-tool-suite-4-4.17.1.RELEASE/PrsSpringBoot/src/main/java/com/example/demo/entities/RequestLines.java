package com.example.demo.entities;

import org.apache.catalina.connector.Request;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "RequestLines")
public class RequestLines {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "RequestId")
	private Requests request;
	
	@ManyToOne
	@JoinColumn(name = "ProductId")
	private Products product;
	
	private int quantity;

	public RequestLines(int id, Requests request, Products product, int quantity) {
		this.id = id;
		this.request = request;
		this.product = product;
		this.quantity = quantity;
	}

	public RequestLines() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Requests getRequest() {
		return request;
	}

	public void setRequest(Requests request) {
		this.request = request;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "RequestLines [id=" + id + ", request=" + request + ", product=" + product + ", quantity=" + quantity
				+ "]";
	}
	
	
}