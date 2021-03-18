package com.joljak.base.config;
/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private String version;
    private String title;

    @Bean
    public Docket apiV1() {
        version = "V1";
        title = "oscar API " + version;

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName(version)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ai.lane4.lane4.api.v1")) //ai.lane4.lane4.api.v1
                .paths(PathSelectors.ant("/v1/**"))
                .build()
                .apiInfo(apiInfo(title, version));

    }

    @Bean
    public Docket apiV2() {
        version = "V2";
        title = "oscar API " + version;

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName(version)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ai.lane4.lane4.api"))
                .paths(PathSelectors.ant("/v2/**"))
                .build()
                .apiInfo(apiInfo(title, version));

    }

    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfo(
                title,
                "Swagger로 생성한 API Docs",
                version,
                "www.flowers.ai",
                new Contact("Contact Me", "www.flowers.ai", "elliot@flowers.ai"),
                "Licenses",

                "www.flowers.ai",

                new ArrayList<>());
    }
}
*/

import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@Configuration

public class SwaggerConfig {

    @Bean
    public Docket swaggerApi() {
        //Authentication header 처리를 위해 사용 (전역설정으로 하는경우 필요없음)
        List global = new ArrayList();
        global.add(new ParameterBuilder().name("Authorization").
                description("Access Token").parameterType("header").
                required(false).modelRef(new ModelRef("string")).build());

        return new Docket(DocumentationType.SWAGGER_2).globalOperationParameters(global)
                .apiInfo((swaggerInfo())).select()
                .apis(RequestHandlerSelectors.basePackage("ai.lane4.api"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false);
        //.securitySchemes(Arrays.asList(apiKey()));   Authorization설정을 전역적으로하고싶을 때 (한번 세팅해서 모든  api에 적용하고싶은경우)
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer +jwtToken", "Authorization", "header");
    }

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder().title("Spring API Documentation")
                .description("서버 API 연동 문서\n\n DEV API BASE URL : https://testapi.lane4.ai/\n" + "PROD API BASE URL : https://api.lane4.ai/")
                .version("1").build();
    }
}
