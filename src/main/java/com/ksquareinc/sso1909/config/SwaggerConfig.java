package com.ksquareinc.sso1909.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ksquareinc.sso1909"))
				.paths(PathSelectors.any())
				.build().apiInfo(apiEndPointsInfo());
	}
	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title("Ksquare SSO REST API")
				.description("Ksquare SSO Server utilizing Oauth2 and JWT as mean of authentication REST API")
				.contact(new Contact("Roberto Gil", "https://www.ksquareinc.com/", "roberto.gil@ksquareinc.com"))
				.license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
				.version("1.0.0")
				.build();
	}
}