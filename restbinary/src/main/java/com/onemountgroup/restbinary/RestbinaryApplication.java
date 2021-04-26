package com.onemountgroup.restbinary;

import com.onemountgroup.restbinary.model.Image;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
	Image.class
})
public class RestbinaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestbinaryApplication.class, args);
	}

}
