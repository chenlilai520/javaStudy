package com.spring.springstudy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.security.Principal;
import java.util.Date;

import static springfox.documentation.builders.PathSelectors.regex;


/**
 * @author chenlilai
 * @title: SwaggerConfiguration
 * @projectName javaStudy1
 * @description:
 * @date 2019/6/517:09
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);

    @Autowired
    private ProjectProperties projectProperties;


    public static final String API = "/(api|common)/.*";

    @Bean
    public Docket createRestApi(){
        StopWatch watch = new StopWatch();
        watch.start();
        Contact contact = new Contact(
                projectProperties.getSwagger().getContactName(),
                projectProperties.getSwagger().getContactUrl(),
                projectProperties.getSwagger().getContactEmail());
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title(projectProperties.getSwagger().getTitle())
                .description(projectProperties.getSwagger().getDescription())
                .version(projectProperties.getSwagger().getVersion())
                .termsOfServiceUrl(projectProperties.getSwagger().getTermsOfServiceUrl())
                .contact(contact)
                .license(projectProperties.getSwagger().getLicense())
                .licenseUrl(projectProperties.getSwagger().getLicenseUrl())
                .build();

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("APP接口")
                .apiInfo(apiInfo)
                .forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity.class)
                .ignoredParameterTypes(java.sql.Date.class, Principal.class)
                .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
                .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.spring.springstudy.controller"))
                .paths(regex(API))
                .build();
        watch.stop();
        log.info("启动 Swagger 使用了 {} ms", watch.getTotalTimeMillis());
        return docket;
    }
}