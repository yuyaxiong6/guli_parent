package com.yuyx.eduservice.config;


import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
///配置类
@Configuration
@MapperScan("com.yuyx.eduservice.mapper")//匹配mapper
public class EdueConfig {


    /**
     2 * 逻辑删除插件
     3 */
 @Bean
 public ISqlInjector sqlInjector() {
         return new LogicSqlInjector();
         }
}
