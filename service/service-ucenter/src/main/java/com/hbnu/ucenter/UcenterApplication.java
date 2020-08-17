package com.hbnu.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Classname UcenterApplication
 * @Author MayPrayer
 * @Description TODO
 * @Date 2020/8/15 17:11
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.hbnu")
@MapperScan(basePackages = "com.hbnu.ucenter.mapper")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
