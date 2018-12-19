package com.evanslaton.codefellowship;

import com.evanslaton.codefellowship.controllers.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CodefellowshipApplicationTests {

	@LocalServerPort
	private int port;
	@Autowired
	UserController controller;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testDefaultMessage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class)).contains("Basic Information");
	}

}
