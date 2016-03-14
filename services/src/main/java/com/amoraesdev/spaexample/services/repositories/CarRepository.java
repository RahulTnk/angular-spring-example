package com.amoraesdev.spaexample.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amoraesdev.spaexample.services.entities.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
	
}
