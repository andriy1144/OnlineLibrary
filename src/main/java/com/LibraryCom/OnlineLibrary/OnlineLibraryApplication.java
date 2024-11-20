package com.LibraryCom.OnlineLibrary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = "com.LibraryCom.OnlineLibrary.*")
@EnableAspectJAutoProxy
@EnableScheduling
@EnableJpaRepositories(basePackages = {"com.LibraryCom.OnlineLibrary.Repositories"})
public class OnlineLibraryApplication {

	private static final Logger logger = LoggerFactory.getLogger(OnlineLibraryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OnlineLibraryApplication.class, args);

		logger.info("--Databases has been succesfuly conected--");
	}

}
