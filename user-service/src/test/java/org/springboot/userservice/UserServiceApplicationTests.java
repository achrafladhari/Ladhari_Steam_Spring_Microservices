package org.springboot.userservice;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}
	@AfterAll
	static void tearDownAfterClass() throws Exception {

	}

	@BeforeEach
	void setUp() throws Exception{
		System.out.println("hello");
	}

	@AfterEach
	void tearDown() throws Exception {

	}

	@Test
	void contextLoads() {
	}



}
