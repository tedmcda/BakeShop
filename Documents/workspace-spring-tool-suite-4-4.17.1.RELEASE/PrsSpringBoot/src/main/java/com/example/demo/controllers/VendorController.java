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
import com.example.demo.repositories.VendorRepository;

@RestController
@RequestMapping("/Vendors")
public class VendorController {

	@Autowired
	VendorRepository vendorRepo;

	@GetMapping("")
	public List<Vendors> getAll() {
		List<Vendors> vendors = vendorRepo.findAll();

		return vendors;
	}
	
	@GetMapping("/{id}")
	public Vendors getById(@PathVariable int id) {
		Vendors vendor = new Vendors();
		Optional<Vendors> optionalVendors = vendorRepo.findById(id);
		
		if (optionalVendors.isPresent()) {
			vendor = optionalVendors.get();
		}
		
		return vendor;
	}
	
	@PostMapping("")
	public Vendors createVendor(@RequestBody Vendors newVendor) {
		Vendors vendor = new Vendors();			
		
		boolean vendorExists = vendorRepo.findById(newVendor.getId()).isPresent();
		
		if (!vendorExists) {
			vendor = vendorRepo.save(newVendor);
		}
		
		return vendor;
	}
	
	
	@PutMapping("")
	public Vendors updateVendor(@RequestBody Vendors updatedVendor) {
		Vendors vendor = new Vendors();

		boolean vendorExists = vendorRepo.findById(updatedVendor.getId()).isPresent();

		if (vendorExists) {
			vendor = vendorRepo.save(updatedVendor);
		}

		return vendor;
	}
	
	@DeleteMapping("/{id}")
	public Vendors delete(@PathVariable int id) {
		Vendors vendor = new Vendors();
		Optional<Vendors> optionalVendor = vendorRepo.findById(id);

		boolean vendorExists = optionalVendor.isPresent();

		if (vendorExists) {
			vendor = optionalVendor.get();
			vendorRepo.deleteById(id);
		}
		return vendor;
	}
}
