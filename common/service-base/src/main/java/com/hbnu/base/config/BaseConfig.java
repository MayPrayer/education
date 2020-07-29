package com.hbnu.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * ClassName: BaseConfig <br/>
 * Description: <br/>
 * date: 2020/7/29 18:18<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */

@Configuration
@EnableSwagger2 //开启swagger
public class BaseConfig {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())  //里面调用的apiinfo方法
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Education API Doc")   //文档标题
                .description("This is a restful api document of Education.")  //文档描述
                .version("1.0")
                .build();
    }

}
