package com.amoraesdev.spaexample.services.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="car", schema="spaexample")
public class Car {
	
	@Id
	@SequenceGenerator(name = "seq_car_generator", sequenceName = "spaexample.seq_car")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_car_generator")
	private Long id;
	
	private String brand;
	
	private String model;
	
	private Integer price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
}
