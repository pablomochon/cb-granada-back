package com.basketballticketsproject.basketballticketsproject;

import com.basketballticketsproject.basketballticketsproject.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class CBGranadaTicketsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CBGranadaTicketsProjectApplication.class, args);
	}

}
