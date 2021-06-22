package com.onemount.onecinema.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {
  @Bean
	public OpenAPI customOpenAPI(
			@Value("${application-description}") String appDesciption,
			@Value("${application-version}") String appVersion) {

		return new OpenAPI()
			.info(new Info().title("ONECINEMA REST API Document")
			.version(appVersion)
			.contact(new Contact().name("Tuan Anh Dam").email("anh.dam@onemount.com").url("https://onemount.com"))
			.description(appDesciption)
			.termsOfService("http://swagger.io/terms/")
			.license(new License().name("Apache 2.0")
			.url("http://springdoc.org")));
	}

  @Bean
  public GroupedOpenApi filmOpenApi() {
    String paths[] = {"/film/**"};
    return GroupedOpenApi.builder().group("film").pathsToMatch(paths)
          .build();
  }

  @Bean
  public GroupedOpenApi eventOpenApi() {
    String paths[] = { "/event/**" };
    return GroupedOpenApi.builder().group("event").pathsToMatch(paths).build();
  }
  
  @Bean
  public GroupedOpenApi cinemaOpenApi() {
    String paths[] = { "/cinema/**" };
    return GroupedOpenApi.builder().group("cinema").pathsToMatch(paths).build();
  }
}
