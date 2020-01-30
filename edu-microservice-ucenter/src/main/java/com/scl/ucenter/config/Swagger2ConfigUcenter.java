package com.scl.ucenter.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created with IntelliJ IDEA.
 * User: chenglu
 * Date: 2020/1/28
 * Description:
 */
@Configuration
@EnableSwagger2
public class Swagger2ConfigUcenter {
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error/.*")))
                .build()
                ;
    }
    @Bean
    public Docket adminApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminAppi")
                .apiInfo(adminApiInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))
                .build();

    }
    private ApiInfo webApiInfo() {

        return new ApiInfoBuilder()
                .title("web-info-ucenter")
                .description("接口定义")
                .version("1.0")
                .contact(new Contact("scl","http://scl.com","1578380573@qq.com"))
                .build();
    }
    private ApiInfo adminApiInfo(){
        return new ApiInfoBuilder()
                .title("admin-info-ucenter")
                .description("接口定义")
                .version("1.0")
                .contact(new Contact("scl","http://scl.com","1578380573@qq.com"))
                .build();
    }
}
