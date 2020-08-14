package com.hbnu.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * ClassName: EduApplication <br/>
 * Description: <br/>
 * date: 2020/7/29 17:21<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@EnableDiscoveryClient     //服务注册
@EnableFeignClients       //服务调用方
@SpringBootApplication   //启动类注解
@ComponentScan(basePackages = "com.hbnu") //设置扫描所有com.hbnu 包中的注解，不设置的话只能扫描当前包中
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
