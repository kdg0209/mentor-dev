package com.intw.mentorapi.config;

import com.google.common.collect.Lists;
import com.google.common.net.HttpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Profile("dev")
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private String title;
    private String description;

    @Bean
    public Docket AppApi(){
        title = "App Service";
        description = "사용자 API";

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("App")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.intw.mentorapi.app"))
                .paths(PathSelectors.ant("/app/**"))
                .build()
                .securityContexts(AppSecurityContext())
                .securitySchemes(Arrays.asList(apiKey()))
                .apiInfo(apiInfo(title, description));
    }

    @Bean
    public Docket AdminApi(){
        title = "Admin Service";
        description = "관리자 API";

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Admin")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.intw.mentorapi.admin"))
                .paths(PathSelectors.ant("/admin/**"))
                .build()
                .securityContexts(Arrays.asList(adminSecurityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .apiInfo(apiInfo(title, description));
    }

    private ApiInfo apiInfo(String title, String description){
        return new ApiInfoBuilder()
                .title(title)
                .version("1.0.0")
                .description(description)
                .build();
    }

    private SecurityContext adminSecurityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityContext> AppSecurityContext() {
        String paths[] = {
                "/app/board/write",
        };

        List<SecurityContext> securityContexts = new ArrayList<>();

        for (String path: paths) {
            securityContexts.add(SecurityContext.builder()
                    .securityReferences(defaultAuth())
                    .forPaths(PathSelectors.regex(path))
                    .build());
        }
        return securityContexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference(HttpHeaders.AUTHORIZATION, authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey(HttpHeaders.AUTHORIZATION, "token", "header");
    }
}
