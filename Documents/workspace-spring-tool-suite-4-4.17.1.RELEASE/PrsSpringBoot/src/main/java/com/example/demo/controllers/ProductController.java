package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Products;
import com.example.demo.entities.User;
import com.example.demo.entities.Vendors;
import com.example.demo.repositories.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductRepository productRepo;

	@GetMapping("")
	public List<Products> getAll() {
		List<Products> products = productRepo.findAll();

		return products;
	}

	@GetMapping("/{id}")
	public Products getById(@PathVariable int id) {
		Products product = new Products();
		Optional<Products> optionalProducts = productRepo.findById(id);

		if (optionalProducts.isPresent()) {
			product = optionalProducts.get();
		}

		return product;
	}

	@PostMapping("")
	public Products createProduct(@RequestBody Products newProduct) {
		Products product = new Products();			
		
		boolean productExists = productRepo.findById(newProduct.getId()).isPresent();
		
		if (!productExists) {
			product = productRepo.save(newProduct);
		}
		
		return product;
	}

	@PutMapping("")
	public Products updateProduct(@RequestBody Products updatedProduct) {
		Products product = new Products();

		boolean productExists = productRepo.findById(updatedProduct.getId()).isPresent();

		if (productExists) {
			product = productRepo.save(updatedProduct);
		}

		return product;
	}
	
	@DeleteMapping("/{id}")
	public Products delete(@PathVariable int id) {
		Products product = new Products();
		Optional<Products> optionalProduct = productRepo.findById(id);

		boolean productExists = optionalProduct.isPresent();

		if (productExists) {
			product = optionalProduct.get();
			productRepo.deleteById(id);
		}
		return product;
	}
}
