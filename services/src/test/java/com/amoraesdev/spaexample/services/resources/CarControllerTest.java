package com.amoraesdev.spaexample.services.resources;

import static com.amoraesdev.spaexample.TestHelper.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import com.amoraesdev.spaexample.services.entities.Car;
import com.amoraesdev.spaexample.services.repositories.CarRepository;

public class CarControllerTest extends AbstractControllerTest {
	
	private MockMvc mockMvc;
	
	private final String BASE_URL = "/cars";
	
	@Mock
	private CarRepository carRepository;
	
	@InjectMocks
	private CarController carController;
	
	
	@Before
	public void init(){
		//initialize the mocks before test starts
		MockitoAnnotations.initMocks(this);
		this.mockMvc = standaloneSetup(carController)
				.setHandlerExceptionResolvers(createExceptionResolver()) //set the same exceptionResolver of the application
				.build();
	}
	
	/**
	 * Find a car by Id
	 * @throws Exception
	 */
	@Test
	public void findById() throws Exception{
		//creates the constants to populate the entity and test the return later
		final long id = 1L;
		final String brand = "Toyota";
		final String model = "Corolla";
		final int price = 10000;
		//creates a car entity
		final Car car = new Car(); 	
		car.setId(id);
		car.setBrand(brand);
		car.setModel(model);
		car.setPrice(price);
		//when the mock carRepository.findOne() method is called with the "id" parameter, it will return the entity "car" 
		when(carRepository.findOne(id)).thenReturn(car);
		//mock the request and test the response
		this.mockMvc
			.perform(get(BASE_URL+"/{id}",id)
				.accept(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(new Long(id).intValue())))
				.andExpect(jsonPath("$.brand", is(brand)))
				.andExpect(jsonPath("$.model", is(model)))
				.andExpect(jsonPath("$.price", is(price)));
	}
		
	/**
	 * Try to find a car that does not exist
	 * @throws Exception
	 */
	@Test
	public void findByIdNotFound() throws Exception{
		final long id = 1L;
		when(carRepository.findOne(id)).thenReturn(null);
		this.mockMvc.perform(get(BASE_URL+"/{id}",id)
			.accept(APPLICATION_JSON_UTF8))
			.andExpect(status().isNotFound());
	}
	
	/**
	 * Test car insert	
	 * @throws Exception
	 */
	@Test
	public void insertCar() throws Exception{
		//creates the constants to populate the entity
		final long id = 1L;
		final String brand = "Toyota";
		final String model = "Corolla";
		final int price = 10000;
		//creates a car entity
		final Car car = new Car(); 	
		car.setId(id);
		car.setBrand(brand);
		car.setModel(model);
		car.setPrice(price);
		
		this.mockMvc
			.perform(post(BASE_URL)
					.contentType(APPLICATION_JSON_UTF8)
					.content(convertObjectToJsonBytes(car))
					)
				.andExpect(status().isOk());
		verify(carRepository, times(1)).saveAndFlush(any());
			
	}
	
	/**
	 * Test car insert with null brand and model
	 * @throws Exception
	 */
	@Test
	public void insertCarNullValues() throws Exception{
		//creates the constants to populate the entity
		final long id = 1L;
		final int price = 10000;
		//creates a car entity
		final Car car = new Car(); 	
		car.setId(id);
		car.setPrice(price);
		
		this.mockMvc
			.perform(post(BASE_URL)
					.contentType(APPLICATION_JSON_UTF8)
					.content(convertObjectToJsonBytes(car))
					)
				.andExpect(status().isBadRequest());
			
	}
	
	
	
	
}
