package com.hbnu.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Classname OrderApplication
 * @Author MayPrayer
 * @Description TODO
 * @Date 2020/8/18 8:11
 */
@SpringBootApplication
@ComponentScan("com.hbnu")
@EnableDiscoveryClient  //服务注册
@EnableFeignClients  //远程调用
@MapperScan("com.hbnu.order.mapper")
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}
