package com.hbnu.edu.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: EduConfig <br/>
 * Description: <br/> 配置类
 * date: 2020/7/29 17:29<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Configuration   //定义配置类
@MapperScan("com.hbnu.edu.mapper") //设置mapper扫描包
public class EduConfig {


}
