package com.LibraryCom.OnlineLibrary;

import com.LibraryCom.OnlineLibrary.FunctionalClasses.CheckReturnBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Timer;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableScheduling
public class OnlineLibraryApplication {

	private static final Logger logger = LoggerFactory.getLogger(OnlineLibraryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OnlineLibraryApplication.class, args);

		logger.info("--Databases has been succesfuly conected--");

	}

}
