package com.dovepay.test.server.b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestServerBApplication {

	public static void main(String[] args) {
	    System.out.println("TestServerBApplication start...");
		SpringApplication.run(TestServerBApplication.class, args);
		System.out.println("TestServerBApplication run...");
	}
}
