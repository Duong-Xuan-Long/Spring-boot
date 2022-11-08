package com.example.Web_Ecommerce;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebEcommerceApplication.class, args);
	}
	@Bean
	public OpenAPI openApi() {
		return new OpenAPI()
				.info(new Info()
						.title("Customer vs Admin")
						.description("Danh sách các chức năng của ứng dụng")
						.version("v1.0")
						.contact(new Contact()
								.name("Xuan Long")
								.email("linesco@gmail.com"))
				);
	}
}
