package com.amoraesdev.spaexample.services.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.security.oauth2.resource.EnableOAuth2Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amoraesdev.spaexample.services.entities.Car;
import com.amoraesdev.spaexample.services.repositories.CarRepository;

@RestController
@RequestMapping("/cars")
@EnableOAuth2Resource
public class CarController {

	@Autowired
	private CarRepository repo;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Car> findAll(){
		return repo.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Car findById(@PathVariable Long id){
		return repo.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Car insertCar(@RequestBody Car car){
		car.setId(null);
		car = repo.saveAndFlush(car);
		return car;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Car updateCarro(@RequestBody Car car, @PathVariable Long id){
		car.setId(id);
		car = repo.saveAndFlush(car);
		return car;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteCar(@PathVariable Long id){
		repo.delete(id);
	}
}
