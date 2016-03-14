package com.amoraesdev.spaexample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.amoraesdev.spaexample.ui.UIApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UIApplication.class)
@WebAppConfiguration
public class UiApplicationTests {

	@Test
	public void contextLoads() {
	}

}
