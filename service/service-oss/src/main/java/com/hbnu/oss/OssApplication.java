package com.hbnu.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * ClassName: OssService <br/>
 * Description: <br/>
 * date: 2020/8/5 10:56<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)  //排除数据库自动配置 ，不启用数据库配置
@ComponentScan(basePackages = "com.hbnu")
public class OssApplication {
    public static void main(String[] args) {
       SpringApplication.run(OssApplication.class,args);
    }
}
