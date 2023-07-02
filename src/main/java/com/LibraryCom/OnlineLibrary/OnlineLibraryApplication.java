package com.LibraryCom.OnlineLibrary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineLibraryApplication {

	private static final Logger logger = LoggerFactory.getLogger(OnlineLibraryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OnlineLibraryApplication.class, args);

		logger.info("--Application has succesfully started--");
	}

}
