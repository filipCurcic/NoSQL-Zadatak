package com.example.zadatak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.zadatak.config.AppProperties;

@SpringBootApplication
@EnableMongoRepositories
@EnableConfigurationProperties(AppProperties.class)
public class ZadatakApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZadatakApplication.class, args);
	}

}
