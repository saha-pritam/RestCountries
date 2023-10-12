package org.pritam.restCountries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiDescriptionBuilder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RestCountriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestCountriesApplication.class, args);
	}

	@Bean
	Docket getSwaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("org.pritam.restCountries.controller"))
				.build()
				.apiInfo(new ApiInfoBuilder()
						.description(null)
						.title("Rest Countries Documentation")
						.version("v3.1")
						.build());
	}
	
	@Bean
	public UiConfiguration uiConfiguration() {
	    return UiConfigurationBuilder//
	            .builder()//
	            .defaultModelsExpandDepth(-1)//
	            .build();//
	}
}
