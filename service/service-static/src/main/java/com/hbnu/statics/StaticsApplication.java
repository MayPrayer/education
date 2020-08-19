package com.hbnu.statics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Classname StaticsApplication
 * @Author MayPrayer
 * @Description TODO
 * @Date 2020/8/19 8:01
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.hbnu")
@EnableFeignClients
@EnableScheduling //开启定时任务
@MapperScan("com.hbnu.statics.mapper")
public class StaticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaticsApplication.class,args);
    }
}
