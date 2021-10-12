package com.mindtekstudentportal.mindtek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class MindtekStudentportalApplication {

	public static void main(String[] args) {
		SpringApplication.run(MindtekStudentportalApplication.class, args);
	}


	@Bean
	public Docket swaggerConfiguration(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
//				.paths(PathSelectors.ant("/api/*"))
				.apis(RequestHandlerSelectors.basePackage("com.mindtekstudentportal.mindtek"))
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails(){
		return new ApiInfo(
				"Students Rest API calls",
				"Rest API calls developed for Mindtek students.",
				"1.0",
				"Keep it up.",
				new springfox.documentation.service.Contact("Mindtek", "https://www.mindtekbootcamp.com", "training@mindtekbootcamp.com"),
				"API License",
				"https://www.mindtekbootcamp.com",
				Collections.emptyList()
		);
	}

}
