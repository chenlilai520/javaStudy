package com.spring.springstudy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@Profile(Constants.SPRING_PROFILE_SWAGGER)
public class SwaggerConfiguration {

    private final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);

    @Autowired
    private ProjectProperties projectProperties;

    /**
     * app管理接口
     */
    public static final String API = "/(api|common)/.*";
    /**
     * 运营后台接口
     */
    public static final String ADMIN = "/(admin|common)/.*";
    /**
     * pc管理接口
     */
    public static final String WEB = "/(web|common)/.*";

    /**
     * 公众号管理接口
     */
    public static final String WE_CHAT = "/(wx|common)/.*";

    /**
     * Swagger Springfox configuration.
     *
     * @return the Swagger Springfox configuration
     */
    @Bean
    public Docket swaggerSpringFoxDocket() {
        log.info("启动 Swagger");
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
                .globalOperationParameters(globalParamters("ios"))
                .ignoredParameterTypes(java.sql.Date.class, Principal.class)
                .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
                .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cifmedia.web.rest"))
                .paths(regex(API))
                .build();
        watch.stop();
        log.info("启动 Swagger 使用了 {} ms", watch.getTotalTimeMillis());
        return docket;
    }

    @Bean
    public Docket createAdminRestApi() {
        Contact contact = new Contact(
                projectProperties.getSwagger().getContactName(),
                projectProperties.getSwagger().getContactUrl(),
                projectProperties.getSwagger().getContactEmail());
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("运营后台管理接口")
                .description(projectProperties.getSwagger().getDescription())
                .version(projectProperties.getSwagger().getVersion())
                .termsOfServiceUrl(projectProperties.getSwagger().getTermsOfServiceUrl())
                .contact(contact)
                .license(projectProperties.getSwagger().getLicense())
                .licenseUrl(projectProperties.getSwagger().getLicenseUrl())
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("运营后台接口")
                .apiInfo(apiInfo)
                .globalOperationParameters(globalParamters("sys"))
                .select()
                //api接口包扫描路径
                .apis(RequestHandlerSelectors.basePackage("com.cifmedia.web.rest"))
                //可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .paths(PathSelectors.regex(ADMIN))
                .build();
    }


    @Bean
    public Docket createWebRestApi() {
        Contact contact = new Contact(
                projectProperties.getSwagger().getContactName(),
                projectProperties.getSwagger().getContactUrl(),
                projectProperties.getSwagger().getContactEmail());
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("PC管理接口")
                .description(projectProperties.getSwagger().getDescription())
                .version(projectProperties.getSwagger().getVersion())
                .termsOfServiceUrl(projectProperties.getSwagger().getTermsOfServiceUrl())
                .contact(contact)
                .license(projectProperties.getSwagger().getLicense())
                .licenseUrl(projectProperties.getSwagger().getLicenseUrl())
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("PC管理接口")
                .apiInfo(apiInfo)
                .globalOperationParameters(globalParamters("web"))
                .select()
                //api接口包扫描路径
                .apis(RequestHandlerSelectors.basePackage("com.cifmedia.web.rest"))
                //可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .paths(PathSelectors.regex(WEB))
                .build();
    }


    @Bean
    public Docket createWeChatRestApi() {
        Contact contact = new Contact(
                projectProperties.getSwagger().getContactName(),
                projectProperties.getSwagger().getContactUrl(),
                projectProperties.getSwagger().getContactEmail());
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("公众号接口")
                .description(projectProperties.getSwagger().getDescription())
                .version(projectProperties.getSwagger().getVersion())
                .termsOfServiceUrl(projectProperties.getSwagger().getTermsOfServiceUrl())
                .contact(contact)
                .license(projectProperties.getSwagger().getLicense())
                .licenseUrl(projectProperties.getSwagger().getLicenseUrl())
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("公众号接口")
                .apiInfo(apiInfo)
                .globalOperationParameters(globalParamters("h5"))
                .select()
                //api接口包扫描路径
                .apis(RequestHandlerSelectors.basePackage("com.cifmedia.web.rest"))
                //可以根据url路径设置哪些请求加入文档，忽略哪些请求
                .paths(PathSelectors.regex(WE_CHAT))
                .build();
    }

    private List<Parameter> globalParamters(String platformStr) {
        List<Parameter> parameters = new ArrayList<>();
        Parameter parameter = new ParameterBuilder()
                .name("Authorization")
                .description("登录后获得的身份凭证")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .defaultValue("Bearer ")
                .build();
        Parameter version = new ParameterBuilder()
                .name("Version")
                .description("客户端版本号")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .defaultValue("v")
                .build();
        Parameter platform = new ParameterBuilder()
                .name("Platform")
                .description("平台 苹果：ios 安卓：android 公众号：h5 小程序 we_chat pc端 web 运营后台 sys")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .defaultValue(platformStr)
                .build();
        Parameter systemVersion = new ParameterBuilder()
                .name("System_version")
                .description("手机系统版本号")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .defaultValue("")
                .build();
        Parameter model = new ParameterBuilder()
                .name("Model")
                .description("手机型号")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .defaultValue("appleXs")
                .build();
        Parameter imei = new ParameterBuilder()
                .name("Imei")
                .description("手机串号")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .defaultValue("")
                .build();
        Parameter sign = new ParameterBuilder()
                .name("Sign")
                .description("签名信息")
                .modelRef(new ModelRef("string"))
                .parameterType("query")
                .required(false)
                .defaultValue("")
                .build();
        parameters.add(version);
        parameters.add(platform);
        parameters.add(model);
        parameters.add(imei);
        parameters.add(sign);
        parameters.add(systemVersion);
        parameters.add(parameter);
        return parameters;
    }
}