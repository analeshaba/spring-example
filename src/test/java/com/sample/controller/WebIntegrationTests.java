package com.sample.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.sample.service.ContactApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContactApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class WebIntegrationTests {

	@LocalServerPort
	private int serverPort;

	@Test
	public void homePageTest() {
		RestTemplate restTemplate = new RestTemplate();
		assertEquals( ContactController.DEFAULT_INFO_MESSAGE, 
				      restTemplate.getForObject("http://127.0.0.1:" + serverPort, String.class));
	}
	@Test
	public void getContactTest() {
		String dummyData = "[{\"id\":1,\"name\":\"Sample Contact #1\",\"company\":\"ABC Company\",\"image\":null,\"email\":\"A@ABC.COM\",\"birthDate\":\"09/11/2017\",\"phoneNumber\":\"(773)224-1830\",\"address\":\"123 N Main Street\",\"city\":\"CHICAGO\",\"state\":\"IL\",\"postalCode\":\"60600\",\"country\":\"USA\"},{\"id\":2,\"name\":\"Sample Contact #2\",\"company\":\"Tx Company\",\"image\":null,\"email\":\"A@ABC.COM\",\"birthDate\":\"09/11/2017\",\"phoneNumber\":\"(773)224-1830\",\"address\":\"123 N Noeth Street\",\"city\":\"DALLAX\",\"state\":\"TX\",\"postalCode\":\"56050\",\"country\":\"USA\"},{\"id\":3,\"name\":\"Sample Contact #3\",\"company\":\"Some Company\",\"image\":null,\"email\":\"A@DCCOMPANU.COM\",\"birthDate\":\"09/11/2017\",\"phoneNumber\":\"(773)224-1830\",\"address\":\"Happy Way Avenue\",\"city\":\"WASHINGTON\",\"state\":\"DC\",\"postalCode\":\"56050\",\"country\":\"USA\"}]";
		
		RestTemplate restTemplate = new RestTemplate();
		assertEquals( dummyData, 
				      restTemplate.getForObject("http://127.0.0.1:" + serverPort + "/contacts", String.class));
	}
}