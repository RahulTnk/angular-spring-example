package com.amoraesdev.spaexample.services.resources;

import java.security.Principal;

import org.springframework.cloud.security.oauth2.resource.EnableOAuth2Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
@EnableOAuth2Resource
public class GreetingController {

	@RequestMapping(method = RequestMethod.GET)
	public Principal findAll(Principal user){
		return user;
	}
	
}
