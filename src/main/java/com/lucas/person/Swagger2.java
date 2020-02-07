package com.lucas.person;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//http://localhost:8080/swagger-ui.html
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) //apiInfo()用来创建该Api的基本信息（这些基本信息会展现在文档页面中）
                .select() //select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现
                .apis(RequestHandlerSelectors.basePackage("com.lucas.person"))//采用指定扫描的包路径来定义
                .paths(PathSelectors.any()) //Swagger会扫描该包下所有Controller定义的API，并产生文档内容
                                            //（除了被@ApiIgnore指定的请求）
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("lucas spring boot swagger2")
                .termsOfServiceUrl("http://www.lucas.com")
                .contact("lucas")
                .version("1.0")
                .build();
    }

}