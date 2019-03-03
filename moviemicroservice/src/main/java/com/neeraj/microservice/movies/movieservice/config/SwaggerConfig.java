package com.neeraj.microservice.movies.movieservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static Set<String> DEFAULT_PRODUCES_AND_CONSUMES;

    private String projectName;
    private String projectDescription;
    private String projectVersion;
    private String projectContactName;
    private String projectContactEmail;

    public SwaggerConfig(
            @Value("${info.app.name}") String projectName,
            @Value("${info.app.description}") String projectDescription,
            @Value("${info.app.version}") String projectVersion,
            @Value("${info.contact.name}") String projectContactName,
            @Value("${info.contact.email}") String projectContactEmail) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectVersion = projectVersion;
        this.projectContactName = projectContactName;
        this.projectContactEmail = projectContactEmail;
        DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(Arrays.asList("application/json"));
    }

    @Bean
    public Docket produceAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.neeraj.microservice.movies.movieservice.controller"))
//                .paths(PathSelectors.ant("/*"))
                .build()
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }

    /*
        Describe your apis. The ApiInfo class that contains custom information about the API.
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(projectName)
                .description(projectDescription)
                .version(projectVersion)
                .contact(new Contact(projectContactName, "", projectContactEmail))
                .build();
    }
}
