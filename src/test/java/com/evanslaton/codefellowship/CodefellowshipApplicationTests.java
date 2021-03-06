package com.evanslaton.codefellowship;

import com.evanslaton.codefellowship.applicationuser.UserController;
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

	// Tests that the splash page isn't empty
	@Test
	public void testSplashPage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class)).contains("Friends: Don't just make them, amass them.");
	}

	// Tests that the sign up page isn't empty
	@Test
	public void testSignUpPage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/signup", String.class)).contains("First Name");
	}
}
