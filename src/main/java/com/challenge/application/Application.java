package com.challenge.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                //.paths(PathSelectors.ant("/api/*"))
                .apis(RequestHandlerSelectors.basePackage("com.challenge.application"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails(){
        return new ApiInfo(
                "Tenpo Challenge",
                "Backend challenge for tenpo interview",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Joaquin Galindez", "", "joaquingalindez91@gmail.com"),
                "API License",
                "",
                Collections.emptyList());

    }

}
