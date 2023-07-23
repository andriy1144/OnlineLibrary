package com.LibraryCom.OnlineLibrary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication

public class OnlineLibraryApplication {

	private static final Logger logger = LoggerFactory.getLogger(OnlineLibraryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OnlineLibraryApplication.class, args);

		logger.info("--Databases has been succesfuly conected--");
	}

}
