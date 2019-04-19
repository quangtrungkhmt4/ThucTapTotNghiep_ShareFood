package com.sharefood.ShareFood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShareFoodApplication {

	public static void main(String[] args) {
		System.getProperties().put( "server.port", 9999 );
		SpringApplication.run(ShareFoodApplication.class, args);
	}

}
