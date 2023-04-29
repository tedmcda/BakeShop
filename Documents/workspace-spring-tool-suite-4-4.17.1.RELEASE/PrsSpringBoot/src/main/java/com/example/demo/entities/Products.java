package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Products")
public class Products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String partNbr;
	
	private String name;
	
	private double price;
	
	private String unit;
	
	private String photoPath;
	
	@ManyToOne
	@JoinColumn(name = "VendorId")
	private Vendors vendor;

	public Products() {
	}

	public Products(int id, String partNbr, String name, double price, String unit, String photoPath, Vendors vendor) {
		this.id = id;
		this.partNbr = partNbr;
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.photoPath = photoPath;
		this.vendor = vendor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPartNbr() {
		return partNbr;
	}

	public void setPartNbr(String partNbr) {
		this.partNbr = partNbr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Vendors getVendor() {
		return vendor;
	}

	public void setVendor(Vendors vendor) {
		this.vendor = vendor;
	}

	@Override
	public String toString() {
		return "Products [id=" + id + ", partNbr=" + partNbr + ", name=" + name + ", price=" + price + ", unit=" + unit
				+ ", photoPath=" + photoPath + ", vendor=" + vendor + "]";
	}

}