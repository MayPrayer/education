package com.hbnu.edu.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
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
    /**
     * 逻辑删除插件  首先配置bean插件，实体类添加注解tablelogic
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
