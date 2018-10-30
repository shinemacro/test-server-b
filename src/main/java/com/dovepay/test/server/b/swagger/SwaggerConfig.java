package com.dovepay.test.server.b.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration	// 该注解就是告诉Spring这个是一个配置文件类，这里配置的Bean要交给Spring去管理。这个就是用来取代Beans.xml这种文件的。
@EnableSwagger2	// 启用 Swagger
public class SwaggerConfig {
  @Bean
  public Docket createRestApi() {
    Predicate<RequestHandler> predicate = input -> true;
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .useDefaultResponseMessages(false)
        .select()
        .apis(predicate)
        .build();
  }
  
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Server-B 服务API文档")//大标题
        .version("0.0.1")//版本
        .build();
  }
  
}